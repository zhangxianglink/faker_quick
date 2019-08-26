package com.example.demo.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Test;

public class ReadFile {
	
	public static void main(String[] args) throws IOException {
//		File file = new File("D:/data/首营资料7.25");
//		File[] listFiles = file.listFiles();
//		for (File file2 : listFiles) {
//			System.out.println(file2.getName());
//		}
		Path path = Paths.get("D:/data/d725");
		Stream<Path> walk = Files.walk(path,3);
		ArrayList<String> list = new ArrayList<>();
		walk.forEach(e -> {
			String string = e.toString();
			if(string.contains("pdf")){
				list.add(string);
			}
		});
		list.forEach(System.out::println);
		walk.close();
	}
	
	@Test
	public void read() throws IOException{
		Path path = Paths.get("D:/data/首营资料7.25");
		Stream<Path> walk = Files.walk(path,3);
		ArrayList<String> list = new ArrayList<>();
		walk.forEach(e -> {
			String string = e.toString();
			if(string.contains("pdf")){
				list.add(string);
			}
		});
		list.forEach(System.out::println);
	}
	
	@Test
	public void getFileLists(String path){
		File file = new File("");
		if(file.exists()){
			File[] listFiles = file.listFiles();	
			if(listFiles == null || listFiles.length == 0){
				System.out.println("文件夹为空");
			}else{
				for (File file2 : listFiles) {
					if(file2.isDirectory()){
						System.out.println(file2.getAbsolutePath());
						getFileLists(file2.getAbsolutePath());
					}else{
						System.out.println(file2.getAbsolutePath());
					}
				}
			}
		}else{
			System.out.println("文件不存在");
		}
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
