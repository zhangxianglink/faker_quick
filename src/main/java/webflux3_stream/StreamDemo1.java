package webflux3_stream;

import java.util.stream.IntStream;

public class StreamDemo1 {
	
	public static void main(String[] args) {
		int[] stream = {1,2,3};
		/*
		 * sum()终止操作
		 * map()中间操作
		 * 所谓惰性求值: 终止操作没有进行调用,中间操作不会进行执行
		 */
		int sum = IntStream.of(stream).parallel().map(StreamDemo1::doubleHappy).sum();
		System.out.println(sum);
	}
	
	public static int doubleHappy(int i) {
		System.out.println(i+"乘以2");
		return i * 2;
	}
}
