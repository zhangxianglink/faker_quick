package webflux2;

public class Thread2_3 {

	public static void main(String[] args) {
		Runnable target = () -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("天生我材必有用");
			}
		};
		new Thread(target).start();
	}
}
