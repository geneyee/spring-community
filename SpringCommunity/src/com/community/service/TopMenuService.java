package com.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.dao.TopMenuDao;
import com.community.domain.BoardInfo;

@Service
public class TopMenuService {
	
	@Autowired
	private TopMenuDao topMenuDao;
	
	public List<BoardInfo> getTopMenuList() {
		List<BoardInfo> topMenuList = topMenuDao.getTopMenuList();
		return topMenuList;
	}

}
