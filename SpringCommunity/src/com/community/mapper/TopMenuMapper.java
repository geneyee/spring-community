package com.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.community.domain.BoardInfo;

public interface TopMenuMapper {
	
	@Select("select board_info_idx, board_info_name "
			+ "from board_info_table "
			+ "order by board_info_idx")
	List<BoardInfo> getTopMenuList();

}
