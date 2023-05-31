package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/modelattribute")
public class ModelAttributeMemberController {
	/*
	 * @ModelAttribute 어노테이션
	 * 
	 * - 매개변수로 전달받은 데이터를 전달할 때 사용
	 */
	
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String registerForm() {
		return "member/registerModelAttributeForm";
	}
	
	@RequestMapping(value="/register01", method = RequestMethod.POST)
	public String register01(String userId, String password) {
		log.info("id : " + userId);
		log.info("password " + password);
		
		return "result";
	}
	
	//modelAttribute 사용
	@RequestMapping(value="/register02", method = RequestMethod.POST)
	public String register02(@ModelAttribute("userId") String userId,
			@ModelAttribute("password") String password) {
		log.info("id : " + userId);
		log.info("password " + password);
		
		return "result";
	}
	
	//3
	@RequestMapping(value="/register03", method=RequestMethod.POST)
	public String register03(Member mem) {
		log.info("id : " + mem.getUserId());
		log.info("password " + mem.getPassword());
		
		return "result";
	}
}
