package com.example.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class SpringQuickApplicationTests {

	@Test
	public void contextLoads() {
	   Integer a = null;
	   if ( a == null || a != 1){
		   System.out.println("ok");
	   }
	}
	
	public static void main(String[] args) {
		String[] str = new String[]{"燕十三","步惊云","谢晓峰","无名扫地僧","太极张三丰","浪翻云"};
		List<String> list = Arrays.asList(str);
		TreeSet<String> set = new TreeSet<String>((a,b) -> a.compareTo(b));
		set.addAll(list);
		for (String string : set) {
			System.out.println(string);
		}
	}
	
	
	@SuppressWarnings("resource")
	@Test
	public void str() throws FileNotFoundException{
		String a = "123";
		a = "234";
		System.out.println(a);
		String file = null;
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
	}
	
	@Test
	public void show(){
		HashMap<Object, Object> map = new HashMap<>();
		 List<Map<String, Object>> seals = new ArrayList<Map<String,Object>>();
		 map.put("seals", seals);
		 Map<String, Object> seal;
		 for (int i = 0; i < 2; i++) {
			seal = new HashMap<>();
			seal.put("first", i);
			seal.put("second", 10-i);
			seals.add(seal);
		}
		 System.out.println(map.toString());
	}
	

}
