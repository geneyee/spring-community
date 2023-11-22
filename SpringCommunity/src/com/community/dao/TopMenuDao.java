package com.community.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.domain.BoardInfo;
import com.community.mapper.TopMenuMapper;

@Repository
public class TopMenuDao {
	
	@Autowired
	private TopMenuMapper topMenuMapper;
	
	public List<BoardInfo> getTopMenuList() {
		List<BoardInfo> topMenuList = topMenuMapper.getTopMenuList();
		return topMenuList;
	}

}
