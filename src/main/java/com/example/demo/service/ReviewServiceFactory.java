package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;



@Component
public class ReviewServiceFactory {
	
	private Map<String, ReviewService> reviewServiceImpls = new HashMap<>();
	
	@Autowired
	List<ReviewService> reviewServiceList;
	
	@PostConstruct
	public void initServiceList(){
		reviewServiceList.forEach(e -> reviewServiceImpls.put(e.getName(), e));
	}
	
	public ReviewService getService(String name){
		ReviewService reviewService = reviewServiceImpls.get(name);
		Assert.notNull(reviewService, "name参数有问题");
		return reviewService;
	}

}
