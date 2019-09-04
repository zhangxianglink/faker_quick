package optimize;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class AboutSet {
	
	/**
	 * HashSet输出无规律
	 * LinkedHashSet输出输入顺序完全一致
	 * TreeSet自然元素排序
	 */
	@Test
	public void test() {
		//优化访问代码
		String[] str = {"north1","north2","north6","north4","north8","north0","north34","rey1"}; 
		List<String> list = Arrays.asList(str);
		// 分离循环中被重复调用的代码
		int size = list.size();
		int count = 0;
		String s = null;
		for (int i = 0; i < size; i++) {
			//省略相同操作
			if(((s = list.get(i)).indexOf("north") != -1) ||
					(s.indexOf("re") != -1) ) {
				count ++;
			}
		}
		System.out.println(count);
		// 不影响可读性的前提下,减少方法调用
	}

}
