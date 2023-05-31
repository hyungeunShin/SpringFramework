package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/redirectattribute")
@Slf4j
public class RedirectAttributeMemberController {
	/*
	 * 4. RedirectAttribute 타입
	 * - RedirectAttribute는 일회성으로 데이터를 전달하는 용도로 사용
	 * - redirect 방식으로 가야한다
	 * 
	 * 에러 message같은 일회용 데이터
	 */
	
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String registerForm() {
		return "member/registerRedirectAttributeForm";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Member member, RedirectAttributes ra) {
		log.info("mem : " + member);
		ra.addFlashAttribute("msg", "success");
		//ra.addFlashAttribute("member", member);
		return "redirect:/redirectattribute/result";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.GET)
	public String result() {
		return "result";
	}
}
