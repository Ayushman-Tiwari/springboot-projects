package com.ayushman.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public @ResponseBody String hello(@RequestParam String name) {
		return "Hello: " + name;
	}
	
}
