package threads.two;

/**
 *  s守护线程, 例如垃圾回收, jit等线程
 * @author Administrator
 *
 */
public class DaemonThread {
	
	public static class myThread extends Thread{
		@Override
		public void run() {
			while (true) {
				System.out.println("Daemon , I am runing !");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * y下面这个例子中, t1是守护线程, main是主线程, 当主线程运行完毕,整个程序自然结束.
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		myThread t1 = new myThread();
		t1.setDaemon(true); // 必须在start()之前设置, 不然当作工作线程去执行
		t1.start();
		Thread.sleep(2000);
	}
}
