package com.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.community.service.UserService;

@RestController
public class RestApiController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public ResponseEntity<Boolean> checkUserIdExist(@PathVariable String user_id) {
		
		boolean chk = userService.checkUserIdExist(user_id);
		
		return ResponseEntity.ok(chk);
	}

}
