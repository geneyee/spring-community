package com.community.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.community.dto.Content;
import com.community.dto.User;
import com.community.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor {
	
	@Resource(name = "loginUser")
	@Lazy
	private User loginUser;
	
	@Autowired
	private BoardService boardService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		int contentIdx = Integer.parseInt(request.getParameter("content_idx"));
		Content target = boardService.getContentInfo(contentIdx);
		
		if(target.getContent_writer_idx() != loginUser.getUser_idx()) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_writer");
			return false;
		}
		
		return true;
	}

}
