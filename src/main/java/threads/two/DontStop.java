package threads.two;

/**
 * stop方法简单粗暴,但是如果写入数据时强行停止就会导致数据异常.
 * @author Administrator
 *
 */
public class DontStop {
	public static User u = new User();
	public static class User {
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}
		private int id ;
		private String name;
		public User() {
			this.id = 0;
			this.name = "0";
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static class createUser extends Thread{
		//合理退出
		volatile boolean stopme = false;
		public void stopme() {
			stopme =true;
		}
		@Override
		public void run() {
			while (true) {
				if(stopme) {
					System.out.println("exit by stop me");
					break;
				}
				synchronized (u) {
					int ms = (int)(System.currentTimeMillis()/1000) ;
					u.setId(ms);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					u.setName(String.valueOf(ms));	
				}
				Thread.yield();
			}
		}
	}
	
	public static class readUser extends Thread{
		@Override
		public void run() {
			while (true) {
				synchronized (u) {
					if (u.getId() != Integer.parseInt(u.getName())) {
						System.out.println(u.toString());
					}
				}
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new readUser().start();
		while (true) {
			createUser t = new createUser();
			t.start();
			Thread.sleep(200);
			//多个线程交替为user赋值, 一旦被打断id != name
//			t.stop();
			t.stopme();
			
		}
	}

}
