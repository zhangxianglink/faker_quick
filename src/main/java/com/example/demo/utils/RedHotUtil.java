package com.example.demo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RedHotUtil {

    public static void main(String[] args) {
       List<Integer> line = line(500, 3);
       Integer integer = line.get(0);
    	 Random random = new Random();
    	 int height = 600;
     int a =	random.nextInt((height / 2 - 175)) + 25;
    	int b = random.nextInt((height / 2 - 175 )) + (height / 2);
    	System.out.println( a + " : " + b + " :" + integer);
    }
    
    private static List<Integer> line(int width , int size){
    	int space = 25;
    	width = width - 150 - space - space;
	    List<Integer> list = getList(width, size);
        Collections.sort(list);
        return list;
    }

    private static List<Integer> getList(int width, int numbers){
        ArrayList<Integer> list = new ArrayList<>();
        Integer spots = numbers ;
        Integer amount = width;
        list.add(amount);
        Random random = new Random();
        for (int i = 0; i < numbers -1; i++) {
            int i1 = random.nextInt(amount / spots * 2 - 50) + 50;
            amount -= i1;
            spots--;
            list.add(amount);
        }
        return list;
    }
    
    
    
     
}
