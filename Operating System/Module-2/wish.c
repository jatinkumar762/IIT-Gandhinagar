#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h> 
#include<sys/types.h>
#include<sys/wait.h>
#include<fcntl.h> 

// size_t path = 9;
// char cmdPath[path][50]={"/usr/local/sbin","/usr/local/bin","/usr/sbin","/usr/bin","/sbin","/bin","/usr/games","/usr/local/games","/snap/bin"};

char error_message[30] = "An error has occurred\n";


char* removeSpaces(char *str) 
{ 
    int count = 0,s=0,e=0,l=0,k=0; 
    char *tmp;

    l=strlen(str);

    tmp=(char*)malloc(sizeof(char)*(l+1));

    while(str[s]==' ') 
        s++;
    
    e=l-1;

    while(str[e]==' '){
        // printf("%c",str[e]);
         e--;
    }

    k=0;
    for(count=s;count<=e;count++){
      tmp[k++]=str[count];
    }
    tmp[k] = '\0'; 

    return tmp;
} 

int checkRedirect(char** command,int* pi)
{
    int i=0,count=0,j=0;
    char tmp[100];
    char buff[100];
    
    strcpy (buff,command[*pi]);
    
    for(i=0;buff[i];i++)
    {
        if(buff[i]=='>'){
           count+=1;
        }
    }
    j=0;
    for(i=0;buff[i];i++)
    {
        if(buff[i]=='>')
        {
            if(i==0){  
                tmp[j++]='>';
                tmp[j]='\0';
                strcpy (command[*pi],tmp);
                (*pi)++;
                j=0;
            }
            else
            {
                tmp[j]='\0';
                strcpy (command[*pi],tmp);
                (*pi)++;
                strcpy (command[*pi],">");
                (*pi)++;
                j=0;
            }       
        }
        else
        {
            tmp[j++]=buff[i];
        }   
    }
    tmp[j]='\0';
    if(j!=0)
    {
        strcpy(command[*pi],tmp);
            (*pi)++;
    }
    return count;
} 

void parseInput(char** command,int count,int RD_count)
{
    int i,j,rd_location;
    char cmdPath[50]="/bin/";  
    char tmp[200];

    if(RD_count>0)
    {
        for(i=0;i<count;i++)
           if(!strcmp(command[i],">"))
           {
               rd_location=i;
               break;
           }

        if(RD_count>1 || (count-rd_location-1)>1 || (count-rd_location-1)==0)
        {
            write(STDERR_FILENO, error_message, strlen(error_message));
        }
        else if (fork() == 0)
        {
            int fd = open(command[rd_location+1], O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);

            dup2(fd, 1);   // make stdout go to file
            dup2(fd, 2);   // make stderr go to file - you may choose to not do this
                        // or perhaps send stderr to another file

            close(fd);     // fd no longer needed - the dup'ed handles are sufficient

            command[rd_location]=NULL;

            for(i=0;cmdPath[i];i++)
               tmp[i]=cmdPath[i];
            for(j=0;command[0][j];i++,j++)
                tmp[i]=command[0][j];

            tmp[i]='\0';

            execv(tmp, command);
        }
    }
    else if(fork()==0) {

        for(i=0;cmdPath[i];i++)
               tmp[i]=cmdPath[i];
        for(j=0;command[0][j];i++,j++)
               tmp[i]=command[0][j];

        tmp[i]='\0';

        execv(tmp, command);

    }
    wait(NULL);
}

int main(int argc,char* argv[])
{
    char** command;
    char** multiCommand;
    int i=0,j,mc_Count=0,RD_count=0;
    size_t size=100;
    char* buff=(char*)malloc(sizeof(char)*size);
    //char* temp=(char*)malloc(sizeof(char)*size);
    multiCommand=(char**)malloc(sizeof(char*)*10);
    for(i=0;i<10;i++)
    multiCommand[i]=(char*)malloc(sizeof(char)*50);

    command=(char**)malloc(sizeof(char*)*10);
    for(i=0;i<10;i++)
    command[i]=(char*)malloc(sizeof(char)*50);

    if(argc==1)
    {
           int pid = fork();
           if(pid==0)
           {
                    while(1)
                    {
                            printf("%s","wish>");
                            getline(&buff,&size,stdin);
                            buff[strlen(buff)-1]='\0';


                            mc_Count=0;
                            while((multiCommand[mc_Count]=strsep(&buff, "&"))!=NULL){
                                    multiCommand[mc_Count]=removeSpaces(multiCommand[mc_Count]);
                                    //printf("%s%ld\n",multiCommand[mc_Count],strlen(multiCommand[mc_Count]));
                                    mc_Count++;      
                            }

                            for(j=0;j<mc_Count;j++)
                            {
                                        i=0;
                                        while((command[i]=strsep(&multiCommand[j], " "))!=NULL)
                                        {
                                            if(strchr(command[i], '>'))
                                                RD_count=checkRedirect(command,&i);
                                            else
                                                i++;  
                                        }
                                        if(strcmp(command[0],"exit")==0)
                                            exit(0);
                                        else if(strcmp(command[0],"cd")==0)
                                        {
                                                if(i>2)
                                                {
                                                    write(STDERR_FILENO, error_message, strlen(error_message));
                                                    exit(1);
                                                }
                                                else{
                                                    if(chdir(command[1])!=0)
                                                        write(STDERR_FILENO, error_message, strlen(error_message)); 
                                                }
                                        }
                                        else if(strcmp(command[0],"path")==0)
                                        {

                                        }
                                        else
                                        {
                                            parseInput(command,i,RD_count);
                                        }       
                            }
                    }
           }
           wait(NULL);  
    }
    else if(argc==2)
    {
        FILE *fp = fopen(argv[1],"r");
		if(fp!=NULL)
        {
			fseek(fp, 0L, SEEK_END);
			int file_len = ftell(fp)+1;
			fseek(fp, 0L, SEEK_SET);
			char *command = (char*)calloc(file_len, sizeof(char));
			char ch;
			int index = 0;
			while((ch=fgetc(fp))!=EOF)
            {
				if(ch=='\n')
                {
					command[index]='\0';
					index=0;
					//parse_arg(command);				
				}
				else
					command[index++]=ch;
			}
			fclose(fp);
		}
    }
    else if(argc>2)
    {
        write(STDERR_FILENO, error_message, strlen(error_message));
        exit(1);
    }
    exit(0);
}
