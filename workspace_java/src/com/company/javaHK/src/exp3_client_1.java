package com.company.javaHK.src;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class exp3_client_1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket soc = new Socket("localhost", 10006);

        ClientRecThread clientRecThread = new ClientRecThread(soc);
        clientRecThread.start();

        System.out.print("请输入你的登录名：");
        String name = scanner.nextLine();

        PrintStream ps = new PrintStream(soc.getOutputStream());
        ps.print(name + "上线了!!\n");

        System.out.print("打下字符，并按下回车即可发送数据");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            System.out.print("打下字符，并按下回车即可发送数据");
            ps = new PrintStream(soc.getOutputStream());
            input = name + ":" + input + "\n";
            ps.print(input);
            input = scanner.nextLine();
        }


        ps.close();
        soc.close();
    }
}

class ClientRecThread_1 extends Thread {
    public Socket socket;
    public ClientRecThread_1(Socket socket){
        this.socket = socket;
    }

    public void receiveMrthod() {
        BufferedReader br = null;
        try {
            //读取数据
            BufferedReader binBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            do {
                String temp = binBufferedReader.readLine();
                if (temp == null) {
                    break;
                }
                System.out.println("收到消息: " + temp);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            receiveMrthod();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }

    }
}
