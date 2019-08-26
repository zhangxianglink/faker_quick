package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BoyDao;
import com.example.demo.pojo.Boy;
import com.example.demo.service.ReviewServiceFactory;
import com.example.demo.utils.WatermarkPdfUtils;

@RestController
public class Boycontroller {
	
	@Autowired
	private BoyDao boyDao;

	@RequestMapping("/test")
	@ResponseBody
	public List<Boy> test(){
		List<Boy> userList = boyDao.userList();
		
		return userList;
	}
	
	@Autowired
	private ReviewServiceFactory factory;
	
	@GetMapping(value="/get/{id}")
	public String get1(@PathVariable("id") Integer id){
		return "注解中id要和参数名一致:"+id;
	}
	
	@GetMapping("/service/{name}")
	public String service(@PathVariable("name") String name){
		System.out.println(name);
		String putMessage = factory.getService(name).putMessage();
		return putMessage;
	}
	
	@PostMapping("/pdf")
	public String waterMark(MultipartFile multipartFile) throws IOException{
		byte[] infile = multipartFile.getBytes();
		WatermarkPdfUtils.addWatermarktext(infile, null, "风遁螺旋手里剑");
		return "ok ";
	}
	
}
