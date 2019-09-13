package threads.two;

import org.junit.Test;

public class InterruptThread {
	/**
	 * interrupt() 中断线程, 只是提供了一个提示信息, 如何退出自己决定 , 如果立即无条件退出等同于stop()
	 * isInterrupted() 判断是否中断
	 * interrupted() 判断是否中断,并且清除中断状态
	 * @throws InterruptedException 
	 */

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread() {
			@Override
			public void run() {
			    while (Thread.currentThread().isInterrupted()) {
					System.out.println("exit interrupt");
					break;
				}
			    Thread.yield();
			}
		};
		thread.start();
		thread.interrupt();
	}
	
	/**
	 * sleep()遇到中断会抛出异常
	 * @throws InterruptedException 
	 */
	@Test
	public void test2() throws InterruptedException {
		Thread thread = new Thread() {
			@Override
			public void run() {
			    while (true) {
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("interrupted");
						break;
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.out.println("Interrupted in sleep");
						//第二次中断
						Thread.currentThread().interrupt();
					}
					Thread.yield();
				}
			}
		};
		thread.start();
		Thread.sleep(2000);
		//第一次中断
		thread.interrupt();
	}
}
