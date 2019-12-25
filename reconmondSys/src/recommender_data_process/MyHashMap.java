package recommender_data_process;

import java.util.HashMap;

public class MyHashMap<K> extends HashMap<K,String> {
	private static final long serialVersionUID = 1L;
	    @Override
	    public String put(K key, String value) {
	        String newV = value;
	        if (containsKey(key)) {
	            String oldV = get(key);
	            newV =oldV+"||"+ newV;
	        }
	        return super.put(key, newV);
	    }

}
