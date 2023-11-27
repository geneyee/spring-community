package com.community.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public String checkUserIdExist(String user_id) {
		return sqlSessionTemplate.selectOne("user.checkUserIdExist", user_id);
	}
	

}
