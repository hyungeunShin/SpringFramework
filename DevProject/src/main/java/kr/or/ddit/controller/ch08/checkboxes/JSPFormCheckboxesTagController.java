package kr.or.ddit.controller.ch08.checkboxes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.CodeLabelValue;
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
		return "ch08/checkboxes/registerForm01";
	}
	
	@RequestMapping(value="/registerForm02")
	public String registerForm02(Model model) {
		List<CodeLabelValue> hobbyCodeList = new ArrayList<>();
		hobbyCodeList.add(new CodeLabelValue("01", "Sports"));
		hobbyCodeList.add(new CodeLabelValue("02", "Movie"));
		hobbyCodeList.add(new CodeLabelValue("03", "Music"));
		model.addAttribute("hobbyCodeList", hobbyCodeList);
		model.addAttribute("member", new Member());
		return "ch08/checkboxes/registerForm02";
	}
}
