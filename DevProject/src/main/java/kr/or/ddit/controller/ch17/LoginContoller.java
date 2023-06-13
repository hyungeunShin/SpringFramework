package kr.or.ddit.controller.ch17;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginContoller {
	@RequestMapping(value="/login1", method=RequestMethod.GET)
	public String loginForm(HttpServletRequest req) {
		return "ch17/loginForm";
	}
	
	@RequestMapping(value="/login1", method=RequestMethod.POST)
	public String login(String userId, String userPw, Model model) {
		MemberVO member = new MemberVO();
		member.setUserId(userId);
		member.setUserPw(userPw);
		member.setUserName("홍길동");
		
		model.addAttribute("user", member);
		
		return "ch17/success";
	}
}
