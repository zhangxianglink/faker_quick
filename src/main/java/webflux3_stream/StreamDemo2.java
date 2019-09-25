package webflux3_stream;

import java.util.stream.Stream;

/**
 * 
 * 中间操作
 * @author Administrator
 *
 */
public class StreamDemo2 {
	public static void main(String[] args) {
		String str = "this is I want to do!";
		Stream.of(str.split(" ")).filter(s -> s.length() > 2)
		.map(s -> s.length()).forEach(System.out::println);
		//flatMap, A --> B(集合)
		//最终获得的是A元素中所有B元素的集合
		//IntStream 不是  Stream的子类 ,需要使用boxed()装箱
		Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed())
		.forEach(i -> System.out.println((char)i.intValue()));
		//peek用于debug是个中间操作, foreach是终止操作
		System.out.println("peek----------------------");
		Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);
		//skip 忽略几个
		Stream.of(str.split(" "))
		.map(s -> s.length()).skip(2).forEach(System.out::println);
	}
}
