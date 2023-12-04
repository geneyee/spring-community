package com.community.service;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.community.dao.BoardDao;
import com.community.dto.Content;
import com.community.dto.User;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	private static final Logger log = LogManager.getLogger(BoardService.class);
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "loginUser")
	private User loginUser;
	
	@Value("${path.upload}")
	private String path_upload;
	
	// 파일 저장 메서드
	private String saveUploadFile(MultipartFile upload_file) {
		
		// 파일 이름 랜덤
		String file_name = UUID.randomUUID().toString();
		
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return file_name;
	}
	
	// 글쓰기
	public void addContentInfo(Content content, int board_info_idx) {
		
		log.info("카테고리 번호 => {}", board_info_idx);
		
		MultipartFile upload_file = content.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			log.info("파일 업로드 => {}", file_name);
			
			// 업로드 한 파일 이름 db에 저장
			content.setContent_file(file_name);
		}
		
		// 게시판 idx
		content.setContent_board_idx(board_info_idx);
		// 유저 idx
		content.setContent_writer_idx(loginUser.getUser_idx());
		
		boardDao.addContentInfo(content);
	}
	
	// 게시판 이름
	public String getBoardInfoName(int board_info_idx) {
		return boardDao.getBoardInfoName(board_info_idx);
	}
	
	// 게시글 목록
	public List<Content> getContentList(int content_board_idx) {
		return boardDao.getContentList(content_board_idx);
	}
	
	// 글 읽기
	public Content getContentInfo(int content_idx) {
		return boardDao.getContentInfo(content_idx);
	}

	// 글 수정 - 조회
	public void getModifyContentInfo(Content content, int content_idx, int board_info_idx) {
		
		Content target = getContentInfo(content_idx);
		
		content.setContent_writer_name(target.getContent_writer_name());
		content.setContent_date(target.getContent_date());
		content.setContent_subject(target.getContent_subject());
		content.setContent_text(target.getContent_text());
		content.setContent_file(target.getContent_file());
		content.setContent_board_idx(board_info_idx);
		content.setContent_idx(content_idx);
		content.setContent_writer_idx(target.getContent_writer_idx());
		
	}
	
	// 글 수정 
	public void modifyContentInfo(Content content) {
		
		MultipartFile upload_file = content.getUpload_file();
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			content.setContent_file(file_name);
		}
		
		boardDao.modifyContentInfo(content);
	}
	
	// 글 삭제
	public void deleteContentInfo(int content_idx) {
		
		// 파일 삭제
		Content target = getContentInfo(content_idx);
		
		if(target.getContent_file() != null) {
				
			String fileName = target.getContent_file();
			File file = new File(path_upload + "/" + fileName);
			file.delete();
		}
		
		boardDao.deleteContentInfo(content_idx);
		
	}

}
