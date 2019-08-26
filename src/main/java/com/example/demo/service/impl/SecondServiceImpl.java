package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.service.ReviewService;

@Service
public class SecondServiceImpl implements ReviewService{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Second";
	}

	@Override
	public String putMessage() {
		// TODO Auto-generated method stub
		return "来自二号";
	}

}
