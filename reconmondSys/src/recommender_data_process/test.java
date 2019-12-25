package recommender_data_process;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class test {
	public static void main(String[] args) throws IOException {
		FileIO io = new FileIO();
		MyHashMap<String> simitemMap = new MyHashMap<String>();
		ArrayList<String> arrayList = new ArrayList<String>();//存放key的集合
		ArrayList<String> simArrayList = new ArrayList<String>();//相似度集合

		io.setItem_to_user(io.read("item_to_user_test.dat"));//读取文件
		io.setUser_to_item(io.read("user_to_item_test.dat"));

		//正式数据
		Recommend recommend = new Recommend();
		ArrayList<Float> va,vb;
		float simVal;
		String temp;

		int num = io.getItem_to_user().size();//一共的电影数量
		int i =0;
		int j =0;


		for (Entry<String, String> entry : io.getItem_to_user().entrySet()) {//所有电影id的迭代器
			HashMap<String,Float> itemid = new HashMap<String,Float>();
			ArrayList<String> userList = new ArrayList<String>();//电影对应的评分
			ArrayList<Float> itemList = new ArrayList<Float>();//电影
			itemid = recommend.neighbour(entry.getKey(),io);//这里返回所有邻居电影的集合    下一步根据邻居电影集合查询所有用户看过该电影的评分
			va = recommend.SpListuser(io.getItem_to_user().get(entry.getKey()));//返回看过电影1的所有用户评分集合

			for (Map.Entry<String, Float> entry1 : itemid.entrySet()) {//邻居电影的迭代器
				String itemString = entry1.getKey();//对应的电影
				if (itemString.equals(entry.getKey())) {//当电影等于查询时 退出当前循环
					continue;
				}else{
					//准备进行查找电影映射表  将查找到的映射中的评分放入list
					String userString =  io.getItem_to_user().get(entry1.getKey());//查找该电影下的 所有用户的评分
					vb = recommend.SpListuser(userString);//得到一个电影的评分集合
					simVal = recommend.sim(va, vb);//进行相似度计算
					temp=itemString+","+simVal;//电影和对应的相似度
					simitemMap.put(entry.getKey(), temp);
				}
			}
			i++;
			System.out.println(i+"/"+io.getItem_to_user().size());
		}
		io.writeFile("simtable_test.dat", simitemMap);
	}
}
