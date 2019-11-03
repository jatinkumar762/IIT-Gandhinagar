#include<stdio.h>
#include<stdlib.h>
#include<string.h>
   
int countFreq(char* pat, char* txt) 
{ 
    int M = strlen(pat); 
    int N = strlen(txt); 
    int i,j; 
    /* A loop to slide pat[] one by one */
    for (i = 0; i <= N - M; i++) 
    {        
        for (j = 0; j < M; j++) 
            if (txt[i+j] != pat[j]) 
                break; 
   
        if (j == M)   
        { 
           return 1; 
        } 
    } 
    return 0; 
} 

int main(int argc,char* argv[])
{
   int i=2;
   size_t MAX=355; 
   char* buf=(char*)malloc(sizeof(char)*MAX);

   if(argc==1)
   {
       printf("%s\n","wgrep: searchterm [file ...]");
       exit(1);
   }
   else if(argc==2)
   {
       while(fgets(buf, MAX, stdin)!=NULL)
       {
           if(countFreq(argv[1],buf))
                printf("%s", buf);
       }
   }
   else if(argc>2)
   {
       while (argv[i]!=NULL)
        {
            FILE *fp = fopen(argv[i], "r");
            if (fp == NULL) {
                printf("wgrep: cannot open file\n");
                exit(1);
            }
            while(getline(&buf, &MAX, fp)!=-1){ 
	    	    if(countFreq(argv[1],buf))
                    printf("%s", buf);  
	        }
            fclose(fp);
            i++;
        }
   }
  exit(0);
}
