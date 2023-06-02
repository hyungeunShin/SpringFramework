package kr.or.ddit.controller.ch08.radio;

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
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/formtag/radio")
@Slf4j
public class JSPFormRadioTagController {
	/*
	 * 스프링 폼 태그
	 * 
	 * 여러개의 라이도 버튼 요소
	 * - HTML 라디오 버튼을 출력하려면 <form:radiobuttons> 요소를 사용
	 */
	
	//map타입의 데이터를 생성하여 전달
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("01", "Male");
		map.put("02", "Female");
		map.put("03", "Other");
		model.addAttribute("map", map);
		model.addAttribute("member", new Member());
		return "form/radio/registerForm01";
	}
	
	@RequestMapping(value="/registerForm02")
	public String registerForm02(Model model) {
		List<CodeLabelValue> list = new ArrayList<CodeLabelValue>();
		list.add(new CodeLabelValue("01", "Male"));
		list.add(new CodeLabelValue("02", "Female"));
		list.add(new CodeLabelValue("03", "Other"));
		model.addAttribute("genderCodeList", list);
		model.addAttribute("member", new Member());
		return "form/radio/registerForm02";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String registerFormResult(@ModelAttribute Member member, Model model) {
		log.info("gender: " + member.getGender());
		return "form/radio/result";
	}
	
	//단일 라디오 버튼
	/*
	 * - HTML 라디오 버튼을 출력하려면 <form:radiobutton> 
	 */
	
	@RequestMapping(value="/registerForm03")
	public String registerForm03(Model model) {
		model.addAttribute("member", new Member());
		return "form/radio/registerForm03";
	}
	
	@RequestMapping(value="/registerForm04")
	public String registerForm04(Model model) {
		Member member = new Member();
		member.setGender("Male");
		model.addAttribute("member", member);
		return "form/radio/registerForm03";
	}
}
