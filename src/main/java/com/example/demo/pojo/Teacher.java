package com.example.demo.pojo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("tt")
public class Teacher {
	
	private Integer id;
	
	private String name;

}
