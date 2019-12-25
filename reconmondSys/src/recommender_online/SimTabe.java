package recommender_online;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;


public class SimTabe{
	HashMap<String,String> SimHashMap = new HashMap<String,String>();
	IdentityHashMap<Float, String> SimListEndiValueMap = new IdentityHashMap<Float, String>();
	ArrayList<String> enditem = new ArrayList<String>();
	HashMap<String,String> itemend = new HashMap<String,String>();
	public ArrayList<String> getEnditem() {
		return enditem;
	}
	public void readitem(String file_path,ArrayList<String> arrayList) throws Exception { //���еĽ�����ж�ֵ
		File file;
		FileReader fr;
		BufferedReader br;
		file = new File(file_path);//��ȡ��Ӱ�ļ�ֵ�Եĵ�ַ
		if (!file.exists()){
			System.out.println("\""+file_path+"\" does not exsit!");
			return;
		}
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		String lineText = null;
		String[] splitAddress;
		String split = null;
		while (br.ready()) {
			lineText = br.readLine();
			splitAddress=lineText.split("::");//�Կ�      �и��ַ���
			split = splitAddress[1]+splitAddress[2];
			itemend.put(splitAddress[0], split);//������put��hashmap��
			//��ȡarraylist�еļ�ֵ��
		}
		br.close();
		String itemid;
		Iterator<String> iterator = arrayList.iterator();
		while (iterator.hasNext()) {
			itemid = iterator.next();
			System.out.println(itemid+" - "+itemend.get(itemid));
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
		String lineText = null;
		String[] splitAddress;
		while (br.ready()) {
			lineText = br.readLine();
			splitAddress=lineText.split(" ");//�Կ�      �и��ַ���
			SimHashMap.put(splitAddress[0],splitAddress[1]);
		}
		br.close();
	}
	public String SimTabeMap(String MovieID) throws Exception {
		ArrayList<String> item1 = new ArrayList<String>();
		item1.add(String.valueOf(MovieID));
		String MovidValue = "";
		for (Entry<String, String> entrysim : SimHashMap.entrySet()) {
			if (entrysim.getKey().equals(MovieID)) {
				MovidValue =entrysim.getValue();
				readitem("movies.dat", item1);
			}
		}
		return MovidValue;
	}

	public ArrayList<String> SimTabeSort(String movalue) {
		String[] splitAddress = movalue.split("\\|\\|");
		int i=0;
		while (i<splitAddress.length) {
			String[] splitkey2=splitAddress[i].split(",");//�����и��ַ���,��
			SimListEndiValueMap.put(Float.parseFloat(splitkey2[1]), splitkey2[0]);//���������ֵĸ�ʽ����hashmap��
			i++;
		}
		List<Map.Entry<Float,String>> list = new ArrayList<Map.Entry<Float,String>>(SimListEndiValueMap.entrySet());
	    //Ȼ��ͨ���Ƚ�����ʵ������
        Collections.sort(list,new Comparator<Map.Entry<Float,String>>() {
            //��������
            public int compare(Entry<Float, String> o1,
                    Entry<Float, String> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
            
        });
        int j = 0;
		for(Map.Entry<Float,String> mapping:list){ 
			if (j==15) {
				break;
			}
		    enditem.add(mapping.getValue());
		    j++;
		}

		return enditem;
	}



}
