package com.company.javaHK.src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class exp3_server {
    public static ServerSocket serverSocket = null;
    public static List<Socket> SocketList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(10006);
        System.out.println("服务器已经准备好接收数据了");
        for(int i = 0;i<5;i++){
            AddClientsThread addClientsThread = new AddClientsThread();
            addClientsThread.start();
        }
    }


    static class AddClientsThread extends Thread {
        Socket socket = null;

        public Socket getSocket() {
            return socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //如果要为多个客户端服务,让服务器接收的客户端请求(Socket socket=serverSocket.accept())
                    // 处于循环中,其实就相当于有N个服务器,当然就可以与n个用户端通信
                    socket = serverSocket.accept();
                    SocketList.add(socket);
                    System.out.println("连接成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (true) {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        do {
                            String temp = br.readLine();
                            System.out.println(temp);

                            for(int i=0;i<SocketList.size();i++){

                                System.out.println(SocketList.size());
                                Socket tempSocket = SocketList.get(i);
                                if(tempSocket!=socket){
                                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(tempSocket.getOutputStream()));
                                    //send message
                                    bw.write(temp+"\n");
                                    bw.flush();
                                }
                            }
                            //如果数据不为空的时候就是数据已经收取到
                            if (!temp.equals("")) {
                                break;
                            }
                        } while (true);
                        //他会关闭我的socket
                        //br.close();
                    } catch (IOException e) {
                        //如果下线则删除该代码
                        System.out.println("Socket is closed");
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

