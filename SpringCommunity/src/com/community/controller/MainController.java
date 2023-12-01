package com.community.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	private static Logger log = LogManager.getLogger(MainController.class);
	
	@GetMapping("/main")
	public String main() {
		String msg = "로그 테스트";
		log.info("로그 테스트!!!!! => {}", msg);
		return "main";
	}
}
