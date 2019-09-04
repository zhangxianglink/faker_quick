package pool;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

public class ThreadPoolTest {
	
	/**
	 * 不使用线程池
	 */
	@Test
	public void test1() {
		Instant now = Instant.now();
		for (int i = 0; i < 1000; i++) {
			new Thread(new MyThread("No ThreadPool" + i)).start();
		}
		Instant end = Instant.now();
		System.out.println(Duration.between(now, end).toMillis());
	}
	
	@Test
	public void test2() {
		Instant now = Instant.now();
		for (int i = 0; i < 1000; i++) {
			ZThreadPool.getInstance().start(new MyThread("Use ThreadPool" + i));
		}
		Instant end = Instant.now();
		System.out.println(Duration.between(now, end).toMillis());
	}
	

}
