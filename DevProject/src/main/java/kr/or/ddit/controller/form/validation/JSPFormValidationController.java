package kr.or.ddit.controller.form.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/formtag/validation")
@Slf4j
public class JSPFormValidationController {
	/*
	 * 스프링 폼 태그
	 * 
	 * 입력값 검증 에러
	 * - 입력값 검증 에러를 출력하려면 <form:errors> 요소를 사용
	 */
	
	@RequestMapping(value="/registerFormValidation01")
	public String registerFormValidation01(Model model) {
		model.addAttribute("member", new Member());
		return "form/validation/registerForm01";
	}
	
	@RequestMapping(value="/result", method = RequestMethod.POST)
	public String result(@ModelAttribute Member member) {
		log.info("userID : " + member.getUserId());
		log.info("username : " + member.getUserName());
		log.info("email : " + member.getEmail());
		
		return "form/validation/result";
	}
}
