package com.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.community.dto.Content;

public interface BoardMapper {
	
	@SelectKey(statement = "select content_seq.nextval from dual", keyProperty = "content_idx", before = true, resultType = int.class)
	
	// 글쓰기
	@Insert("insert into content_table(content_idx, content_subject, content_text, content_file, content_writer_idx, content_board_idx, content_date) "
			+ "values (#{content_idx}, #{content_subject}, #{content_text}, "
			+ "#{content_file, jdbcType=VARCHAR}, " // null 허용되는 컬럼에 null 들어와도 에러...-> Mybatis 에러. 타입을 정확히 명시해주면 해결
			+ "#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(Content content);
	
	// 게시판 이름
	@Select("select board_info_name from board_info_table where board_info_idx = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);
	
	// 게시글 목록
	@Select("select c.content_idx, c.content_subject, u.user_name as content_writer_name, "
			+ "to_char(c.content_date, 'YYYY-MM-DD') as content_date "
			+ "from content_table c join user_table u on c.content_writer_idx = u.user_idx "
			+ "where c.content_board_idx = #{content_board_idx} "
			+ "order by c.content_idx desc")
	List<Content> getContentList(int content_board_idx);
	
	
	// 글 읽기
	@Select("select u.user_name as content_writer_name, "
			+ "to_char(c.content_date, 'YYYY-MM-DD') as content_date, "
			+ "c.content_subject, c.content_text, c.content_file, "
			+ "c.content_writer_idx "
			+ "from content_table c join user_table u on c.content_writer_idx = u.user_idx "
			+ "where c.content_idx = #{content_idx}")
	Content getContentInfo(int content_idx);

	// 글 수정
	@Update("update content_table "
			+ "set content_subject = #{content_subject}, content_text = #{content_text}, "
			+ "content_file =#{content_file, jdbcType=VARCHAR}"
			+ "where content_idx = #{content_idx}")
	void modifyContentInfo(Content content);
	
	// 글 삭제
	@Delete("delete from content_table where content_idx = #{content_idx}")
	void deleteContentInfo(int content_idx);
}
