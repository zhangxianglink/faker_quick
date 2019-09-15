package threads.two;

public class Sync implements Runnable {
	private static Sync sync = new Sync();

	public volatile static int i = 1;

	public  synchronized void instance() {
		i++;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			instance();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(sync);
		Thread t2 = new Thread(sync);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
