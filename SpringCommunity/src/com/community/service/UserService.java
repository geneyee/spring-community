package com.community.service;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.dao.UserDao;
import com.community.dto.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Resource(name = "loginUser")
	private User loginUser;
	
	// 아이디 중복
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
	
	// 수정 -조회
	public void getModifyUserInfo(User user) {
		
		// 로그인 한 유저의 idx
		// loginUser의 user_idx를 파라미터로 넘긴다.(getLoginUserInfo() 참고)
		User target = userDao.getModifyUserInfo(loginUser.getUser_idx());
		
		user.setUser_id(target.getUser_id());
		user.setUser_name(target.getUser_name());	
		user.setUser_idx(loginUser.getUser_idx());
	}

	// 수정 - update
	public void modifyUserInfo(User user) {
		
//		user.setUser_idx(loginUser.getUser_idx()); // session에 저장된 user_idx user에 저장
//		System.out.println(user.getUser_idx());
		
		userDao.modifyUserInfo(user); //hidden으로 user_idx값 넘겨받음
	}
	 

}
