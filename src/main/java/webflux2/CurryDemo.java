package webflux2;

import java.util.function.Function;

/**
 * 级联表达式和柯里化
 * @author Administrator
 *
 */
public class CurryDemo {
	
	public static void main(String[] args) {
		//级联表达式
		Function<Integer, Function<Integer, Integer>> fim =  x -> y -> x + y;
		//柯里化:函数标准化, 将多个参数转化为一个函数
		Integer apply = fim.apply(2).apply(3);
		System.out.println(apply);
	}

}
