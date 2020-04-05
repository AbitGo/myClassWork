#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

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


void main()
{
    printf("START:父进程开始\n");
    key_t key;      //消息队列键值
    int projectId;  //项目ID
    int msgid;      //消息队列标识符
    int msgflg = 0; //调用阻塞直到条件满足为止

    //创建消息队列键值
    key = (key_t)2333;
    if (key == -1){
        printf("FAILED:父进程创建消息队列键值失败");
        return;
    }
    //建立消息队列
    msgid = msgget(key, IPC_CREAT | 0666);
    if (msgid < 0){
        printf("FAILED:父进程建立消息队列失败");
        return;
    }

    Msg msg[10]={
        {1, {1, 0}},
        {1, {2, 0}},
        {1, {3, 0}},
        {2, {1, 0}},
        {2, {2, 0}},
        {2, {3, 0}},
        {3, {1, 0}},
        {3, {2, 0}}
    };

    // 消息类型处理:
    // 1.父进程产生数据, 子进程接收 
    // 2.子进程产生数据, 父进程接收
    //C99才支持在for循环内初始化
    int i = 0;
    while (i<10)
    {
        //写入消息队列
        if (msgsnd(msgid, &msg[i],sizeof(MsgData), msgflg) < 0){
            printf("write:父进程写入消息成功\n");
        }else{
            printf("write:父进程写入消息失败\n");
        }
        i++;
    }

    printf("OK:父进程所有数据发送完成\n");
    printf("END:父进程生命周期结束\n");
    return;
}