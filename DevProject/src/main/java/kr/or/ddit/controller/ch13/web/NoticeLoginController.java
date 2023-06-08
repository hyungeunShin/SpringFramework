package kr.or.ddit.controller.ch13.web;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.ch13.service.INoticeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeLoginController {
	@Inject
	private INoticeService noticeService;
	
	//로그인
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String noticeLogin(Model model) {
		model.addAttribute("bodyText", "login-page");
		return "conn/login";
	}
	
	//회원가입
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String registerForm(Model model) {
		model.addAttribute("bodyText", "register-page");
		return "conn/register";
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck", method=RequestMethod.POST)
	public ResponseEntity<ServiceResult> idCheck(String memId) {
		log.info("id : " + memId);
		ServiceResult result = noticeService.idCheck(memId);
		return new ResponseEntity<ServiceResult>(result, HttpStatus.OK);
	}
	
	//아이디 비밀번호 찾기
	@RequestMapping(value="/forget", method = RequestMethod.GET)
	public String loginForget(Model model) {
		model.addAttribute("bodyText", "login-page");
		return "conn/forget";
	}
}
