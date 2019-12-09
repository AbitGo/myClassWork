package com.company.javaHK.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class exp1_client {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket soc=new Socket("localhost",10002);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入消息至服务器");
		String message="";
		String temp;
		while(!(temp=br.readLine()).equals("send")){
			message=message+temp+"\n";
		}
		PrintStream ps=new PrintStream(soc.getOutputStream());
		ps.print(message);
		ps.close();
		soc.close();
	}

}
