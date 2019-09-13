package threads.two;

public class JoinA {
	
	private volatile static int  i = 0;
	
	public static class Nums extends Thread{
		@Override
		public void run() {
			for ( i = 0; i < 10000999; i++);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Nums nums = new Nums(); // 选择目标线程对象, 而不是Thread
		nums.start(); //0
		nums.join(); //10000999
		System.out.println(i);
	}

}
