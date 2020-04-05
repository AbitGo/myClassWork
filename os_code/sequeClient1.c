#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
 
typedef struct
{
    /* data */
    int tranData;
    int readCount;
}MsgData;

typedef struct 
{
    //消息类型.是必须的.且值必须 > 0.这个值被系统使用
    long type;
    MsgData msgData;
}Msg;

void printMsg(Msg *msg) {
    printf("{ type = %ld, tranData = %d, readCount = %d }\n",
           msg->type, msg->msgData.tranData, msg->msgData.readCount);
}

void main()
{
    printf("START:子进程开始\n");
    key_t key;//消息队列键值
    int projectId;//项目ID
    int msgid;//消息队列标识符
    ////读取的消息类型位msgtyp=0的消息
    //即全部拉取
    int msgType = 0;
    int msgflg = 0;//调用阻塞直到接收消息成功为止
    Msg msg;
    int res;
    
    //创建消息队列键值
    key = (key_t)2333;
    if( key == -1 ){
        printf("FAILED:子进程1创建消息队列键值失败");
        return;
    }
    //建立消息队列
    msgid = msgget(key,IPC_CREAT|0666);
    if( msgid < 0){
        printf("FAILED:子进程1建立消息队列失败");
        return;
    }
    while(1){
        printf("read:子进程1读取消息:\n");
        res = msgrcv(msgid, &msg, sizeof(MsgData), msgType, IPC_NOWAIT);
        if (res < 0) {
            //如果消息接收完毕就退出，否则报错并退出
            if (errno == ENOMSG) {
                printf("Msg:子进程读取完\n");
                break;
            }

        }
        // 打印消息内容
        printMsg(&msg);
        //ssize_t msgrcv(int msqid, void *msgp, size_t msgsz, long msgtyp, int msgflg);
        // if(msgrcv(msgid,(struct msg*)&bufMsg,3,msgType,msgflg) < 0){
        //     printf("FAILED:子进程1读取失败\n");
        // }else{
        //     printf("%d\n",bufMsg.testData);
        //     printf("OK:子进程1读取成功\n");
        // }
        // if(bufMsg.testData==20){
        //     printf("END:子进程1读取结束\n");
        //     break;
        // }
    }
    
    //删除消息队列
    if(msgctl(msgid,IPC_RMID,0) < 0){
        perror("FAILED:子进程1删除消息队列失败\n");
        return;
    }
    printf("delete:子进程1删除消息队列\n");
    printf("END:子进程1程序结束\n");
    return;
}