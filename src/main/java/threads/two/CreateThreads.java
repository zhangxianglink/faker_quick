package threads.two;

import java.util.Vector;

import org.junit.Test;

public class CreateThreads implements Runnable{
	
	@Test
	public void method1() {
		Thread t1  = new Thread() {
			@Override
			public void run() {
				System.out.println("I am fine!");
			}
		};
		t1.start();
	}
	

	@Override
	public void run() {
		System.out.println("I am runnable");
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new CreateThreads());
		thread.start();
	}
}
