package recommender_online;

import java.util.*;

public class Test {
	public static void main(String[] args) throws Exception {
		SimTabe io = new SimTabe();
		io.readFile("simtable.dat");//��ȡ���ƶ��б�
		System.out.println("Inputed Movie:");
		String movalue = io.SimTabeMap("1");//���ҵ�Ӱ1�����ƶ�   ����ӡ����Ӧ�ĵ�Ӱ
		ArrayList<String> item= io.SimTabeSort(movalue);//��ȡ�����ƶ��б�
		
		System.out.println("Recommender movies:");
		io.readitem("movies.dat",item);//ִ�ж�ȡ�ļ� �е�Ӱ����    ��ȡ��Ӱ��ֵ�Ե��ļ�
 
	}
}
