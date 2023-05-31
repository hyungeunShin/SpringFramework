package kr.or.ddit.controller.form.selectbox;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;

@Controller
@RequestMapping("/formtag/selectbox")
public class JSPFormSelectboxTagController {
	/*
	 * 스프링 폼 태그
	 * 
	 * 셀렉트 박스
	 * - HTML 셀렉트 박스를 출력하려면 <form:select> 요소를 사용한다
	 */
	
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("01", "Korea");
		map.put("02", "Canada");
		map.put("03", "Austrailia");
		
		model.addAttribute("map", map);
		model.addAttribute("member", new Member());
		return "form/selectbox/registerForm01";
	}
}
