package com.company.hw3.map_java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String,Integer> param1 = new HashMap<>();
        param1.put("Lucy",new Integer(5));
        param1.put("Lily",new Integer(4));
        param1.put("Han Meime",new Integer(5));
        param1.put("Jim",new Integer(3));
        param1.put("Zou Encen ",new Integer(3));
        param1.put("WeiHaiTao ",new Integer(0));


        String key = "DaHuaiDan";
        int value;
        if (param1.containsKey(key))
        {
            value = param1.get(key);
            System.out.println("Use key:" + key + " find value:" + value);
        }
        else
        {
            System.out.println("Does not contain key:" + key);
        }

        key = "Tom";
        if (param1.containsKey(key))
        {
            value = param1.get(key);
            System.out.println("Use key:" + key + " find value:" + value);
        }
        else
        {
            System.out.println("Does not contain key:" + key);
        }

        Iterator it = param1.entrySet().iterator();

        //将param的key作为迭代器迭代
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            key=(String) entry.getKey();
            value = param1.get(key);
            System.out.println("Use iterator to get the first entry is Key :"+key+"  Value :"+value);
        }
    }

}
