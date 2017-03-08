package com.niit.stayconnected.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {

	@RequestMapping("/")
	public String welcome(){
		return "/index";
	}
	
	@RequestMapping("register")
	public String Register(){
		return "/register";
	}
}
