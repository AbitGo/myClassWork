package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class ActivityPlay extends AppCompatActivity {
    private TextView textView;
    private Button left;
    private Button right;
    private ImageButton imageView;
    private int index = 0;
    private Button submit;
    private WebSocket mSocket;
    private String name;
    //初始状态
    private int send=-1;
    private int value_0=-1;

    private TextView result;

    private int[] imgIndex = {R.mipmap.shitou, R.mipmap.jiandao, R.mipmap.bu};
    private String[] imgContant = {"石头","剪刀","布"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playinterface);
        initView();
        start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1){
                case 1:
                    //连接成功
                    textView.setText(msg.obj.toString());
                    break;
                case 2:
                    //接收到的消息
                    String receive = msg.obj.toString();
                    JSONObject jsonObject = JSONObject.parseObject(receive);
                    value_0= jsonObject.getIntValue("value");
                    String name_O = jsonObject.getString("name");
                    //如果是自己发起的，那就直接接收即可
                    if (send==0){
                        send = -1;
                        submit.setEnabled(true);
                        if(value_0==index){
                            result.setText("结果:平局");
                        }else {
                            if((index+1)%3==value_0){
                                result.setText("结果:你赢了");
                            }else {
                                result.setText("结果:你输了");
                            }
                        }
                    }
                    //如果send等于-1的时候，证明是别人发过来的，
                    else if(send==-1){
                        textView.setText("请尽快猜拳");
                        submit.setEnabled(true);
                        //如果不是自己自己发起的，那就需要进行下一步的提交
                        send = 0;
                    }
                    break;
                case 3:
                    //关闭
                    textView.setText(msg.obj.toString());
                    break;
                case 4:
                    //失败
                    textView.setText(msg.obj.toString());
                    break;
            }
        }
    };


    private void start() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(3, TimeUnit.SECONDS)//设置连接超时时间
                .build();

        Request request = new Request.Builder().url("ws://47.102.42.105:8989/websocket/"+name).build();
        EchoWebSocketListener socketListener = new EchoWebSocketListener();
        mOkHttpClient.newWebSocket(request, socketListener);
        mOkHttpClient.dispatcher().executorService().shutdown();
    }

    void initView() {
        textView = findViewById(R.id.showMsg);
        final Intent intent = getIntent();
        name = intent.getStringExtra("roomId");
        textView.setText(name);

        left = findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = ((index + 3) - 1) % 3;
                imageView.setImageResource(imgIndex[index]);
            }
        });
        right = findViewById(R.id.right);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = ((index + 3) + 1) % 3;
                imageView.setImageResource(imgIndex[index]);
            }
        });
        imageView = findViewById(R.id.iv1);
        submit = findViewById(R.id.submitJSON);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自己先提交的数据
                //只要发送以下自己的名字以及index值即可
                if(send==-1){
                    send = 0;
                    JSONObject jsonObject= new JSONObject();
                    jsonObject.put("name",name);
                    jsonObject.put("value",index);
                    submit.setEnabled(false);
                    textView.setText("等待对方答映");
                    mSocket.send(jsonObject.toString());
                }
                //如果是0的时候那就是别人发过来的状态
                else if(send==0) {//true
                    //如果是别人先提交的数据
                    send = -1;
                    if(value_0==index){
                        result.setText("结果:平局");
                    }else {
                        if((index+1)%3==value_0){
                            result.setText("结果:你赢了");
                        }else {
                            result.setText("结果:你输了");
                        }
                    }
                    JSONObject jsonObject= new JSONObject();
                    jsonObject.put("name",name);
                    jsonObject.put("value",index);
                    submit.setEnabled(true);
                    mSocket.send(jsonObject.toString());
                }
            }
        });

        result = findViewById(R.id.result);
    }

    private final class EchoWebSocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            mSocket = webSocket;
//            String openid = "1";
//            //连接成功后，发送登录信息
//            String message = "{\"type\":\"login\",\"user_id\":\"" + openid + "\"}";
//            mSocket.send(message);
            showInfo(1,"连接成功！");
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
            //接收消息
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            showInfo(2,text);

            //收到服务器端发送来的信息后，每隔25秒发送一次心跳包
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mSocket.send("heartbeat");
                }
            }, 25000);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            //关闭
            showInfo(3,"closed:" + reason);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            showInfo(4,"failure:" + t.getMessage());
        }
    }
    void showInfo(int kind,String s){
        Message message = new Message();
        message.arg1=kind;
        message.obj = s;
        handler.sendMessage(message);
    }

}
