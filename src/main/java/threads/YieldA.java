package threads;


public class YieldA {
	
	public static class RunCPU extends Thread{
		public  RunCPU(String name) {
			super.setName(name);
		}
		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				System.out.println(Thread.currentThread().getName() + ":  " + i);
				if(i == 30) {
					Thread.yield();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		RunCPU runCPU = new RunCPU("run111111");
		RunCPU runCPU2 = new RunCPU("run222222");
		runCPU.start(); // 到30让出cpu给runCPU2
		runCPU2.start();// 到30让出cpu给runCPU
	}

}
