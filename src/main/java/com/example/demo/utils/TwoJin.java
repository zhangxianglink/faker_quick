package com.example.demo.utils;

import org.junit.Test;

public class TwoJin {
	
	public static void main(String[] args) {
		String data = "0101";
		int changeToten = changeToten(data);
		double changeToTen2 = changeToTen(data);
		System.out.println(changeToten);
		System.out.println(changeToTen2);
	}
	
	/*
	 * 自定义逻辑
	 */
	private static double changeToTen(String data){
		   char[] array = data.toCharArray();
		   double result = 0d;
		   int j =0 ;
			for (int i = array.length -1; i >= 0; i--) {
				if(array[i] == '1'){
				result += Math.pow(2, j);
				}
				j++;
			}
		return result;
	}
	
	/*
	 *已存在的方法
	 */
	private static int changeToten(String data){
		int parseInt = Integer.parseInt(data, 2);
		return parseInt;
	}
	
	
	/**
	 * 移位运算
	 */
	@Test
	public void shiftOperation(){
		byte a = 24;
		byte b = (byte) (a >> 5);
		float c = (a >> 5);
		System.out.println(b);
		System.out.println(c);
		
	}

}
