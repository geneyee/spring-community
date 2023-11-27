package com.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.community.dto.User;

public interface UserMapper {
	
	// 아이디 중복 체크 
	@Select("select user_name " +
			"from user_table " +
			"where user_id = #{user_id}")
	String checkUserIdExist(String user_id);
	
	// 회원가입
	@Insert("insert into user_table (user_idx, user_name, user_id, user_pw) " +
			"values (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addUserInfo(User user);
	
	// 로그인
	@Select("select user_idx, user_name "
			+ "from user_table "
			+ "where user_id = #{user_id} and user_pw =#{user_pw}")
	User getLoginUserInfo(User user);

}
