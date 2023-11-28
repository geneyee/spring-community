package com.community.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.community.dto.User;

public class CheckLoginInterceptor implements HandlerInterceptor {

	private User loginUser;
	
	public CheckLoginInterceptor(User loginUser) {
		this.loginUser = loginUser;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인 x
		if(loginUser.isUserLogin() == false) {
			response.sendRedirect(request.getContextPath() + "/user/not_login");
			return false; // controller로 x
		}
		return true;
	}
}
