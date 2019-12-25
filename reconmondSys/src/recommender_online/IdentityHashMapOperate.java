package recommender_online;

import java.util.ArrayList;
import java.util.IdentityHashMap;

public class IdentityHashMapOperate
{
    public static IdentityHashMap<String, Object> addValue(IdentityHashMap<String, Object> map, String key, Object value)
    {
        if(map.get("sort") == null)//没有顺序控制key
        {
            map.put("sort", key);//直接增加顺序控制key
            map.put(key, value);
        }
        else
        {
              String sort = map.get("sort").toString();//取出顺序控制key
              sort+= "^"+key;//需要控制顺序的key
              map.put("sort", sort);//回写到map
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
