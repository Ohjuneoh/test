package kr.co.helf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.helf.dto.BoardPrevNextDto;
import kr.co.helf.form.AddBoardForm;
import kr.co.helf.service.BoardService;
import kr.co.helf.vo.Board;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	@Autowired
	BoardService boardService;
	
	// 공지사항 전체조회(페이징처리 포함)
	@GetMapping(value="/notice")
	public String noticeList(@RequestParam(name="page", required=false, defaultValue="1")int page, Model model) {
		Map<String, Object> param = new HashMap<>();
		param.put("page",page);
		
		Map<String, Object> result = boardService.getAllNotice(param);
		
		model.addAttribute("result", result);
		return "/board/notice";
	}
	
	// 공지사항 등록폼 화면 요청처리
	@GetMapping(value="/noticeform")
	public String noticeform() {
		
		return "board/noticeform";
	}
	
	// 공지사항 등록 요청처리
	@PostMapping(value="/addNotice")
	public String addNotice(AddBoardForm form) {

		boardService.addNotice(form);
		
		return "redirect:/board/notice";
	}
	
//	// 공지사항 수정 화면
//	@GetMapping("/modify")
//	public String getNoticeModify(ModifyBoardForm form) {
//		
//	}

	
	// 공지사항 상세정보 & 이전글/다음글
	@GetMapping(value="/detail")
	public String detailPrevNext(@RequestParam("no") int no, Model model) {
		Board board = boardService.getBoardByNo(no);
		BoardPrevNextDto dto = boardService.getPrevNext(no);
		
		model.addAttribute("board", board);
		model.addAttribute("dto", dto);
		
		return "board/noticeDetail";
	}
	
	// faq화면 
	@GetMapping(value="/faq")
	public String faqform() {
		return "board/faq";
	}
	

	// 1:1문의 화면 
	@GetMapping(value="/inquiries")
	public String inquiriesform() {
		return "board/inquiries";
	}
	
	// 1:1문의 유저 문의하기 폼  
	@GetMapping(value="/inquiryUserForm")
	public String inquiryUserform() {
		
		return "board/inquiryUserForm";
	}
	
	
}
