package kr.or.ddit.controller;

import java.security.Principal;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list() {
		return "board/list";
	}
	
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerForm(Principal principal) {
		//방법 1
		log.info("사용자명 : " + principal.getName());
		
		//방법 2
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		log.info("user.username : " + user.getUsername());
		log.info("user.password : " + user.getPassword());
		
		//역할명 얻어오기
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		while(iterator.hasNext()) {
			log.info("auth : " + iterator.next().getAuthority());
		}
		
		return "board/register";
	}
}
