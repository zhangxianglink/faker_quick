package optimize.myMap;

import java.util.*;

public class MyLinkedMultiValueMap<K,V> implements MyMultiValueMap<K,V> {

    /**
     * 数据源
     */
    protected   Map<K , List<V>> mSource = new LinkedHashMap<K, List<V>>();

    public MyLinkedMultiValueMap() {
    }

    @Override
    public void add(K key, V value) {
        if (key != null){
            //如果key存在,就继续添加value
            if (!mSource.containsKey(key)) {
                mSource.put(key, new ArrayList<V>(2));
            }
            mSource.get(key).add(value);
        }
    }

    @Override
    public void add(K key, List<V> values) {
        // 便利添加进来的List的Value，调用上面的add(K, V)方法添加
        for (V value : values) {
            add(key, value);
        }
    }

    @Override
    public void set(K key, V value) {
        mSource.remove(key);
        add(key,value);
    }

    @Override
    public void set(K key, List<V> values) {
        mSource.remove(key);
        add(key,values);
    }

    @Override
    public List<V> remove(K key) {
        return mSource.remove(key);
    }

    @Override
    public void clear() {
        mSource.clear();
    }

    @Override
    public Set<K> keySet() {
        return mSource.keySet();
    }

    @Override
    public List<V> getValues(K key) {
        return mSource.get(key);
    }

    @Override
    public List<V> values() {
        ArrayList<V> list = new ArrayList<>();
        for (K k : mSource.keySet()) {
            list.addAll(mSource.get(k));
        }
        return list;
    }

    @Override
    public int size() {
        return mSource.size();
    }

    @Override
    public boolean isEmpty() {
        return mSource.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        return mSource.containsKey(key);
    }

    @Override
    public void set(Map<K,List<V>> map) {
        // 移除所有值，便利Map里的所有值添加进来
        mSource.clear();
        mSource.putAll(map);
    }
}
