package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
//	Logger log = LogManager.getLogger(MainController.class);
		
	@GetMapping("/main")
	public String main() {
		return "main";
	}
}
