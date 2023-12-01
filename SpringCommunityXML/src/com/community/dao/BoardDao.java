package com.community.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.dto.Content;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 글쓰기
	public void addContentInfo(Content content) {
		sqlSessionTemplate.insert("board.addContentInfo", content);
	}
	
	// 게시판 이름
	public String getBoardInfoName(int board_info_idx) {
		return sqlSessionTemplate.selectOne("board.getBoardInfoName", board_info_idx);
	}
	
	// 게시글 목록
	public List<Content> getContentList(int content_board_idx) {
		return sqlSessionTemplate.selectList("board.getContentList", content_board_idx);
	}
	
	// 글 읽기
	public Content getContentInfo(int content_idx) {
		return sqlSessionTemplate.selectOne("board.getContentInfo", content_idx);
	}

}
