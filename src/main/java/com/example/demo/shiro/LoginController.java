package com.example.demo.shiro;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	
	@PostMapping("/doLogin")
	public void doLogin(@RequestBody Map<String, String>  map) {
		String username = map.get("user");
		String password = map.get("pwd");
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(new UsernamePasswordToken(username, password));
			System.out.println("登陆成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("登陆失败");
		}
	}
	
	@PostMapping("/hello")
	public String hello(@RequestParam("zzr") String user , @RequestParam("jk") Integer jk) {
		return user + jk;
	}

	@GetMapping("/login")
    public String  login() {
        return "please login!";
    }
}
