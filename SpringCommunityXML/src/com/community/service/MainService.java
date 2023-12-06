package com.community.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.dao.BoardDao;
import com.community.dto.Content;

@Service
public class MainService {
	
	@Autowired
	private BoardDao boardDao;
	
	// 게시판 리스트 
	public List<Content> getMainList(int content_board_idx) {
		RowBounds rowBounds = new RowBounds(0, 5);
		return boardDao.getContentList(content_board_idx, rowBounds);
	}

}
