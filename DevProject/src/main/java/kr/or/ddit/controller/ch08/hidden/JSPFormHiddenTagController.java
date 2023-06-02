package kr.or.ddit.controller.ch08.hidden;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;

@Controller
@RequestMapping("/formtag/hidden")
public class JSPFormHiddenTagController {
	/*
	 * 스프링 폼 태그
	 * 
	 * hidden 필드
	 * - HTML 숨겨진 필드를 출력하려면 <form:hidden> 요소를 사용
	 */
	
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		Member member = new Member();
		member.setUserId("hong123");
		member.setUserName("홍길동");
		model.addAttribute("member", member);
		return "ch08/hidden/registerForm01";
	}
	
	@RequestMapping(value="/result")
	public String result(@ModelAttribute Member member, Model model) {
		return "ch08/hidden/result";
	}
}
