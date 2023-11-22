package com.community.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.domain.BoardInfo;

@Repository
public class TopMenuDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<BoardInfo> getTopMenuList() {
		List<BoardInfo> topMenuList = sqlSessionTemplate.selectList("topmenu.get_topmenu_list");
		return topMenuList;
	}

}
