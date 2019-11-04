#include<stdio.h>
#include<stdlib.h>

void printbinary(int count,char ch)
{
    fwrite(&count,4,1,stdout);
    fwrite(&ch,1,1,stdout);
}

int main(int argc,char* argv[])
{
    int i=1,count;
    char ch,prev='\0';
    if(argc==1)
    {
        printf("%s\n","wzip: file1 [file2 ...]");
        exit(1);
    }
    else if(argc>1)
    {
        count=0;
        while (argv[i]!=NULL)
        {
            FILE *fp = fopen(argv[i], "r");
            if (fp == NULL) {
                printf("wzip: cannot open file\n");
                exit(1);
            }      
            while((ch=fgetc(fp))!=EOF)
            { 
                    if(prev==0)
                    {
                        prev=ch;
                        count++;
                    }
                    else{
                        if(ch!=prev) {
                            printbinary(count,prev);
                            count=1;
                            prev=ch;
                        }
                        else {
                            count++;
                        }                   
                    }
	        }
            fclose(fp);
            i++;
        }
        if(count!=0)
            printbinary(count,prev);
    }
    exit(0);
}