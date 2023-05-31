package kr.or.ddit.controller.form.label;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;

@Controller
@RequestMapping("/formtag/label")
public class JSPFormLabelTagController {
	/*
	 * 스프링 폼 태그
	 * 
	 * 라벨 요소
	 * - HTML 라벨을 출력하려면 <form:label>
	 */
	
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		model.addAttribute("member", new Member());
		return "form/label/registerForm01";
	}
}
