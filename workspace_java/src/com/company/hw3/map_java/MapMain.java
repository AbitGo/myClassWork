package com.company.hw3.map_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MapMain {

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("040411214", "邹恩岑");
		map.put("040411215", "张小虎");
		map.put("040411216", "王小兵");
		map.put("040411217", "李小红");

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
		
		// 迭代器与下标的概念是类似的，有些数据结构没有下标操作（例如，链表，哈希映射），则我们访问这些数据结构的元素时，需要使用迭代器iterator来代替下标index。
		// Map.Entry叫做map的条目，一个条目是一个key-value对
		// Iterator.next()操作，是使迭代器移动到map的下一个下元素上
        // Entry.getKey()方法，用来从当前key-value对中获取key
        // Entry.getValue()用来从当前key-value对中获取value
		Iterator it = map.entrySet().iterator();
		Map.Entry entry = (Map.Entry) it.next();
        key=(String) entry.getKey();
        value=(String) entry.getValue();
        System.out.println("Use iterator to get the first entry is Key :"+key+"  Value :"+value);
        
        // 使迭代器移动到map的下一个下元素上
		entry = (Map.Entry) it.next();
        key=(String) entry.getKey();
        value=(String) entry.getValue();
        System.out.println("Use iterator to get the second entry is Key :"+key+"  Value :"+value);
	}

}
