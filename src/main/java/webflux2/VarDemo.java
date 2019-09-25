package webflux2;

import java.util.function.Function;

/**
 * 变量引用
 * @author Administrator
 *
 */
public class VarDemo {

	public static void main(String[] args) {
		//java是值传递, 为保证数据的一致性, 在内部类执行时要用final修饰.
		final String str = "sssss";
		Function<String, String> function = s -> s +str;
		String apply = function.apply("kk");
		System.out.println(apply);
	}
}
