package com.community.mapper;

import org.apache.ibatis.annotations.Insert;

import com.community.dto.Content;

public interface BoardMapper {
	
	@Insert("insert into content_table(content_idx, content_subject, content_text, content_file, content_writer_idx, content_board_idx, content_date) "
			+ "values (CONTENT_SEQ.nextval, #{content_subject}, #{content_text}, "
			+ "#{content_file, jdbcType=VARCHAR}, " // null 허용되는 컬럼에 null 들어와도 에러...-> Mybatis 에러. 타입을 정확히 명시해주면 해결
			+ "#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(Content content);

}
