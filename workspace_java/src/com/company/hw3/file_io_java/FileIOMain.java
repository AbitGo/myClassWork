package com.company.hw3.file_io_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileIOMain {

	private ArrayList outputList = new ArrayList();
	
	FileIOMain()
	{
		outputList.add("ѧ�ţ�040411214");
		outputList.add("�������޶��");
		outputList.add("�Ұ��˶�����ɽ");
		for (int i = 0; i < 1000; i++)
		{
			String morning ="Good Morning";
			outputList.add(morning + i);
		}
	}
	
	public void readFile(String file_path) throws IOException
	{
		File file;
		FileReader fr;
		BufferedReader br;

		file = new File(file_path);
		if (!file.exists()){
			System.out.println("\""+file_path+"\" does not exsit!");
			return;
		}

		fr = new FileReader(file);
		br = new BufferedReader(fr);
		String lineText = br.readLine();
		while (lineText!=null){
			System.out.println(lineText);
		lineText = br.readLine();
		}
		/*��ҵend*/
		
		br.close();
	}

	public void writeFile(String file_path) throws IOException
	{
		File file;
		FileWriter fw;
		PrintWriter pw;

		file = new File(file_path);
		if (!file.exists()){
			file.createNewFile();
		}
		fw = new FileWriter(file);
		pw = new PrintWriter(fw);
		
		/*��ҵ����д���´��룬ʹ��ѭ�����FileIOMain.outputList���ļ�*/
		String lineText;
		lineText = "ѧ�ţ�17200135123";
		pw.println(lineText);
		lineText = "������Τ����";
		pw.println(lineText);
		lineText = "�˶���CS:GO";
		pw.println(lineText);

		/*��ҵend*/
		
		pw.close();
	}
	
	public static void main(String[] args) throws IOException {
		FileIOMain io = new FileIOMain();
		io.readFile("d:/hw3_input.txt");
		io.writeFile("d:/hw3_output.txt");
	}

}
