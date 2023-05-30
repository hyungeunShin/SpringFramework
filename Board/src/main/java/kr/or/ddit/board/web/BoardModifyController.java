package kr.or.ddit.board.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardModifyController {
	
	@Inject
	private IBoardService boardService;
	
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String boardUpdateForm(int boNo, Model model) {
		BoardVO vo = boardService.selectBoard(boNo);
		model.addAttribute("board", vo);
		model.addAttribute("status", "u"); // update인지 insert를 위한 폼인지 구분하기 위해서
		return "board/form";
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST) 
	public String boardUpdate(BoardVO vo, Model model) {
		String goPage = "";
		
		ServiceResult result = boardService.updateBoard(vo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/board/detail.do?boNo=" + vo.getBoNo();
		} else {
			model.addAttribute("board", vo);
			model.addAttribute("status", "u");
			goPage = "board/form";
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/delete.do", method=RequestMethod.POST)
	public String boardDelete(int boNo, Model model) {
		String goPage = "";
		ServiceResult result = boardService.deleteBoard(boNo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/board/list.do";
		} else {
			goPage = "redirect:/board/detail.do?boNo=" + boNo;
		}
		
		return goPage;
	}
	
}
