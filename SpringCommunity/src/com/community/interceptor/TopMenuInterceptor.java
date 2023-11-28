package com.community.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.community.dto.BoardInfo;
import com.community.dto.User;
import com.community.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor {
	
	private TopMenuService topMenuService;
	private User loginUser;

	public TopMenuInterceptor(TopMenuService topMenuService, User loginUser) {
		this.topMenuService = topMenuService;
		this.loginUser = loginUser;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		List<BoardInfo> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUser", loginUser);
		
		return true;
	}
}
