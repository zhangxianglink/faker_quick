package webflux2;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * 方法引用
 * @author Administrator
 *
 */
class Dog{
	//jdk会默认将实例传入,非静态方法
		public int eat(Dog this,int i) {
			return i +1;
		}
	}
public class MethodRefrenceDemo {

	public static void main(String[] args) {
		String[] arrs = {"as","df","e","s","sdf"};
		//静态方法的引用
		Arrays.asList(arrs).forEach(System.out::println);
		//非静态方法使用对象实例
		Dog dog = new Dog();
		IntUnaryOperator intUnaryOperator = dog::eat;
		int i = intUnaryOperator.applyAsInt(20);
		System.out.println(i);
		//使用类名访问
		BiFunction<Dog, Integer, Integer> biFunction = Dog::eat;
		Integer apply = biFunction.apply(dog, 23);
		System.out.println(apply);
	}
}
