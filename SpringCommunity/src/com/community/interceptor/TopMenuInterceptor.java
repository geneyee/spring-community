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
		
		List<BoardInfo> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUser", loginUser);
		// 주의할것 loginUser는 SessionScope bean이다. 
		// session영역에 자동으로 저장되는 것이 아닌 브라우저 최초 요청 시 주입되는 bean을 의미.
		// 그러므로, request.SetAttribute() 해줘서 jsp에서 사용한다. 
		
		
		return true;
	}
}
