package pool;

import java.util.List;
import java.util.Vector;

public class ZThreadPool {
	
	private static ZThreadPool instance = null;
	
	//空闲的线程队列
	private List<PThread> idleThreads;
	//线程计数器
	private int threadCounter;
	private boolean isShutDown = false;
	
	private ZThreadPool() {
		this.idleThreads = new Vector(5);
		threadCounter = 0;
	}
	
	public int getCreatedThreadCount() {
		return threadCounter;
	}
	
	
	// 取得线程的实例
	public synchronized static ZThreadPool getInstance() {
		if (instance == null) 
			instance = new ZThreadPool();
		return instance;
	}
	
	//将线程放入池中
	protected synchronized void repool(PThread pThread) {
		if (!isShutDown) {
			idleThreads.add(pThread);
		}else {
			pThread.shutDown();
		}
	}
	
	//停掉线程池里面的所有线程
	public synchronized void shutdown() {
		isShutDown = true;
		for (int i = 0; i < idleThreads.size(); i++) {
			PThread pThread = idleThreads.get(i);
			pThread.shutDown();
		}
	}
	
	//执行任务
	public synchronized void start(Runnable runnable) {
		PThread pThread = null;
		//如果有空线程,直接使用
		if(idleThreads.size() > 0) {
			int lastIndex = idleThreads.size() -1 ;
			 pThread = idleThreads.get(lastIndex);
			 idleThreads.remove(lastIndex);
			 //执行任务
			 pThread.setTarget(runnable);
		}else {
			threadCounter++;
			pThread = new PThread(runnable, "Pthread#" + threadCounter, this);
			pThread.start();
		}
	}
	
	

}
