package threads.two;

public class WaitAndNotify {
	final static Object ob = new Object();

	public static class T1 extends Thread {
		@Override
		public void run() {
			synchronized (ob) {
				System.out.println(System.currentTimeMillis() + " : " + "T1.start");
				try {
					System.out.println(System.currentTimeMillis() + " : " + "T1.wait");
					ob.wait();//放开锁,停止继续执行
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis() + " : " + "T1.end");
			}
		}
	}

	public static class T2 extends Thread {
		@Override
		public void run() {
			synchronized (ob) {
				System.out.println(System.currentTimeMillis() + " : " + "T2.start");
				ob.notify();// 提示其他进程去获取锁
				System.out.println(System.currentTimeMillis() + " : " + "T2.end");
				try {
					Thread.sleep(2000);//占用锁没有放开.导致t2end比t1end早两秒
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Thread t1 = new T1();
		Thread t2 = new T2();
		t1.start();
		t2.start();
	}
}
