package pool;



public class PThread extends Thread{
	
	//线程池
	private ZThreadPool pool;
	//任务
	private Runnable runnable;
	private boolean isShutDown = false;
	private boolean idIdle = false;

	public PThread(Runnable runnable, String name, ZThreadPool zThreadPool) {
		super(name);
		this.pool = zThreadPool;
		this.runnable = runnable;
	}
	
	public Runnable getRunnable() {
		return runnable;
	}
	
	public boolean getIdle() {
		return idIdle;
	}

	public void shutDown() {
		isShutDown = true;
		notifyAll();
	}

	public synchronized void setTarget(Runnable newRunnable) {
		runnable = newRunnable;
		// 通知run方法执行
		notifyAll();
	}


	public void run() {
		//只要不是 手动关闭,就一直不结束
		while(!isShutDown) {
			idIdle = false;
			if(runnable != null) {
				//运行任务
				runnable.run();
			}
			idIdle = true;
			try {
				//任务结束,放回线程池
				pool.repool(this);
				synchronized (this) {
					// 空闲
					wait();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			idIdle = false;
		}
	}
}
