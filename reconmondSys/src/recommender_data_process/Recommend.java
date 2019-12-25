package recommender_data_process;

import java.util.*;
import java.util.Map.Entry;

public class Recommend {
	public HashMap<String,Float> neighbour(String itemid,FileIO io) {//邻居算法 获取到电影id的邻居用户id  根据电影id查找相似的邻居用户 
		String userString = "";
		ArrayList<String> arrayListid = new ArrayList<String>();//所有该电影的邻居用户id
		ArrayList<String> neighbourid = new ArrayList<String>();//所有该电影的邻居集合
		HashMap<String,Float> userid = new HashMap<String,Float>();
		userString = io.getItem_to_user().get(itemid);
		String[] splitkey=userString.split("\\|\\|");//其中需要进行符号转义 
		int i=0;
		while (i<splitkey.length){
			String[] splitkey2=splitkey[i].split(",");//切分用户和评分
			arrayListid.add (splitkey2[0]);//切分用户id至 arraylist中
	        i++;
		}
		Iterator<String> iterator = arrayListid.iterator();//当前电影的所有用户的id的迭代器
		while (iterator.hasNext()) {
			String itemsplit = io.getUser_to_item().get(iterator.next());
			String[] splitkeyitem=itemsplit.split("\\|\\|");
			int ii=0;
			while (ii<splitkeyitem.length){
				String[] splitkey3=splitkeyitem[ii].split(",");//再次切割
				if (splitkey3[0]==itemid) {
					continue;//当切割到的是自己本身时退出进行下一次循环 
				}
				userid.put(splitkey3[0],Float.parseFloat(splitkey3[1]));//看过这个输入电影的所有邻居电影集合
				ii++;
			}
		}
		return userid;//返回电影集合  下一步进行相似度比较   以电影的id进行相似度比较
	}
	public ArrayList<Float> SpListuser(String itemString) {
		ArrayList<Float> userList = new ArrayList<Float>();
		String[] splitkeyitem=itemString.split("\\|\\|");
		int ii=0;
		while (ii<splitkeyitem.length){
			String[] splitkey3=splitkeyitem[ii].split(",");//再次切割
			userList.add(Float.parseFloat(splitkey3[1]));//看过这个电影的所有用户的评分集合
			ii++;
		}
		return userList;
	}
	float sim(ArrayList<Float> va, ArrayList<Float> vb)
	{
		
		// 如果向量维度不相等，则不能计算，函数退出
		if (va.size() != vb.size()) //维度不相等进行补零操作
		{
			if (va.size()>vb.size()) {
				while (va.size()>vb.size()) {
					vb.add((float) 0);					
				}
			}
			if (va.size()<vb.size()) {
				while (va.size()<vb.size()) {
					va.add((float) 0);					
				}
			}
		}
		int size = va.size();
		float simVal = 0;
		//sim(va,vb) = (va * vb) / (|va| * |vb|)
		// 分子 = va.get(0)*vb.get(0) + va.get(1)*vb.get(1) +...+ va.get(size - 1)*vb.get(size - 1)
		// 分母 = va的模 * vb的模 = sqrt((va.get(0))的平方 + (va.get(1))的平方 + ... + va.get(size - 1)的平方) * sqrt((vb.get(0))的平方 + (vb.get(1))的平方 + ... + vb.get(size - 1)的平方)
		float num = 0;// numerator分子
		float den = 1;// denominator分母
		float denva = 0;
		float denvb = 0;
		for (int i = 0; i < size; i++) {
			num = num+((float)va.get(i))*((float)vb.get(i));
			denva =(float)(denva+ Math.pow((float) va.get(i), 2));
			denvb =(float)(denvb+ Math.pow((float) vb.get(i), 2));
		}
		den = (float) (Math.sqrt(denva)*Math.sqrt(denvb));
		simVal = num / den;
		return simVal;
	}
}
