package kr.or.ddit.controller.ch13.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.ch13.service.INoticeService;
import kr.or.ddit.vo.DDITMemberVO;
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
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(DDITMemberVO memberVO, Model model, HttpServletRequest req, RedirectAttributes ra) {
		String goPage = "";
		
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(memberVO.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요!");
		}
		if(StringUtils.isBlank(memberVO.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요!");
		}
		if(StringUtils.isBlank(memberVO.getMemName())) {
			errors.put("memName", "이름을 입력해주세요!");
		}
		
		if(errors.size()>0) {	// 에러가발생
			model.addAttribute("bodyText", "register-page");
			model.addAttribute("errors", errors);
			model.addAttribute("member", memberVO);
			goPage = "conn/register";
		}else {
			ServiceResult result = noticeService.signup(req, memberVO);
			if(result.equals(ServiceResult.OK)) {
				ra.addFlashAttribute("message", "회원가입을 완료하였습니다.");	// 일회성 메시지
				goPage = "redirect:/notice/login";
			}else {
				model.addAttribute("bodyText", "register-page");
				model.addAttribute("message", "서버에러, 다시시도해주세요");
				model.addAttribute("member", memberVO);
				goPage = "conn/register";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(DDITMemberVO memberVO, Model model, HttpServletRequest req) {
		log.info("loginCheck() 실행@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.info("loginCheck() 실행@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.info("loginCheck() 실행@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.info("loginCheck() 실행@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		String goPage = "";
		
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(memberVO.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요!");
		}
		if(StringUtils.isBlank(memberVO.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요!");
		}
		
		if(errors.size()>0) {
			model.addAttribute("bodyText", "login-page");
			model.addAttribute("errors", errors);
			model.addAttribute("member", memberVO);
			goPage = "conn/login";
		}else {
			DDITMemberVO member = noticeService.loginCheck(memberVO);
			if(member != null) {
				HttpSession session = req.getSession();
				session.setAttribute("SessionInfo", member);
				goPage = "redirect:/notice/list";
			}else {
				model.addAttribute("bodyText", "login-page");
				model.addAttribute("message", "서버 에러, 로그인 정보를 정확하게 입력해주세요!");
				model.addAttribute("member", memberVO);
				goPage = "conn/login";
			}
		}
		return goPage;
	}
	
	@ResponseBody
	@RequestMapping(value="/idForget", method=RequestMethod.POST)
	public ResponseEntity<String> idForget(@RequestBody DDITMemberVO vo) {
		//data: {memEmail : email, memName : name}과 같은 경우는 문자열로 넘어와서 idForget(String memEmail, String memName) 이런식으로 받아야함
		String memId = noticeService.idForget(vo);
		return new ResponseEntity<String>(memId, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/pwForget", method=RequestMethod.POST)
	public ResponseEntity<String> pwForget(@RequestBody DDITMemberVO vo) {
		String memPw = noticeService.pwForget(vo);
		return new ResponseEntity<String>(memPw, HttpStatus.OK);
	}
}
