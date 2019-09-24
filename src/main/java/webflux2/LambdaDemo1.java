package webflux2;

@FunctionalInterface
interface Interface1{
	//只能有一个抽象方法方法
	public abstract int doubleHappy(int i);
	//可以有default 方法
	default int doubleNum(int x, int y) {
		y = this.doubleHappy(y);
		return x * y;
	}
}

@FunctionalInterface
interface Interface2{
	public abstract int doubleHappy(int i);
	default int doubleNum(int x, int y) {
		y = this.doubleHappy(y);
		return x * y;
	}
}

@FunctionalInterface
interface Interface3 extends Interface1 ,Interface2 {

	//注意实现的具体接口
	@Override
	default int doubleNum(int x, int y) {
		// TODO Auto-generated method stub
		return Interface2.super.doubleNum(x, y);
	}
	
}

public class LambdaDemo1 {
	public static void main(String[] args) {
		// 函数式编程就是返回一个 函数接口对象实例
		Interface1 i1 = (i) -> {
			return i + i;
		};
		int num = i1.doubleNum(2, 9);
		System.out.println(num);
	}
}
