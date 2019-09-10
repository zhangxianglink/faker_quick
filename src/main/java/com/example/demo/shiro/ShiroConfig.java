package com.example.demo.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	//首先需要提供一个 Realm 的实例。
	@Bean
	MyRealm myRealm() {
		return new MyRealm();
	}
	
	@Bean
	DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
	}
	
	//在 ShiroFilterFactoryBean 中指定路径拦截规则等。
	@Bean
	ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean bean = new  ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager());
		bean.setLoginUrl("/login");
	    bean.setSuccessUrl("/index");
	    bean.setUnauthorizedUrl("/unauthorizedurl");
	    //接下来的 Map 中配置了路径拦截规则，注意，要有序。
	    Map<String, String> map = new LinkedHashMap<>();
        map.put("/doLogin", "anon");
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
	}
	
}
