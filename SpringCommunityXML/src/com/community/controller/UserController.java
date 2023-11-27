package com.community.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.community.dto.User;
import com.community.service.UserService;
import com.community.validator.UserValidator;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name = "loginUser")
	@Lazy
	private User loginUser;
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUser")User user,
						@RequestParam(value = "fail", defaultValue = "false") boolean fail,
						Model model) {
		
		model.addAttribute("fail", fail);
		
		return "user/login";
	}
	
	@PostMapping("/login_pro")
	public String login(@Valid @ModelAttribute("tempLoginUser")User user, BindingResult result) {
		if(result.hasErrors()) {
			return "user/login";
		}
		
		userService.getLoginUserInfo(user);
		
		return loginUser.isUserLogin() ? "user/login_success" : "user/login_fail";
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUser")User user) {
		return "user/join";
	}
	
	@PostMapping("/join_pro")
	public String join(@Valid @ModelAttribute("joinUser")User user, BindingResult result) {

		if(result.hasErrors()) {
			return "user/join";
		}
		
		userService.addUserInfo(user);
		
		return "user/join_success";
	}

	@GetMapping("/modify")
	public String modify() {
		return "user/modify";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "user/logout";
	}
	
	//validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
}
