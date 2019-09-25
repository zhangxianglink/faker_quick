package webflux3_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStream {
	
	public static void main(String[] args) {
		//集合
		List<Object> list = new ArrayList<>();
		list.stream();
		//数组形式
		Arrays.asList(1).stream();
		//数字
		IntStream.of(1,2,3);
		//无限流
		new Random().ints().limit(10).forEach(System.out::println);
		//提供者
		Random random =new Random();
		Stream.generate(() -> random.nextInt()).limit(10);
		Stream.generate(() -> 1);
	}
}
