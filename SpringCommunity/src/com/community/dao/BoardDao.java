package com.community.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.dto.Content;
import com.community.mapper.BoardMapper;

@Repository
public class BoardDao {
	
	@Autowired
	private BoardMapper boardMapper;
	
	// 글쓰기
	public void addContentInfo(Content content) {
		boardMapper.addContentInfo(content);
	}
	
	// 게시판 이름
	public String getBoardInfoName(int board_info_idx) {
		return boardMapper.getBoardInfoName(board_info_idx);
	}
	
	// 게시글 목록
	public List<Content> getContentList(int content_board_idx) {
		return boardMapper.getContentList(content_board_idx);
	}
	
	// 글 읽기
	public Content getContentInfo(int content_idx) {
		return boardMapper.getContentInfo(content_idx);
	}
	
	// 글 수정
	public void modifyContentInfo(Content content) {
		boardMapper.modifyContentInfo(content);
	}
	
	// 글 삭제
	public void deleteContentInfo(int content_idx) {
		boardMapper.deleteContentInfo(content_idx);
	}

}
