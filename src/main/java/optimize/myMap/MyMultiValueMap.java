package optimize.myMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MyMultiValueMap<K,V> {

    /**
     * add : one key to one value
     */
    void add(K key, V value);

    /**
     * add : one key to more value
     */
    void add(K key, List<V> values);

    /**
     * set : one key to one value
     */
    void set(K key, V value);

    /**
     * set : one key to more value
     */
    void set(K key, List<V> values);

    /**
     * exchange key - list<value>
     */
    void set(Map<K,List<V>> map);

    /**
     * remove : key and value
     */
    List<V> remove(K key);

    /**
     * clear key - list<value>
     */
    void clear();

    /**
     * get keys
     */
    Set<K> keySet();
    /**
     * get keys
     */
    List<V> getValues(K key);

    /**
     * get all values
     */
    List<V> values();

    int size();

    boolean isEmpty();

    /**
     * contain key
     */
    boolean containsKey(K key);


}
