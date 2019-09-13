package threads.two;

/**
 *      线程组是将线程分类
 * @author Administrator
 *
 */
public class ThreadGroupName implements Runnable{

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		String name = thread.getThreadGroup().getName()+" :  "+thread.getName();
		while(true) {
			System.out.println("S9世界赛" + name);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadGroup group = new ThreadGroup("线程组");
		Thread thread1 = new  Thread(group, new ThreadGroupName(), "skt");
		Thread thread2 = new  Thread(group, new ThreadGroupName(), "fpx");
		thread1.start();
		thread2.start();
		System.out.println("活动的线程:"+group.activeCount()); //此为预估值,仅供参考
		group.list();
	}
}
