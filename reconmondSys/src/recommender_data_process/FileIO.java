package recommender_data_process;

import java.io.*;
import java.util.*;
import java.util.Map.*;


public class FileIO{
	MyHashMap<String> item_to_user = new MyHashMap<String>();
	MyHashMap<String> user_to_item = new MyHashMap<String>();
	
	public MyHashMap<String> getItem_to_user() {
		return item_to_user;
	}
	public MyHashMap<String> getUser_to_item() {
		return user_to_item;
	}
	public void setUser_to_item(MyHashMap<String> user_to_item) {
		this.user_to_item = user_to_item;
	}
	public void setItem_to_user(MyHashMap<String> item_to_user) {
		this.item_to_user = item_to_user;
	}
	public MyHashMap<String> read(String file_path) throws IOException {//��ȡ����ӳ���ļ�
		MyHashMap<String> read = new MyHashMap<String>();
		File file;
		FileReader fr;
		BufferedReader br;
		file = new File(file_path);
		if (!file.exists()){
			System.out.println("\""+file_path+"\" does not exsit!");
			return read;
		}
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		String lineText = null;
		String[] splitAddress;
		while (br.ready()) {//ѭ����ȡ ָ����һ��
			lineText = br.readLine();
			splitAddress=lineText.split(" "); //�и��ַ���
			read.put(splitAddress[0], splitAddress[1]);
		}
		br.close();
		return read;
	}
	public void readFile(String file_path) throws IOException//��ȡ�ļ�
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
		String lineText = null;
		String item_user;
		String user_item;
		String[] splitAddress;
//		String user_itemadd;
//		String item_useradd;
		while (br.ready()) {//ѭ����ȡ ָ����һ��
			lineText = br.readLine();
//			arrayList.add(lineText);
			splitAddress=lineText.split("::"); //�и��ַ���
			
			item_user = splitAddress[0]+","+splitAddress[2];//ƴ���û����û�����    ʹ��arraylist�����ڴ���ͬ�û��ļ��� ��ͬһ��key
			item_to_user.put(splitAddress[1],item_user);
//			System.out.println(item_user);
//			item_useradd = splitAddress[1]+":"+item_user;
//			
//			item_userList.add(item_useradd);
//			item_to_user.put(splitAddress[1],item_userList);//��Ӱkey���û�����    
//			
			user_item = splitAddress[1]+","+splitAddress[2];//����ʷ��¼ӳ��Ϊ����
//			user_itemadd = splitAddress[0]+":"+user_item;
//			user_itemList.add(user_itemadd);
//			System.out.println(user_item);
			user_to_item.put(splitAddress[0], user_item);//�û�key�͵�Ӱ����
		}
		br.close();
	}	
	public void arraylist_item_user() {//���е�Ӱkey���û����ִ���
		Iterator<Entry<String, String>> iteratorhashMap = item_to_user.entrySet().iterator();
		while(iteratorhashMap.hasNext()){
			System.out.println(iteratorhashMap.next() + " ");
		}
	}
	public void arraylist_user_item() {//��ӡ���� �����û�key�͵�Ӱ���ִ���
		Iterator<Entry<String, String>> iteratorhashMap = user_to_item.entrySet().iterator();
		while(iteratorhashMap.hasNext()){
			System.out.println(iteratorhashMap.next() + " ");
		}
	}
	public void writeFilesim(String file_path,List<String> simList) throws IOException
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
		int size=simList.size();  
        String[] lineText = (String[])simList.toArray(new String[size]);  
        for(int i=0;i<lineText.length;i++){  
            pw.println(lineText[i]);
        }  
		pw.close();
	}
	public void writeFile(String file_path,MyHashMap<String> user_or_item) throws IOException
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
		for (Entry<String, String> entry : user_or_item.entrySet()) {//��Ӱkey���û�����
//			 System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			 pw.println( entry.getKey()+" "+entry.getValue());
		}
		pw.close();
	}
}
