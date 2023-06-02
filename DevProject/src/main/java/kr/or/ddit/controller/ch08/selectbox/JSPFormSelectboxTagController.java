package kr.or.ddit.controller.ch08.selectbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.CodeLabelValue;
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
	
	@RequestMapping(value="/registerForm02")
	public String registerForm02(Model model) {
		List<CodeLabelValue> list = new ArrayList<CodeLabelValue>();
		list.add(new CodeLabelValue("01", "Korea"));
		list.add(new CodeLabelValue("02", "Canada"));
		list.add(new CodeLabelValue("03", "Austrailia"));
		
		model.addAttribute("list", list);
		model.addAttribute("member", new Member());
		return "form/selectbox/registerForm02";
	}
	
	@RequestMapping(value="/registerForm03")
	public String registerForm03(Model model) {
		List<CodeLabelValue> list = new ArrayList<CodeLabelValue>();
		list.add(new CodeLabelValue("01", "Jeep"));
		list.add(new CodeLabelValue("02", "Bmw"));
		list.add(new CodeLabelValue("03", "Audi"));
		
		model.addAttribute("list", list);
		model.addAttribute("member", new Member());
		return "form/selectbox/registerForm03";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String result(@ModelAttribute Member member, Model model) {
		return "form/selectbox/result";
	}
}
