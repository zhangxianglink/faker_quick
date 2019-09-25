package webflux3_stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo3 {
	public static void main(String[] args) {
		//foreachOrdered
		String str = "this is I want to do!!!";
		str.chars().parallel().forEach(i -> System.out.print((char)i));
		System.out.println();
		str.chars().parallel().forEachOrdered(i -> System.out.print((char)i));
		
		//collect 收集数据
		List<String> collect = Stream.of(str.split(" ")).collect(Collectors.toList());
		Object[] array = Stream.of(str.split(" ")).toArray();
		System.out.println(collect);
		//reduse (2元函数)
		Optional<String> reduce = Stream.of(str.split(" ")).reduce((s1,s2) -> s1+"-"+s2);
		System.out.println(reduce.orElse(""));
		//带初始值
		Integer reduce2 = Stream.of(str.split(" ")).map(s -> s.length()).reduce(0,(s1,s2) -> s1 +s2);
		System.out.println(reduce2);
		//min
		Optional<String> min = Stream.of(str.split(" ")).min((s3,s1) -> s1.length() - s3.length());
	    String orElse = min.orElse("");
	    System.out.println(orElse);
	}
}
