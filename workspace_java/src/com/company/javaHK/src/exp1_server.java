package com.company.javaHK.src;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class exp1_server {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket (10002);
		Socket soc=ss.accept();
		BufferedReader br=new BufferedReader(new InputStreamReader(soc.getInputStream()));
		String message="";
		String temp=null;
		System.out.println("it is ok");
		
		do{
			temp=br.readLine();
			if(temp==null){
				break;
			}
			message=message+temp;
		}while(true);
		System.out.println("message is"+message);
		br.close();
		PrintStream ps=new PrintStream(new FileOutputStream("message.txt"));
		ps.println(message);
		ps.close();
		
	}

}
