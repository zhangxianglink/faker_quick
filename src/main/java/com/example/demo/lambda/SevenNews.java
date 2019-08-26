package com.example.demo.lambda;



import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 
 * @author zx
 *
 */
public class SevenNews {

	@Test
	public void autoCloseBeta() throws IOException{
		Path path = Paths.get("C:/Users/cuihang/Desktop/工作/new_file.json");
		try(
		BufferedReader in = Files.newBufferedReader(path);){
			String values = null;
			while((values= in.readLine() )!= null){
				
				System.out.println(values);
			}
		}
	
		
	}
	@Test
	public void nullnot(){
		String a = "";
		String b = null ;
		boolean null1 = Objects.isNull(a);
		boolean nonNull = Objects.nonNull(b);
		System.out.println(null1);
		System.out.println(nonNull);
		
		String[] ids = {"a","b","c","d","e",null,"g","",null,"j"};
		List<String> collect = Arrays.asList(ids).stream().filter(e -> Objects.nonNull(e)).collect(Collectors.toList());
	    System.out.println(collect.toString());
	}
	
	@Test
	public void log(){
		String message = "this is a good new for you ";
		Logger global = Logger.getGlobal();
		global.log(Level.INFO, message);
		global.severe(message);
	}
	
	/**
	 * 读取文件行的流
	 */
	@Test
	public void getFileLine(){
		Path path = Paths.get("D:/data/export/source.txt");
		try(Stream<String> stream = Files.lines(path);){
			List<String> collect = stream.filter(e -> e.contains("夫")).collect(Collectors.toList());
			System.out.println(collect.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 遍历当前目录项的流
	 */
	@Test
	public void getDirectory(){
		Path path = Paths.get("D:/data");
		try(Stream<Path> lists = Files.list(path);){
			lists.forEach(e -> System.out.println(e.getFileName().toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 处理到子目录的流
	 */
	@Test
	public void getSonD() throws IOException{
		Path path = Paths.get("D:/data");
		 BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\my.ini"), StandardCharsets.UTF_8);
		try(Stream<Path> walk = Files.walk(path,2);) {
			walk.forEach(e -> System.out.println(e.getFileName().toString()));
		} catch (Exception e) {

		}
	}
	
	/*
	 * 扫描目录,返回指定的数据
	 */
	@Test
	public void findMyself() throws IOException{
		Path path = Paths.get("D:/data");
		Stream<Path> result = Files.find(path, 2, (p, basicFileAttributes) -> String.valueOf(p).endsWith(".csv"));
//		result.map(Path::getFileName).forEach(System.out::println);
		Stream<Path> result2 = Files.find(path, 2, (p, b) -> b.creationTime().toMillis() > 1554777745561l );
		result2.map(Path::getFileName).forEach(System.out::println);
	}

	@Test
	public void fileAttributes() throws IOException{
		Path file = Paths.get("D:/data");
		BasicFileAttributes readAttributes = Files.readAttributes(file, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
		long millis = readAttributes.lastModifiedTime().toMillis();
		System.out.println(millis);
		Instant instant = readAttributes.lastModifiedTime().toInstant();
	    LocalDateTime time = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));
	    DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日  HH时mm分ss秒");
	    String format = ofPattern.format(time);
	    System.out.println(format);
	}
}
