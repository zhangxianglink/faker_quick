package webflux3_stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 并行流的使用
 * @author Administrator
 *
 */
public class StreamDemo4 {
	public static void main(String[] args) {
		//并行流
//		IntStream.range(0, 100).parallel().peek(StreamDemo4::doing).count();
		//parallel / sequential 多次调用以最后一次为准
//		IntStream.range(0, 100).parallel().peek(StreamDemo4::doing).sequential().peek(StreamDemo4::doing2).count();
		//ForkJoinPool.commonPool jdk使用当前机器cpu个数
//		IntStream.range(0, 100).parallel().peek(StreamDemo4::doing).count();
		//自定义线程,防止阻塞 
		ForkJoinPool pool = new ForkJoinPool(8);
		pool.submit(() -> IntStream.range(0, 100).parallel().peek(StreamDemo4::doing).count());
		pool.shutdown();
		//ForkJoinPool 是守护线程需要延长主线程时间
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void doing(int i){
		System.out.println(Thread.currentThread().getName()+"debug"+":"+i);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void doing2(int i){
		System.err.println("debug2"+":"+i);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
