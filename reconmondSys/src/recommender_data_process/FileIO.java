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
	public MyHashMap<String> read(String file_path) throws IOException {//读取两个映射文件
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
		while (br.ready()) {//循环读取 指向下一个
			lineText = br.readLine();
			splitAddress=lineText.split(" "); //切割字符串
			read.put(splitAddress[0], splitAddress[1]);
		}
		br.close();
		return read;
	}
	public void readFile(String file_path) throws IOException//读取文件
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
		while (br.ready()) {//循环读取 指向下一个
			lineText = br.readLine();
//			arrayList.add(lineText);
			splitAddress=lineText.split("::"); //切割字符串
			
			item_user = splitAddress[0]+","+splitAddress[2];//拼接用户和用户评分    使用arraylist是用于存相同用户的集合 存同一个key
			item_to_user.put(splitAddress[1],item_user);
//			System.out.println(item_user);
//			item_useradd = splitAddress[1]+":"+item_user;
//			
//			item_userList.add(item_useradd);
//			item_to_user.put(splitAddress[1],item_userList);//电影key和用户评分    
//			
			user_item = splitAddress[1]+","+splitAddress[2];//将历史记录映射为两个
//			user_itemadd = splitAddress[0]+":"+user_item;
//			user_itemList.add(user_itemadd);
//			System.out.println(user_item);
			user_to_item.put(splitAddress[0], user_item);//用户key和电影评分
		}
		br.close();
	}	
	public void arraylist_item_user() {//进行电影key和用户评分处理
		Iterator<Entry<String, String>> iteratorhashMap = item_to_user.entrySet().iterator();
		while(iteratorhashMap.hasNext()){
			System.out.println(iteratorhashMap.next() + " ");
		}
	}
	public void arraylist_user_item() {//打印测试 进行用户key和电影评分处理
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
		for (Entry<String, String> entry : user_or_item.entrySet()) {//电影key和用户评分
//			 System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			 pw.println( entry.getKey()+" "+entry.getValue());
		}
		pw.close();
	}
}
