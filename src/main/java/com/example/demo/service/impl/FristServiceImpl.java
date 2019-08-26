package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.service.ReviewService;

@Service
public class FristServiceImpl implements ReviewService{

	@Override
	public String getName() {
		return "First";
	}

	@Override
	public String putMessage() {
		// TODO Auto-generated method stub
		return "来自一号";
	}

}
