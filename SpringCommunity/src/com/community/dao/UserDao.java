package com.community.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.dto.User;
import com.community.mapper.UserMapper;

@Repository
public class UserDao {
	
	@Autowired
	private UserMapper userMapper;
	
	// 아이디 중복 체크
	public String checkUserIdExist(String user_id) {
		return userMapper.checkUserIdExist(user_id);
	}
	
	// 회원가입 - insert
	public void addUserInfo(User user) {
		userMapper.addUserInfo(user);
	}
	

}
