package com.community.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.community.dao.UserDao;
import com.community.dto.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Resource(name = "loginUser")
	@Lazy
	private User loginUser;
	
	// 아이디 중복 체크
	public boolean checkUserIdExist(String user_id) {
		
		String user_name = userDao.checkUserIdExist(user_id);
		
		// user_name 데이터 없으면 회원가입 가능
		return user_name == null ? true : false;
	}

	// 회원가입
	public void addUserInfo(User user) {
		userDao.addUserInfo(user);
	}
	
	// 로그인
	public void getLoginUserInfo(User user) {
		User target = userDao.getLoginUserInfo(user);
		
		if(target != null) {
			loginUser.setUser_idx(target.getUser_idx());
			loginUser.setUser_name(target.getUser_name());
			loginUser.setUserLogin(true);
		}
	}
	
	// 정보수정 - 조회
	public void getModifyUserInfo(User user) {
		// 1. 수정할 유저 찾기
		// 2. 찾은 유저 data -> dto에 담기
		
		User target = userDao.getModifyUserInfo(loginUser.getUser_idx());
		
		user.setUser_id(target.getUser_id());
		user.setUser_name(target.getUser_name());
		user.setUser_idx(loginUser.getUser_idx());
	}
	
	// 정보수정 - update
	public void modifyUserInfo(User user) {
		
		userDao.modifyUserInfo(user);

	}
	
}
