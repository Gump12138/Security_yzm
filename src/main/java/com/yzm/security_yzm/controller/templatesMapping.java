package com.yzm.security_yzm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class templatesMapping {

	@RequestMapping("/signin")
	public String login(){
		return "signin";
	}

	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/signup")
	public String signup(){
		return "signup";
	}
}
