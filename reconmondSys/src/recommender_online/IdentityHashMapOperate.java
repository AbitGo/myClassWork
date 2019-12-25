package recommender_online;

import java.util.ArrayList;
import java.util.IdentityHashMap;

public class IdentityHashMapOperate
{
    public static IdentityHashMap<String, Object> addValue(IdentityHashMap<String, Object> map, String key, Object value)
    {
        if(map.get("sort") == null)//û��˳�����key
        {
            map.put("sort", key);//ֱ������˳�����key
            map.put(key, value);
        }
        else
        {
              String sort = map.get("sort").toString();//ȡ��˳�����key
              sort+= "^"+key;//��Ҫ����˳���key
              map.put("sort", sort);//��д��map
              map.put(key, value);
        }

        return map;
    }

    public static ArrayList<Object> getValues(IdentityHashMap<?, ?> map)
    {
        String sort = map.get("sort").toString();
        ArrayList<Object> values = new ArrayList<Object>();

        if(sort != null)
        { 
            String[] sortKeys = sort.split("\\^");

            for(int i = 0; i < sortKeys.length; i++)
            {
                String key = sortKeys[i];
                Object value = map.get(key.intern());
                values.add(value);
            }
        }
        return values;
    }
}
