package recommender_data_process;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class test {
	public static void main(String[] args) throws IOException {
		FileIO io = new FileIO();
		MyHashMap<String> simitemMap = new MyHashMap<String>();
		ArrayList<String> arrayList = new ArrayList<String>();//���key�ļ���
		ArrayList<String> simArrayList = new ArrayList<String>();//���ƶȼ���

		io.setItem_to_user(io.read("item_to_user_test.dat"));//��ȡ�ļ�
		io.setUser_to_item(io.read("user_to_item_test.dat"));

		//��ʽ����
		Recommend recommend = new Recommend();
		ArrayList<Float> va,vb;
		float simVal;
		String temp;

		int num = io.getItem_to_user().size();//һ���ĵ�Ӱ����
		int i =0;
		int j =0;


		for (Entry<String, String> entry : io.getItem_to_user().entrySet()) {//���е�Ӱid�ĵ�����
			HashMap<String,Float> itemid = new HashMap<String,Float>();
			ArrayList<String> userList = new ArrayList<String>();//��Ӱ��Ӧ������
			ArrayList<Float> itemList = new ArrayList<Float>();//��Ӱ
			itemid = recommend.neighbour(entry.getKey(),io);//���ﷵ�������ھӵ�Ӱ�ļ���    ��һ�������ھӵ�Ӱ���ϲ�ѯ�����û������õ�Ӱ������
			va = recommend.SpListuser(io.getItem_to_user().get(entry.getKey()));//���ؿ�����Ӱ1�������û����ּ���

			for (Map.Entry<String, Float> entry1 : itemid.entrySet()) {//�ھӵ�Ӱ�ĵ�����
				String itemString = entry1.getKey();//��Ӧ�ĵ�Ӱ
				if (itemString.equals(entry.getKey())) {//����Ӱ���ڲ�ѯʱ �˳���ǰѭ��
					continue;
				}else{
					//׼�����в��ҵ�Ӱӳ���  �����ҵ���ӳ���е����ַ���list
					String userString =  io.getItem_to_user().get(entry1.getKey());//���Ҹõ�Ӱ�µ� �����û�������
					vb = recommend.SpListuser(userString);//�õ�һ����Ӱ�����ּ���
					simVal = recommend.sim(va, vb);//�������ƶȼ���
					temp=itemString+","+simVal;//��Ӱ�Ͷ�Ӧ�����ƶ�
					simitemMap.put(entry.getKey(), temp);
				}
			}
			i++;
			System.out.println(i+"/"+io.getItem_to_user().size());
		}
		io.writeFile("simtable_test.dat", simitemMap);
	}
}
