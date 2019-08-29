package optimize;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import com.google.common.collect.Maps;

public class ListAndMap {
	
	/**
	 * 三种list的实现, ArrayList, Vector, LinkedList
	 */
	@Test
	public void Test() {
		// ArrayList, Vector 是基于数组实现的
		// LinkedList是基于循环双向链表实现的
	}

	/**
	 * HashMap 和 HashTable
	 */
	@Test
	public void test2() {
		//非线程安全的
		//key,value可以为null
		HashMap<Object, Object> hashMap = Maps.newHashMap();
		hashMap.put(null, null);
        //大部分方法做了同步
		//34空指针异常
		Hashtable<Object, Object> hashtable = new Hashtable<>();
		hashtable.put(null, null);
	}
	
	/**
	 *  HashMap 的实现:  把key做hash算法, 然后把hash值映射到内存地址
	 *        底层的数据结构使用数组.
	 *  LinkedHashMap在内部增加了一个链表,用于存放元素的顺序, 提供元素进入顺序 OR 访问顺序
	 */
	@Test
	public void AboutTreeMap() {
		TreeMap<Integer, String> treeMap = new TreeMap<>(Comparator.reverseOrder()); //倒序排列
		treeMap.put(4, "四");
		treeMap.put(2, "二");
		treeMap.put(3, "三");
		treeMap.put(1, "一");
		treeMap.put(5, "五");
		// key默认自然排序 , 构造注入Comparator进行影响
		System.out.println(treeMap.lastKey());//返回该TreeMap的最后一个（最大的）映射的key
		System.out.println(treeMap.containsKey(1)); //判断该TreeMap中是否包含指定key的映射
		System.out.println("----------------------");
		SortedMap<Integer, String> map = treeMap.subMap(4, 2);
		for (Integer key : map.keySet()) {
			System.out.println(key+":"+map.get(key));
		}
		System.out.println("----------------------");
		SortedMap<Integer, String> map2 = treeMap.headMap(3); // <
		for (Integer key : map2.keySet()) {
			System.out.println(key+":"+map2.get(key));
		}
		System.out.println("----------------------");
		SortedMap<Integer, String> map3 = treeMap.tailMap(3); // >=
		for (Integer key : map3.keySet()) {
			System.out.println(key+":"+map3.get(key));
		}
		//方式2是使用一个实现了Comparator接口的Key
	}
}
