package recommender_data_process;

import java.util.*;
import java.util.Map.Entry;

public class Recommend {
	public HashMap<String,Float> neighbour(String itemid,FileIO io) {//�ھ��㷨 ��ȡ����Ӱid���ھ��û�id  ���ݵ�Ӱid�������Ƶ��ھ��û� 
		String userString = "";
		ArrayList<String> arrayListid = new ArrayList<String>();//���иõ�Ӱ���ھ��û�id
		ArrayList<String> neighbourid = new ArrayList<String>();//���иõ�Ӱ���ھӼ���
		HashMap<String,Float> userid = new HashMap<String,Float>();
		userString = io.getItem_to_user().get(itemid);
		String[] splitkey=userString.split("\\|\\|");//������Ҫ���з���ת�� 
		int i=0;
		while (i<splitkey.length){
			String[] splitkey2=splitkey[i].split(",");//�з��û�������
			arrayListid.add (splitkey2[0]);//�з��û�id�� arraylist��
	        i++;
		}
		Iterator<String> iterator = arrayListid.iterator();//��ǰ��Ӱ�������û���id�ĵ�����
		while (iterator.hasNext()) {
			String itemsplit = io.getUser_to_item().get(iterator.next());
			String[] splitkeyitem=itemsplit.split("\\|\\|");
			int ii=0;
			while (ii<splitkeyitem.length){
				String[] splitkey3=splitkeyitem[ii].split(",");//�ٴ��и�
				if (splitkey3[0]==itemid) {
					continue;//���и�����Լ�����ʱ�˳�������һ��ѭ�� 
				}
				userid.put(splitkey3[0],Float.parseFloat(splitkey3[1]));//������������Ӱ�������ھӵ�Ӱ����
				ii++;
			}
		}
		return userid;//���ص�Ӱ����  ��һ���������ƶȱȽ�   �Ե�Ӱ��id�������ƶȱȽ�
	}
	public ArrayList<Float> SpListuser(String itemString) {
		ArrayList<Float> userList = new ArrayList<Float>();
		String[] splitkeyitem=itemString.split("\\|\\|");
		int ii=0;
		while (ii<splitkeyitem.length){
			String[] splitkey3=splitkeyitem[ii].split(",");//�ٴ��и�
			userList.add(Float.parseFloat(splitkey3[1]));//���������Ӱ�������û������ּ���
			ii++;
		}
		return userList;
	}
	float sim(ArrayList<Float> va, ArrayList<Float> vb)
	{
		
		// �������ά�Ȳ���ȣ����ܼ��㣬�����˳�
		if (va.size() != vb.size()) //ά�Ȳ���Ƚ��в������
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
		// ���� = va.get(0)*vb.get(0) + va.get(1)*vb.get(1) +...+ va.get(size - 1)*vb.get(size - 1)
		// ��ĸ = va��ģ * vb��ģ = sqrt((va.get(0))��ƽ�� + (va.get(1))��ƽ�� + ... + va.get(size - 1)��ƽ��) * sqrt((vb.get(0))��ƽ�� + (vb.get(1))��ƽ�� + ... + vb.get(size - 1)��ƽ��)
		float num = 0;// numerator����
		float den = 1;// denominator��ĸ
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
