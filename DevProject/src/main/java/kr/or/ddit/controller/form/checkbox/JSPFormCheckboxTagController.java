package kr.or.ddit.controller.form.checkbox;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/formtag/checkbox")
@Slf4j
public class JSPFormCheckboxTagController {
	/*
	 * 스프링 폼 태그
	 * 
	 * 체크박스 요소
	 * - HTML 체크박스를 출력하려면 <form:checkbox> 요소를 사용
	 */
	
	@RequestMapping(value="/registerForm01")
	public String registerForm01(Model model) {
		model.addAttribute("member", new Member());
		return "form/checkbox/registerForm01";
	}
	
	@RequestMapping(value="/registerForm02")
	public String registerForm02(Model model) {
		Member member = new Member();
		
		member.setDeveloper("Y");
		member.setForeigner(true);
		member.setHobby("Movie");
		
		String[] array = {"Music", "Movie"};
		member.setHobbyArray(array);
		
		List<String> list = new ArrayList<>();
		list.add("Music");
		list.add("Sports");
		
		member.setHobbyList(list);
		model.addAttribute("member", member);
		return "form/checkbox/registerForm01";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String registerResult(@ModelAttribute Member member, Model model) {
		log.info("developer : " + member.getDeveloper());
		log.info("foreigner : " + member.isForeigner());
		log.info("hobby : " + member.getHobby());
		
		String[] array = member.getHobbyArray();
		if(array != null) {
			for (int i = 0; i < array.length; i++) {
				log.info("" + array[i]);
			}
		}
		
		List<String> list = member.getHobbyList();
		if(list != null) {
			for (String string : list) {
				log.info(string);
			}
		}
		
		//model.addAttribute("member", member);
		return "form/checkbox/result";
	}
}
