package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BoyDao;
import com.example.demo.pojo.Boy;
import com.example.demo.service.ReviewServiceFactory;
import com.example.demo.utils.WatermarkPdfUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/boy")
@RestController
public class Boycontroller {
	
	@Autowired
	private BoyDao boyDao;
	
	@RequestMapping("/test1")
	public String test1() {
		return "xxxxxxxxxxxxxxxxxx";
	}
	
	@PostMapping("/param")
	public Boy param(Integer id, String name) {
		Boy boy = new Boy();
		boy.setbId(id);
		boy.setbName(name);
		return boy;
	}
	
	@PostMapping("/boy")
	public Boy param(@RequestBody Boy boy) {
		return boy;
	}
	
	@PostMapping("/map")
	public String map(@RequestBody Map<String,Object> map) {
		String uid = (String) map.get("uid");
		return uid;
	}
	
	@PostMapping("/string")
	public Boy string(@RequestBody final String param) throws  IOException {
		ObjectMapper mapper = new ObjectMapper();
		Boy readValue = mapper.readValue(param, Boy.class);
		return readValue;
	}

	@GetMapping("/arrs")
	public Integer[] arrs(Integer[] ids) {
		return ids;
	}
	
	@RequestMapping("/test2")
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
