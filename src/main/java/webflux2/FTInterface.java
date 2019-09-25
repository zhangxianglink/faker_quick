package webflux2;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

import com.example.demo.pojo.Boy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class FTInterface {
	
	private int like;
	private String name;
	
	public static void main(String[] args) {
		//断言接口
		IntPredicate predicate = i -> i > 0;
		System.out.println(predicate.test(90));
		//消费函数接口
		Consumer<String> consumer = s -> System.out.println(s);
		consumer.accept("消费一个字符串");
		//提供者
		//此时仍未调用构造函数产生实例对象
		Supplier<Boy> supplier2 = () -> new Boy();
		Supplier<FTInterface> supplier = () -> new FTInterface(5323212,"双色球");
		//调用构造,生成对象
		FTInterface ftInterface = supplier.get();
		System.out.println(ftInterface.toString());
		//输入T,返回R
		Function<String, Integer>  f = s ->  Integer.valueOf(s);
		System.out.println(f.apply("12"));
		//两个参数
		BiFunction<String, String, Integer> b = (s1,s2) -> Integer.compare(s1.length(), s2.length());
		Integer apply = b.apply("s", "kk");
		System.out.println(apply);
	}
}
