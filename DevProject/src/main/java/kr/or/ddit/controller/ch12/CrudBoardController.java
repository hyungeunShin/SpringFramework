package kr.or.ddit.controller.ch12;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/crud/board")
@Slf4j
public class CrudBoardController {
	
	@Inject
	private IBoardService service;
	
	//빈이 등록되고 초기화 단계에서 바로 확인할 때 사용
	@PostConstruct
	public void init() {
		log.info("aopProxy 상태(interface 기반) : {} " + AopUtils.isAopProxy(service));
		log.info("aopProxy 상태(class 기반) : {} " + AopUtils.isCglibProxy(service));
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String crudRegisterForm(Model model) {
		model.addAttribute("board", new Board());
		return "ch12/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String crudRegister(Board board, Model model) {
		log.info("board : " + board);
		
		try {
			service.register(board);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("msg", "등록이 완료되었습니다!");
		return "ch12/success";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String crudList(Model model) {
		List<Board> boardList = service.list();
		model.addAttribute("boardList", boardList);
		return "ch12/list";
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String crudRead(int boardNo, Model model) {
		log.info("no : " + boardNo);
		
		Board board = service.read(boardNo);
		
		model.addAttribute("board", board);
		return "ch12/read";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String crudModifyForm(int boardNo, Model model) {
		Board board = service.read(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("status", "u");
		return "ch12/register";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String crudModify(Board board, Model model) {
		log.info("board: " + board);
		service.update(board);
		model.addAttribute("msg", "수정이 완료되었습니다");
		return "ch12/success";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String crudDelete(int boardNo, Model model) {
		service.delete(boardNo);
		model.addAttribute("msg", "삭제가 완료되었습니다");
		return "ch12/success";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String crudSearch(String title, Model model) {
		Board board = new Board();
		board.setTitle(title);
		
		List<Board> boardList = service.search(board);
		model.addAttribute("board", board);
		model.addAttribute("boardList", boardList);
		return "ch12/list";
	}
}
