package kr.or.ddit.controller.ch08.textfield;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;

@Controller
@RequestMapping("/formtag/textfield")
public class JSPFormTextFieldController {
	/*
	 * 텍스트 필드 요소
	 * - HTML 텍스트 필드를 출력하려면 <form:input> 요소를 사용한다
	 */
	
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		model.addAttribute("member", new Member());
		return "/form/textfield/registerForm01";
	}
	
	@RequestMapping(value="/registerForm02")
	public String registerForm02(Model model) {
		Member member = new Member();
		member.setUserId("hong1234");
		member.setUserName("이길동");
		member.setEmail("aaa@ddit.com");
		model.addAttribute("member", member);
		return "/form/textfield/registerForm01";
	}
}
