package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.pojo.Boy;

@Mapper
public interface BoyDao {

	List<Boy> userList();

	@Select("select id as bId , height as bHeight , name as bName  from boy where id = #{bId}")
	Boy selectOneById2(Integer bId);
	
	Boy selectOneById(@Param("bId") Integer bId);
	
	int insertBoy(Boy boy);
	@Insert("insert into boy(name,height) values(#{bName},#{bHeight})")
	int addBoy(Boy boy);
	
	@Delete("delete from boy where id = #{Id}")
	int deleteOneById2(@Param("Id") Integer bId);
	int deleteOneById(@Param("bId") Integer bId);
	
	int updateUser1(Boy boy);
	@Update("update boy set height = #{height} , name = #{name} where id = #{id}")
	int updateUser2(@Param("height") Integer height , @Param("name") String name ,@Param("id")  Integer id);
	
	List<Boy> findBoy(Boy boy);
	
	Boy findBoy2(Boy boy);
	
	List<Boy> selectById(@Param("list") List<Integer> list);
}
