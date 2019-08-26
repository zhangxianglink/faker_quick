package com.example.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coo")
public class CookileController {
	
	
	@RequestMapping(path="/getCookile",method=RequestMethod.GET)
	public String getCookile(HttpServletResponse res){
		 res.setCharacterEncoding("UTF-8");
		 res.setContentType("application/json");
		Cookie cookie = new Cookie("user", "王麻子");
		 try {
			 cookie.setMaxAge(60*60);
			 res.addCookie(cookie);
			 return cookie.getName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return cookie.getName();
	}
	

}
