package optimize;

public class AddString {
	
	/**
	 * 构建超大字符串优先使用 StringBuilder/StringBuffer , 不考虑线程安全优先使用StringBuilder
	 * @param args
	 */
	public static void main(String[] args) {
		AddString addString = new AddString();
		addString.test1();
		addString.test2();
		addString.test3();
	}
	
	
	public void test1() {
		String str = "";
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			str = str + i;
		}
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2 -currentTimeMillis);
	}
	
	public void test2() {
		String str = "";
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			str = str.concat(String.valueOf(i));
		}
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2 -currentTimeMillis);
	}
	
	public void test3() {
		//如果可以预先评估容积,可以进一步提升性能
		StringBuilder sb = new StringBuilder();
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			sb.append(i);
		}
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2 -currentTimeMillis);
	}

}
