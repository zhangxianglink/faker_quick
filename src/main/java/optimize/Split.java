package optimize;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.junit.Test;

public class Split {
	
	public String getString() {
		String str = null;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 1000; i++) {
			stringBuffer.append(i);
			stringBuffer.append(";");
		}
		return str = stringBuffer.toString();
	}
	
	/**
	 * split方法分割字符串, 性能太慢,
	 */
	@Test
	public void test1() {
		String str = getString();
		Instant now = Instant.now();
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			str.split("\\.");
		}
		Instant end = Instant.now();
		Duration between = Duration.between(now, end);
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2 -currentTimeMillis);
		System.out.println(between.toMillis());
	}

	/**
	 * 效率更高的StringTokenizer
	 */
	@Test
	public void test2() {
		String str = getString();
		StringTokenizer tokenizer = new StringTokenizer(str, ".");
		long currentTimeMillis = System.currentTimeMillis();
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			while (tokenizer.hasMoreTokens()) {
	//			System.out.println(tokenizer.nextToken());
				list.add(tokenizer.nextToken());
			}
			tokenizer = new StringTokenizer(str, ".");
			list.clear();
		}
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2 -currentTimeMillis);
	}
	
	
	/**
	 * 自定义分割法,核心indexof(), substring时间换空间
	 */
	@Test
	public void test3() {
		String string = getString();
		String str = string;
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			while(true) {
				String substring =null;
				int indexOf = str.indexOf(";");
				if(indexOf<0)break;
				substring = str.substring(0, indexOf);
				str = str.substring(indexOf+1);
			}
			str = string;
		}
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2 -currentTimeMillis);
	}
	
}
