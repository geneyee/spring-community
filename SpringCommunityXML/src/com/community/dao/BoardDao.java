package com.community.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
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
	public List<Content> getContentList(int content_board_idx, RowBounds rowBounds) {
		return sqlSessionTemplate.selectList("board.getContentList", content_board_idx, rowBounds);
	}
	
	// 글 읽기
	public Content getContentInfo(int content_idx) {
		return sqlSessionTemplate.selectOne("board.getContentInfo", content_idx);
	}
	
	// 글 수정
	public void modifyContentInfo(Content content) {
		sqlSessionTemplate.update("board.modifyContentInfo", content);
	}

	// 글 삭제
	public void deleteContentInfo(int content_idx) {
		sqlSessionTemplate.delete("board.deleteContentInfo", content_idx);
	}
	
	// 전체 글의 수
	public int getContentCnt(int content_board_idx) {
		return sqlSessionTemplate.selectOne("board.getContentCnt", content_board_idx);
	}
}
