#include<stdio.h>
#include<stdlib.h>
#define MAX 255 

int main(int argc,char* argv[])
{
    int i=1;  
    char buf[MAX];

    if(argc>1)
    {
        while (argv[i]!=NULL)
        {
            FILE *fp = fopen(argv[i], "r");
            if (fp == NULL) {
                printf("wcat: cannot open file\n");
                exit(1);
            }
            while(fgets(buf, MAX, fp)!=NULL){ 
	    	    printf("%s", buf); 
	        }
            fclose(fp);
            i++;
        }
    } 
    exit(0);
}
