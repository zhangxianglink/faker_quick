package threads.two;

public class PrioRityThread {
	
	public static class LowThread extends Thread{
		int count =0;
		@Override
		public void run() {
			while(true) {
				synchronized (PrioRityThread.class) {
					count++;
					if(count > 1000000) {
						System.out.println("LowThread count: " + count);
						break;
					}
				}
			}
		}
	}
	
	public static class HeiThread extends Thread{
		int count =0;
		@Override
		public void run() {
			while(true) {
				synchronized (PrioRityThread.class) {
					count++;
					if(count > 1000000) {
						System.out.println("HeiThread count: " + count);
						break;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		LowThread lowThread = new LowThread();
		HeiThread heiThread = new HeiThread();
		//优先级从一到十, 优先级调度和底层操作系统有密切关系, 高优先级并不一定先执行!
		lowThread.setPriority(Thread.MIN_PRIORITY);
		heiThread.setPriority(Thread.MAX_PRIORITY);
		lowThread.start();
		heiThread.start();
	}

}
