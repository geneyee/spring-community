package com.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean checkUserIdExist(String user_id) {
		
		String user_name = userDao.checkUserIdExist(user_id);
		
		// user_name 데이터 없으면 회원가입 가능
		return user_name == null ? true : false;
	}

}
