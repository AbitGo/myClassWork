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
        System.out.println("�������Ѿ�׼���ý���������");
        for(int i = 0;i<5;i++){
            AddClientsThread addClientsThread = new AddClientsThread();
            addClientsThread.start();
        }
    }


    static class AddClientsThread extends Thread {
        Socket socket = null;
        Boolean flag = true;

        public Socket getSocket() {
            return socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //���ҪΪ����ͻ��˷���,�÷��������յĿͻ�������(Socket socket=serverSocket.accept())
                    // ����ѭ����,��ʵ���൱����N��������,��Ȼ�Ϳ�����n���û���ͨ��
                    socket = serverSocket.accept();
                    SocketList.add(socket);
                    System.out.println("���ӳɹ�");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (true) {
                    try {
                        if(socket.isClosed()){
                            this.flag = false;
                            SocketList.remove(this.socket);
                            System.out.println("��ǰ�߳��Ѿ�����");
                        }
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        while (flag){
                            String temp = br.readLine();
                            System.out.println(temp);

                            for(int i=0;i<SocketList.size();i++){

                                System.out.println("��ǰ��������:"+SocketList.size()+"��");
                                Socket tempSocket = SocketList.get(i);
                                if(tempSocket!=socket){
                                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(tempSocket.getOutputStream()));
                                    //send message
                                    bw.write(temp+"\n");
                                    bw.flush();
                                }
                            }
                            //������ݲ�Ϊ�յ�ʱ����������Ѿ���ȡ��
                            if (!temp.equals("")) {
                                break;
                            }
                        }
                        //����ر��ҵ�socket
                        //br.close();
                    } catch (IOException e) {
                        //���������ɾ���ô���
                        System.out.println("Socket is closed,��ǰ�߳��Ѿ�����");

                        this.flag = false;
                        SocketList.remove(this.socket);
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

