package com.community.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.dto.User;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 아이디 중복 체크
	public String checkUserIdExist(String user_id) {
		return sqlSessionTemplate.selectOne("user.checkUserIdExist", user_id);
	}
	
	// 회원가입
	public void addUserInfo(User user) {
		sqlSessionTemplate.insert("user.addUserInfo", user);
	}
	
	// 로그인
	public User getLoginUserInfo(User user) {
		return sqlSessionTemplate.selectOne("user.getLoginUserInfo", user);
	}
	
	// 정보수정 - 조회
	public User getModifyUserInfo(int user_idx) {
		return sqlSessionTemplate.selectOne("user.getModifyUserInfo", user_idx);
	}
	
	// 정보수정 - update
	public void modifyUserInfo(User user) {
		sqlSessionTemplate.update("user.modifyUserInfo", user);
	}

}
