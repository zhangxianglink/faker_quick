package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.Tclass;
import com.example.demo.pojo.Teacher;

@Mapper
public interface TclassMapper {
	
	Tclass getClass(Integer id);
	
	Teacher gettt(@Param("tid") int tid , @Param("cid") int cid);
	
	Tclass getcc(int id);

}
