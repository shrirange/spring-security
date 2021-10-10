package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String getHello() {
		return "Hello";
	}
	
	@GetMapping("/")
	public String getHome() {
		return "Home";
	}
	
	@GetMapping("/deny")
	public String deny() {
		return "Deny";
	}
	


}
