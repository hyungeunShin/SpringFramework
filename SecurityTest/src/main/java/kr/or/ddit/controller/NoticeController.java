package kr.or.ddit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list() {
		return "notice/list";
	}
	
	//회원, 관리자 둘 다 들어갈 수 있게 된다
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerForm() {
		log.info("");
		return "notice/register";
	}
}
