package kr.or.ddit.controller.ch08.password;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;

@Controller
@RequestMapping("/formtag/password")
public class JSPFormPasswordTagController {
	/*
	 * 스프링 폼 태그
	 * 
	 * 패스워드 필드 요소
	 * - HTML 패스워드 필드를 출력하려면 <form:password> 요소를 사용한다
	 * 
	 */
	
	//model로 new Member를 주거나
	//파라미터로 Member member
	//파라미터로 Member member + @ModelAttribute
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		model.addAttribute("member", new Member());
		return "form/password/registerForm01";
	}
	
	@RequestMapping(value="/registerForm02")
	public String registerForm02(Model model) {
		//값을 설정해서 뷰에 전달 하더라도 패스워드 필드에는 반영되지 않는다
		//패스워드만 그러하다
		Member member = new Member();
		member.setPassword("11231241");
		model.addAttribute("member", member);
		return "form/password/registerForm01";
	}
}
