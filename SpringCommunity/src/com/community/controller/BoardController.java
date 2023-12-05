package com.community.controller;

import java.util.List;

import javax.annotation.Resource;
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
import com.community.dto.Page;
import com.community.dto.User;
import com.community.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	private static Logger log = LogManager.getLogger(BoardController.class);
		
	@Autowired
	private BoardService boardService;
	
	@Resource(name = "loginUser")
	private User loginUser;
	
	@GetMapping("/main") // board/main=?board_info_idx={}
	public String main(@RequestParam int board_info_idx, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		String board_info_name = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", board_info_name);
		
		List<Content> list = boardService.getContentList(board_info_idx, page);
		model.addAttribute("list", list);
		
		Page paging = boardService.getContentCnt(board_info_idx, page);
		model.addAttribute("page", paging);
		
		return "board/main";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam int board_info_idx, @RequestParam int content_idx, Model model) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		
		Content content = boardService.getContentInfo(content_idx);
		model.addAttribute("readContent", content);
		log.info("작성자 idx => {}",content.getContent_writer_idx());
		
		// 글 작성자만 수정, 삭제버튼 활성화
		model.addAttribute("user_idx", loginUser.getUser_idx());
		log.info("로그인 유저 idx => {}", loginUser.getUser_idx());
		
		return "board/read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContent") Content content, @RequestParam int board_info_idx, Model model) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		return "board/write";
	}
	
	@PostMapping("/write_pro")
	public String write(@Valid @ModelAttribute("writeContent") Content content, BindingResult result, @RequestParam int board_info_idx) {
		if(result.hasErrors()) {
			return "board/write";
		}
		boardService.addContentInfo(content, board_info_idx);
		
		return "board/write_success";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam int board_info_idx, @RequestParam int content_idx, Model model, 
			@ModelAttribute("modifyContent") Content content) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		
		boardService.getModifyContentInfo(content, content_idx, board_info_idx);
		
		return "board/modify";
	}
	
	@PostMapping("modify_pro")
	public String modify(@Valid @ModelAttribute("modifyContent") Content content, BindingResult result) {
		
		if(result.hasErrors()) {
			return "board/modify";
		}
		
		boardService.modifyContentInfo(content);
		
		return "board/modify_success";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int board_info_idx, @RequestParam int content_idx, Model model) {
		
		boardService.deleteContentInfo(content_idx);
		model.addAttribute("board_info_idx", board_info_idx);
		
		return "board/delete";
	}
	
	@GetMapping("/not_writer")
	public String not_writer() {
		return "board/not_writer";
	}

}
