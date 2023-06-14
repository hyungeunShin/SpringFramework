package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class controller {
	
	@Inject
	private IMemberService memberService;
	
	@RequestMapping(value="/signupForm", method=RequestMethod.GET)
	public String signUpForm() {
		return "member/member_signup";
	}
	
	@RequestMapping(value="/signInForm", method=RequestMethod.GET)
	public String signInForm() {
		return "member/member_signin";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signUp(MemberVO vo, boolean memAgree, Model model) {
		log.info("mem : " + vo);
		log.info("agree : " + memAgree);
		
		Map<String, String> error = new HashMap<>();
		
		if(StringUtils.isBlank(vo.getMemId())) {
			error.put("error", "누락된 정보가 존재합니다.");
		}
		if(StringUtils.isBlank(vo.getMemPw())) {
			error.put("error", "누락된 정보가 존재합니다.");
		}
		if(StringUtils.isBlank(vo.getMemName())) {
			error.put("error", "누락된 정보가 존재합니다.");
		}
		if(StringUtils.isBlank(vo.getMemPhone())) {
			error.put("error", "누락된 정보가 존재합니다.");
		}
		if(StringUtils.isBlank(vo.getMemEmail())) {
			error.put("error", "누락된 정보가 존재합니다.");
		}
		if(!memAgree) {
			error.put("error", "동의를 체크해주세요.");
		}
		
		if(error.size() > 0) {
			model.addAttribute("error", error);
			model.addAttribute("member", vo);
			return "member/member_signup";
		} else {
			int res = memberService.insertMember(vo);
			
			if(res > 0) {
				model.addAttribute("res", "가입이 완료되었습니다.");
				return "member/member_signin";
			} else {
				error.put("error", "가입에 실패하셨습니다.");
				model.addAttribute("error", error);
				model.addAttribute("member", vo);
				return "member/member_signup";
			}
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String logIn(@RequestParam Map<String, Object> map, Model model, HttpSession session, HttpServletRequest request) {
		String id = map.get("memId").toString();
		String pw = map.get("memPw").toString();
		log.info("id : " + id);
		log.info("pw : " + pw);
		
		Map<String, String> error = new HashMap<>();
		
		if(StringUtils.isBlank(id) || StringUtils.isBlank(pw)) {
			error.put("error", "누락된 입력정보가 존재합니다.");
		}
		
		if(error.size() > 0) {
			model.addAttribute("error", error);
			model.addAttribute("map", map);
			return "member/member_signin";
		} else {
			MemberVO vo = memberService.selectMember(map);
			
			if(vo != null) {
				session.setAttribute("member", vo);
				
				Cookie[] cookies = request.getCookies();
				log.info("쿠키 길이 : " + cookies.length);
				if(cookies != null) {
					for (Cookie cookie : cookies) {
						String name = cookie.getName();
						log.info("쿠키 이름 : " + name);
						if("requestURI".equals(name)) {
							return "redirect:" + cookie.getValue();
						}
					}
				}
				
				return "redirect:/board/boardList";
			} else {
				error.put("error", "존재하지 않는 회원입니다.");
				model.addAttribute("error", error);
				model.addAttribute("map", map);
				return "member/member_signin";
			}
		}
	}
	
	@RequestMapping(value="/idcheck/{id}", method=RequestMethod.POST)
	@ResponseBody
	public int idCheck(@PathVariable("id") String id) {
		log.info("아이디 중복체크");
		log.info("id : " + id);
		int res = memberService.CheckId(id);
		return res;
	}
}
