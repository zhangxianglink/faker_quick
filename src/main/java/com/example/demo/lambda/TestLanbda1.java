package com.example.demo.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.apache.commons.compress.utils.Lists;
import org.apache.poi.hpsf.Array;
import org.junit.Test;

/*
 * 延迟执行
 */
public class TestLanbda1 {
	
	@Test
	public void show(){
		ArrayList<Object> newArrayList = Lists.newArrayList();
		newArrayList.add("");
		System.out.println(newArrayList.size());
	}

	/*
	 * lambda的基础语法
	 */
	@Test
	public void  first(){
	   String[] strings = {"jack","caption","component","black","war"};
	   Arrays.sort(strings, (first,second) -> Integer.compare(first.length(), second.length()));
	   Arrays.asList(strings).forEach(System.out::println);
  	}
	
	@Test
	public void second(){
		Double[] dd = {1d,2d,3d,6d,5d};
		Stream.of(dd).forEach(e -> System.out.println(e));
	}
	
	@Test
	public void thread(){
		String msg = "aka : 胡德小叮当";
		double[] dou = {1,2,3,45,56,7};
		get3(msg, dou);
	}
	
	public void get3(String txt, double[] dou){
		DoubleStream.of(dou).map(d -> Math.pow(d, 2)).forEach(e ->{
			System.out.println(e + txt);
		});
	}
}
