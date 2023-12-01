package com.community.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.dto.Content;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void addContentInfo(Content content) {
		sqlSessionTemplate.insert("board.addContentInfo", content);
	}

}
