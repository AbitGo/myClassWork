#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>


char parSendBuf[] = "test String 1";
//14
char chRecBuf[15];
char parRecBuf[3];
char OK[3]="OK";
char NO[3]="NO";

int ProcessData(char *str1){
    if((str1[11]-'0')==1){
        return 1;
    }else{
        return 0;
    }
}

int main() {

    int mypipe[2],fd;
    if (pipe(mypipe)<0) { perror("pipe faild"); exit(0); }
    if ((fd=fork())<0) { perror("fork faild"); exit(0); }

    if (fd > 0) {
        printf("PR.size=%d",strlen(parSendBuf));
        write(mypipe[1],parSendBuf,strlen(parSendBuf));
        sleep(10);//等待子进程从管道将数据取走
        read(mypipe[0],parRecBuf,strlen(parRecBuf));
        printf("The parent process get: %s\n",parRecBuf );
        wait();
    }

    if (fd == 0) {
        read (mypipe[0],chRecBuf,strlen(chRecBuf));
        printf("The child process get: %s\n",chRecBuf );
        printf("CR.size=%d",strlen(chRecBuf));
        if(ProcessData(chRecBuf)==1)
        	write(mypipe[1],OK,strlen(OK));
        else{
            write(mypipe[1],NO,strlen(NO));
        }
    }    
}
