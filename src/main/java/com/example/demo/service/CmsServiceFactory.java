package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CmsServiceFactory implements ApplicationContextAware , InitializingBean{
	
	private ApplicationContext applicationContext;
	
	private Map<String, ReviewService> map = new HashMap<>();
	
    // 1获取spring的上下文
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
	     this.applicationContext = arg0;
	}
	
    //2获取接口实现类的所有bean
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, ReviewService> beansOfType = applicationContext.getBeansOfType(ReviewService.class);
		for (ReviewService reviewService : beansOfType.values()) {
			map.put(reviewService.getName(), reviewService);
		}
	}
	
	//3给出公共方法调用
	public ReviewService getCmsService(String name){
		ReviewService reviewService = map.get(name);
		Assert.notNull(reviewService,"sss");
		return reviewService;
	}
	
}
