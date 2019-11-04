#include<stdio.h>
#include<stdlib.h>

int main(int argc,char*argv[])
{
    int i=1,count,k;
    char ch;
    if(argc==1)
    {
        printf("%s\n","wunzip: file1 [file2 ...]");
        exit(1);
    }
    else if(argc>1)
    {
        while (argv[i]!=NULL)
        {
            FILE *fp = fopen(argv[i], "r");
            if (fp == NULL) {
                printf("wunzip: cannot open file\n");
                exit(1);
            }  
            else{

                while (fread(&count, 4, 1, fp)==1) // to read file 
                { 
                    fread(&ch, 1, 1, fp);
                    for(k=0;k<count;k++)
                          printf("%c",ch);
                } 

            }
            i++;
        }
    }
    exit(0);
}