package kr.or.ddit.controller.form.checkboxes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;

@Controller
@RequestMapping("/formtag/checkboxes")
public class JSPFormCheckboxesTagController {
	/*
	 * 스프링 폼 태그
	 * 
	 * 여러개의 체크박스 요소
	 * - HTML 여러개의 체크박스를 출력하려면 <form:checkboxes> 요소를 사용
	 */
	
	//모델에 map 타입의 데이터를 생성하여 추가한 후에 화면에 전달
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("01", "Sports");
		map.put("02", "Music");
		map.put("03", "Movie");
		model.addAttribute("hobbyMap", map);
		model.addAttribute(new Member());
		return "form/checkboxes/registerForm01";
	}
}
