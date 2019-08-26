package com.example.demo.lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

public class Test1 {
	
	@Test
	public void hunger(){
		Comparator<String> comparator = ( a,  b) -> 
			Integer.compare(a.length(), b.length());
		int compare = comparator.compare("aaa", "b");
	}

	@Test
	public void test2(){
		//函数式接口
		String[] a = {};
		Arrays.sort(a,String::compareToIgnoreCase);
		//default方法, Iterable接口
	    ArrayList<Object> list = new ArrayList<>();
	    list.forEach(System.out::println);
	}
	
	@Test
	public void test3(){
		List<String> list = Arrays.asList("aa","bbbbb","ccccccccc","sddddddddddddddddddd","yyyyyy");
		long count = list.stream().filter(e -> e.length() >9).count();
		System.out.println(count);
	}
	
	@Test
	public void create(){
		//静态方法创建
		Stream<String> stream = Stream.of("a","b","c");
		//将数组转化为stream
		String[] arrs = {};
		Stream<String> stream2 = Arrays.stream(arrs);
		//创建一个没有元素的stream
		Stream<String> empty = Stream.<String>empty();
		//创建一个有常数值的Stream
		Stream<String> generate = Stream.generate(() -> "jack");
		//创建一个有随机数的Stream
		Stream<Double> generate2 = Stream.generate(() -> Math.random());
		Stream<Double> generate3 = Stream.generate(Math::random);
		
		//filter过滤器转化, 能够产生一个新的Stream, 里面的元素符合某个特殊条件
		List<String> list = Arrays.asList("aa","bbbbb","cccccAAccc","sddddddddddddddddddd","yyyyyy");
		long count = list.stream().filter(e -> e.length() >9).count();
		//map 对Stream里面的值进行转化, 会对元素里面每一个元素应用函数
		list.stream().map(String::toLowerCase);
		list.stream().map(e -> e.toLowerCase());
		//flatMap
		list.stream().flatMap(e -> Test1.getString(e)).forEach(w -> System.out.println(w+","));
	}
	
	@Test
	public void sonStream(){
		Integer[] arr = {1,2,3,4,5,6,7,8,9,10,11};
		long count = Stream.of(arr).filter(a -> a>1).count();
		System.out.println(count);
		//获取指定长度的Stream
	    Stream.of(arr).limit(3).forEach(System.out::println);
		//忽略x个元素,获取之后所有元素
	    Stream.of(arr).skip(3).forEach(System.out::println);
	    //将两个流连接到一起
	    List<String> arrs = Arrays.asList("Hello","world");
		arrs.stream().flatMap(e -> getString(e)).forEach(w -> System.out.print(w+","));
		Stream.concat(getString("aaa"), getString("bbbb")).forEach(System.out::print);;
		//stream去重
		Stream<String> distinct = Stream.of("a","b","a","c").distinct();
	}
	
	public static Stream<Character> getString(String a){
		ArrayList<Character> list = new ArrayList<>();
		for (Character character : a.toCharArray()) {
			list.add(character);
		}
		return list.stream();
	}
	
	/**
	 * 聚合方法的使用
	 */
	@Test
	public void aggregate(){
		List<String> list = Arrays.asList("kpan","zxxs","zzz","six","li");
		List<Integer> list2 = Arrays.asList(1,2,3,4,5,7,6);
		//count()
		long count = list.stream().count();
		//max,min
		Optional<String> max = list.stream().max(String::compareToIgnoreCase);
		if(max.isPresent()){
			System.out.println("最大的值:"+max.get());
		}
		Optional<Integer> min = list2.stream().min((a,b) -> Integer.compare(a, b));
		if(min.isPresent()){
			System.out.println("最小的值:"+min.get());
		}
		//filter + findFirst/findAny
		//在串行的流中，findAny和findFirst返回的，都是第一个对象；而在并行的流中，findAny返回的是最快处理完的那个线程的数据
		Optional<String> findFirst = list.stream().filter(e -> e.startsWith("z")).findFirst();
		System.out.println(findFirst.get());
		Optional<String> findAny = list.stream().filter(e -> e.startsWith("z")).findAny();
		//findAny2 : zxxs OR zzz
		Optional<String> findAny2 = list.parallelStream().filter(e -> e.startsWith("z")).findAny();
		System.out.println(findAny.get());
		//anyMatch , 通过并行提升速度
		boolean anyMatch = list.stream().parallel().anyMatch(s -> s.contains("s"));
	}

}
