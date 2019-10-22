package com.company.hw3.map_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MapMain {

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("040411214", "�޶��");
		map.put("040411215", "��С��");
		map.put("040411216", "��С��");
		map.put("040411217", "��С��");

		String key = "040411217";
		String value = null;
		if (map.containsKey(key))
		{
			value = (String) map.get(key);
			System.out.println("Use key:" + key + " find value:" + value);
		}
		else
		{
			System.out.println("Does not contain key:" + key);
		}
		
		key = "040411218";
		if (map.containsKey(key))
		{
			value = (String) map.get(key);
			System.out.println("Use key:" + key + " find value:" + value);
		}
		else
		{
			System.out.println("Does not contain key:" + key);
			value = (String) map.get(key);
			System.out.println("Use key:" + key + " find value:" + value + " If map does not contain key, it will return null, be carefull.");
		}
		
		// ���������±�ĸ��������Ƶģ���Щ���ݽṹû���±���������磬������ϣӳ�䣩�������Ƿ�����Щ���ݽṹ��Ԫ��ʱ����Ҫʹ�õ�����iterator�������±�index��
		// Map.Entry����map����Ŀ��һ����Ŀ��һ��key-value��
		// Iterator.next()��������ʹ�������ƶ���map����һ����Ԫ����
        // Entry.getKey()�����������ӵ�ǰkey-value���л�ȡkey
        // Entry.getValue()�����ӵ�ǰkey-value���л�ȡvalue
		Iterator it = map.entrySet().iterator();
		Map.Entry entry = (Map.Entry) it.next();
        key=(String) entry.getKey();
        value=(String) entry.getValue();
        System.out.println("Use iterator to get the first entry is Key :"+key+"  Value :"+value);
        
        // ʹ�������ƶ���map����һ����Ԫ����
		entry = (Map.Entry) it.next();
        key=(String) entry.getKey();
        value=(String) entry.getValue();
        System.out.println("Use iterator to get the second entry is Key :"+key+"  Value :"+value);
	}

}
