package pool;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MyThread implements Runnable{
	
	protected String name;

	@Override
	public void run() {
		System.out.println(name);
	}

}
