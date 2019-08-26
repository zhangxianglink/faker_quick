package com.example.demo.pojo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cc")
public class Tclass {
	
	private int id;
	private String name;
	
	private Teacher teacher;

}
