package com.community.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.community.dto.BoardInfo;
import com.community.dto.Content;
import com.community.service.MainService;
import com.community.service.TopMenuService;

@Controller
public class MainController {
	
	private static Logger log = LogManager.getLogger(MainController.class);
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private TopMenuService topMenuService;
	
	@GetMapping("/main")
	public String main(Model model) {
		
		// 글 목록
		ArrayList<List<Content>> list = new ArrayList<List<Content>>();
		
		for(int i = 1; i <= 4; i++) {
			List<Content> board = mainService.getMainList(i);
			list.add(board);
		}
		
		model.addAttribute("list", list);
		
		// 게시판 이름
		List<BoardInfo> board_list = topMenuService.getTopMenuList();
		model.addAttribute("board_list", board_list);

		return "main";
	}
}
