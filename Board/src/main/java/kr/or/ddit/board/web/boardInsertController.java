package kr.or.ddit.board.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class boardInsertController {
	
	@Inject
	private IBoardService boardService;
	
	@RequestMapping(value="/form.do", method=RequestMethod.GET)
	public String boardForm() {
		return "board/form";
	}
	
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public String boardInsert(BoardVO vo, Model model) {
		String goPage = "";
		
		Map<String, String> errors = new HashMap<>();
		
		
		//form태그의 name 속성값이 vo와 매핑되어 들어옴
		System.out.println("insert메소드 들어오자마자 : " + vo);
		
		
		
		//org.apache.commons 라이브러리를 이용해 vo안에 들어있는
		//제목 내용을 공백/null 체크를 통해 입력값 필터를 처리한다
		
		//if(vo.getBoTitle().equals("")) {
		if(StringUtils.isBlank(vo.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");
		}
		
		//if(vo.getBoContent().equals("")) {
		if(StringUtils.isBlank(vo.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		
		if(errors.size() > 0) { //에러발생
			//model은 데이터 전달자
			//내가 넘기고자 하는 데이터를 대신 넘겨주는 역할을 담당한다
			
			model.addAttribute("errors", errors);
			model.addAttribute("board", vo);
			goPage = "board/form";
		} else {  	//정상
			vo.setBoWriter("a001");
			ServiceResult result = boardService.insertBoard(vo);
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/board/detail.do?boNo=" + vo.getBoNo();
				
				System.out.println("쿼리를 들렸다가 온 후 : " + vo);
			} else {
				errors.put("msg", "서버에러! 다시 시도해주새요");
				model.addAttribute("errors", errors);
				goPage = "board/form";
			}
		}
		
		return goPage;
	}
}
