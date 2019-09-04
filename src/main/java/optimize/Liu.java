package optimize;

import java.util.ArrayList;

import org.apache.commons.collections.functors.SwitchClosure;
import org.junit.Test;

public class Liu {

	private static int x = 100;
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("DZ00000001");
		list.add("BD000DZ002");
		list.add("BD00000003");
		list.add("DZ00000005");
		list.add("TBD000000DZ");
		for (int i= 0 ; i<list.size() ; i++) {
			String str = list.get(i);
			if(str.startsWith("DZ")) {
				list.remove(i);
			}
		}
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	@Test
	public void show() {
		String str = "AbcdefgH";
		String str2 = "";
		for (int i = str.length()-1; i >= 0; i--) {
			str2 = str2 + str.charAt(i); 
		}
		System.out.println(str2);
	}
	
	
}
