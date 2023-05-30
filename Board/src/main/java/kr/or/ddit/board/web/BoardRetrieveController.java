package kr.or.ddit.board.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	
	@Inject
	private IBoardService boardService;
	
	//method를 지우면 get도 가능, post도 가능하다
	@RequestMapping(value="/list.do")
	public String boardList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		 
		//일반적인 게시판 목록 조회 - 페이징 처리가 없음
//		List<BoardVO> list = boardService.selectBoardList();
//		model.addAttribute("list", list);
		
		//일반적인 게시판 목록 조회 - 페이징 처리 및 검색기능 
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		
		//브라우저에서 검색한 검색 유형, 검색 키워드를 이용하여 검색 처리
		//검색 키워드가 있으면 검색을 한거고, 키워드가 없으면 검색을 하지않음
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		
		//총 목록 게시글 수 가져오기
		int totalRecord = boardService.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> dataList = boardService.selectBoardList2(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		return "board/list"; 
	}
	
	@RequestMapping(value="/detail.do", method=RequestMethod.GET)
	public String boardDetail(int boNo, Model model) {
		BoardVO vo = boardService.selectBoard(boNo);
		
		model.addAttribute("board", vo);
		return "/board/view";
	}
}
