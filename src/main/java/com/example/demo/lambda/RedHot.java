package com.example.demo.lambda;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.Test;

public class RedHot {

    //100元的红包,分成随机分10份
	@Test
	public void test(){
		Integer sum = 35;
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		ArrayList<Integer> list3 = new ArrayList<>();
		Random random = new Random();
		for (int i = 1; i < 10; i++) {
			int nextInt = random.nextInt(sum);
			sum = sum - nextInt;
			list.add(nextInt);
		}
		list.add(sum);
		sum = 35;
		for (int i = 1; i < 10; i++) {
			int nextInt = random.nextInt(sum);
			sum = sum - nextInt;
			list2.add(nextInt);
		}
		list2.add(sum);
		for (int i = 0; i < 10; i++) {
		Integer temp = 0;
			temp = list.get(i) + list2.get(i);
			list3.add(temp);
		}
		list3.stream().map(e -> e+3).forEach(System.out::println);
		int sum2 = list3.stream().mapToInt(e -> e.intValue() + 3).sum();
		System.out.println(sum2);
	}
	
	//二倍均值法
	@Test
	public void test2(){
		String money = "302.8";
		Integer nums = 10;
		Integer people = nums - 1;
		int length = money.length();
		int indexOf = money.indexOf(".")+1;
		int count = 1;
		for (int i = 0; i < length-indexOf; i++) {
			count = count * 10;
		}
		String string = String.valueOf(count);
        Integer sum = (int) (Double.valueOf(money) * count);
        System.out.println(sum);
        Random random = new Random();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < people; i++) {
			Integer j = random.nextInt(sum / nums * 2 - 100 ) + 100;
			list.add(String.valueOf(j));
			nums --;
			sum -= j;
		}
        list.add(String.valueOf(sum));
        Stream<BigDecimal> map = list.stream().map(e -> new BigDecimal(e));
        map.map(e -> e.divide(new BigDecimal(string))).forEach(System.out::println);
	}
	
	//线段切割法
	public void test3(){
		
	}
}
