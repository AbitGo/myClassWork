package com.company.javaHK.src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class exp2_server {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket (10006);
		Socket soc=ss.accept();
		double mianji=0.0;
		BufferedReader br=new BufferedReader(new InputStreamReader(soc.getInputStream()));
		String message="";

		System.out.println("exp2_server is ok");
		
		do{
			String temp=br.readLine();
			System.out.println("temp"+temp);
			double num = (double) Double.parseDouble(temp.substring(0,temp.length()));
			message = num*num*Math.PI+"";
			
			if(!temp.equals("")){
				break;
			}
		}while(true);
		System.out.println("area is: "+message);

		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
		bw.write(message);
		//send message
		bw.flush();
		br.close();

		//¹Ø±Õsocket
		soc.close();
		//¹Ø±ÕServerSocket
		ss.close();
		
	}

}
