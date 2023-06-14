package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	@Inject
	private IBoardService boardService;
	
	@RequestMapping(value="/boardInsertForm", method=RequestMethod.GET)
	public String boardInsertForm() {
		return "board/board_form";
	}
	
	@RequestMapping(value="/boardUpdateForm/{boNo}", method=RequestMethod.GET)
	public String boardUpdateForm(@PathVariable("boNo") int boNo, Model model) {
		model.addAttribute("status", "u");
		
		BoardVO vo = boardService.selectBoard(boNo);
		
		model.addAttribute("board", vo);
		
		return "board/board_form";
	}
	
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
		log.info("searchType : " + searchType);
		log.info("searchWord : " + searchWord);
		
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = boardService.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> list = boardService.selectBoardList(pagingVO);
		pagingVO.setDataList(list);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/board_list";
	}
	
	@RequestMapping(value="/detail/{boNo}", method=RequestMethod.GET)
	public String boardDetail(@PathVariable("boNo") int boNo, Model model) {
		BoardVO vo = boardService.selectBoard(boNo);
		model.addAttribute("board", vo);
		return "board/board_detail";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String boardInsert(BoardVO vo, Model model, HttpSession session) {
		log.info("board : " + vo);
		
		Map<String, String> error = new HashMap<>();
		
		if(StringUtils.isBlank(vo.getBoTitle())) {
			error.put("error", "누락된 데이터가 존재합니다.");
		}
		
		if(StringUtils.isBlank(vo.getBoContent())) {
			error.put("error", "누락된 데이터가 존재합니다.");
		}
		
		if(error.size() > 0) {
			model.addAttribute("board", vo);
			model.addAttribute("error", error);
			return "board/board_form";
		} else {
			MemberVO mem = (MemberVO) session.getAttribute("member");
			vo.setBoWriter(mem.getMemId());
			
			int res = boardService.insertBoard(vo);
			
			if(res > 0) {
				log.info("board : " + vo);
				return "redirect:/board/detail/" + vo.getBoNo();
			} else {
				error.put("error", "서버에러");
				model.addAttribute("board", vo);
				model.addAttribute("error", error);
				return "board/board_form";
			}
		}
	}
	
	@RequestMapping(value="/delete/{boNo}", method=RequestMethod.GET)
	public String boardDelete(@PathVariable("boNo") int boNo, Model model) {
		int res = boardService.deleteBoard(boNo);
		if(res > 0) {
			return "redirect:/board/boardList";
		} else {
			BoardVO vo = boardService.selectBoard(boNo);
			model.addAttribute("board", vo);
			model.addAttribute("error", "삭제 실패. 다시 시도해주세요.");
			return "board/board_detail";
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST) 
	public String boardUpdate(BoardVO vo, Model model) {
		Map<String, String> error = new HashMap<>();
		
		if(StringUtils.isBlank(vo.getBoTitle())) {
			error.put("error", "누락된 데이터가 존재합니다.");
		}
		
		if(StringUtils.isBlank(vo.getBoContent())) {
			error.put("error", "누락된 데이터가 존재합니다.");
		}
		
		if(error.size() > 0) {
			model.addAttribute("board", vo);
			model.addAttribute("status", "u");
			model.addAttribute("error", error);
			return "board/board_form";
		} else {
			int res = boardService.updateBoard(vo);
			
			if(res > 0) {
				log.info("board : " + vo);
				return "redirect:/board/detail/" + vo.getBoNo();
			} else {
				error.put("error", "서버에러");
				model.addAttribute("board", vo);
				model.addAttribute("status", "u");
				model.addAttribute("error", error);
				return "board/board_form";
			}
		}
	}
}
