package recommender_online;

import java.util.*;

public class Test {
	public static void main(String[] args) throws Exception {
		SimTabe io = new SimTabe();
		io.readFile("simtable.dat");//读取相似度列表
		System.out.println("Inputed Movie:");
		String movalue = io.SimTabeMap("1");//查找电影1的相似度   并打印出对应的电影
		ArrayList<String> item= io.SimTabeSort(movalue);//获取的相似度列表
		
		System.out.println("Recommender movies:");
		io.readitem("movies.dat",item);//执行读取文件 中电影名字    读取电影键值对的文件
 
	}
}
