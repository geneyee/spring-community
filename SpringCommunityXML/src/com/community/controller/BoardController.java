package com.community.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.community.dto.Content;
import com.community.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	Logger log = LogManager.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/main")
	public String main(@RequestParam int board_info_idx, Model model) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		return "board/main";
	}
	
	@GetMapping("/read")
	public String read() {
		return "board/read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContent") Content content, @RequestParam int board_info_idx) {
		
		content.setContent_board_idx(board_info_idx);
		return "board/write";
	}
	
	@PostMapping("/write_pro")
	public String write(@Valid @ModelAttribute("writeContent") Content content, BindingResult result) {
//		log.info("controller request => {}", content.toString());
		
		if(result.hasErrors()) {
			return "board/write";
		}
		
		boardService.addContentInfo(content);
		
		return "board/write_success";
	}
	
	@GetMapping("/modify")
	public String modify() {
		return "board/modify";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "board/delete";
	}

}
