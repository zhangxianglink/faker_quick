package optimize.myMap;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;

public class GMap {
	
	@Test
	public void show() {
		ArrayListMultimap<String, Integer> create = ArrayListMultimap.create();
		String str = "sdfnlusdfdsscnvzeqcvjabysfsdfadurhfs";
		char[] cs = str.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			create.put(cs[i]+"", i);
		}
		Set<String> set = create.keySet();
		for (String string : set) {
			System.out.println("key="+string+" ,value="+create.get(string) + " size="+create.get(string).size());
		}
	}

}
