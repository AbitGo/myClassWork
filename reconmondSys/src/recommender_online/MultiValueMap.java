package recommender_online;

import java.util.*;

public interface MultiValueMap<K, V> {

    void add(K key, V value);
    void add(K key, List<V> values);
    void set(K key, V value);
    void set(K key, List<V> values);
    void set(Map<K, List<V>> values);
    List<V> remove(K key);
    void clear();
    Set<K> keySet();
    List<V> values();
    V getValue(K key, int index);
    List<V> getValues(K key);
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
}
