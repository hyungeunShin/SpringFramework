package kr.or.ddit.controller.ch08.button;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;

@Controller
@RequestMapping("/formtag/button")
public class JSPFormButtonTagController {
	/*
	 * 스프링 폼 태그
	 * 
	 * 버튼 요소
	 * - HTML에서 버튼을 출력하려면 <form:button>
	 */
	
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		model.addAttribute("member", new Member());
		return "ch08/button/registerForm01";
	}
}
