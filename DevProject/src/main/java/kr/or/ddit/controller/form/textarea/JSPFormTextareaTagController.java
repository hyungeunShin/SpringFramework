package kr.or.ddit.controller.form.textarea;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;

@Controller
@RequestMapping("/formtag/textarea")
public class JSPFormTextareaTagController {
	/*
	 * 스프링 폼 태그
	 * 
	 * 텍스트 영역 요소(textarea)
	 * - HTML 텍스트 영역을 출력하려면 <form:textarea> 요소를 사용한다
	 */
	
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		model.addAttribute("member", new Member());
		return "form/textarea/registerForm01";
	}
	
	@RequestMapping(value="/registerForm02")
	public String registerForm02(Model model) {
		Member member = new Member();
		member.setIntroduction("ㅎㅇ안녕하세요");
		model.addAttribute("member", member);
		return "form/textarea/registerForm01";
	}
	
}
