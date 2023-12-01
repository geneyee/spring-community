package com.community.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.dto.Content;
import com.community.mapper.BoardMapper;

@Repository
public class BoardDao {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public void addContentInfo(Content content) {
		boardMapper.addContentInfo(content);
	}

}
