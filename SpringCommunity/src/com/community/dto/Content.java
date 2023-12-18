package com.community.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Content {
	
	private int content_idx;
	
	@NotBlank
	private String content_subject;
	
	@NotBlank
	private String content_text;
	
	// 클라이언트가 보낸 파일 data 저장 객체
	private MultipartFile upload_file;
	
	// DB에 저장할 파일의 이름
	private String content_file; 
	
	private int content_writer_idx;
	
	private int content_board_idx;
	
	private String content_date;
	
	private String content_writer_name;

	
	public void update(Content content) {
		this.content_writer_name = content.content_writer_name;
		this.content_date = content.content_date;
		this.content_subject = content.content_subject;
		this.content_text = content.content_text;
		this.content_file = content.content_file;
		this.content_board_idx = content.content_board_idx;
		this.content_idx = content.content_idx;
		this.content_writer_idx = content.content_writer_idx;
	}

}
