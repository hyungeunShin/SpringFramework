package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/formtag")
@Slf4j
public class JSPFormtagController {
	/*
	 * 8. 스프링 폼 태그
	 * 
	 *   1. 스프링 폼 태그 라이브러리
	 *   	스프링 폼은 HTML 폼을 표시하기 위한 태그 라이브러리이다
	 *   	스프링 폼을 이용하면 HTML 폼과자바 객체를 쉽게 바인딩 할 수 있다
	 *   
	 *   	스프링 폼 커스텀 태그 목록
	 *   	<form:form>
	 *   	- 폼 요소를 생성
	 *   	<form:input>
	 *   	- 텍스트 필드 요소를 생성
	 *   	<form:password>
	 *   	- 패스워드 필드 요소를 생성
	 *   	<form:textarea>
	 *   	- 텍스트 영역 요소를 생성
	 *   	<form:checkboxes>
	 *   	- 여러개의 체크박스 요소를 생성
	 *   	<form:checkbox>
	 *   	- 체크 박스 요소를 생성
	 *   	<form:radiobuttons>
	 *   	- 여러개의 라디오 버튼 요소를 생성
	 *   	<form:radiobutton>
	 *   	- 라디오 버튼 요소를 생성
	 *   	<form:select>
	 *   	- 셀렉트 박스 요소를 생성
	 *   	<form:hidden>
	 *   	- 숨겨진 필드 요소를 생성
	 *   	<form:label>
	 *   	- 라벨 요소를 생성
	 *   	<form:button>
	 *   	- 버튼 요소를 생성
	 *   	<form:errors>
	 *   	- 입력값 검증 오류를 표시
	 *   	
	 *   	스프링 폼 태그 라이브러리 선언
	 *   	[<%@ taglib uri="http://www.springframework.org/tags/form" %>]
	 *   
	 *   2. 폼 요소
	 *   	- HTML 폼을 출력하려면 <form:form>요소를 사용하여 생성
	 *
	 *   3. 폼 항목의 공통 속성
	 *   	- HTML 폼 항목을 출력하기 위한 태그 라이브러리에는 몇 가지 공통 속성이 있다
	 *   	
	 *   	path : 폼 항목에 바인딩 되는 폼 객체의 프로퍼티를 지정
	 *   	disabled : 폼 항목을 비활성화 할지 여부를 지정, 기본값은 false
	 *   	readonly : 폼 항목을 읽기 전용으로 만들지 여부를 지정, 기본값은 false
	 */
	
	@RequestMapping(value="/registerForm01", method = RequestMethod.GET)
	public String registerForm01(Model model) {
		//form:form 을 사용할 때 modelAttribute="member"를 사용
		//하지만 이때 modelAttribute="member"를 사용하기 위해서 이 별칭을 지칭한 new Member()를 생성해서 보내줘야 한다
		model.addAttribute("member", new Member());
		return "home/formtag/registerForm01";
	}
	
	@RequestMapping(value="/registerForm02", method = RequestMethod.GET)
	public String registerForm02(Model model) {
		//키값과 modelattribute의 값이 같아야 한다.
		model.addAttribute("user", new Member());
		return "home/formtag/registerForm02";
	}
	
	@RequestMapping(value="/registerForm03", method = RequestMethod.GET)
	public String registerForm03(Member member) {
		//@ModelAttribute("member") ==> member
		//@ModelAttribute("user") ==> user 
		//폼 객체의 속성명은 직접 지정하지 않으면 * 폼 객체의 클래스명의 맨 처음 문자를 소문자로 변환하여 처리
		return "home/formtag/registerForm01";
	}
	
	@RequestMapping(value="/registerForm04", method = RequestMethod.GET)
	public String registerForm04(@ModelAttribute("user") Member member) {
		//@ModelAttribute("member") ==> member
		//@ModelAttribute("user") ==> user 
		//폼 객체의 속성명은 직접 지정하지 않으면 * 폼 객체의 클래스명의 맨 처음 문자를 소문자로 변환하여 처리
		return "home/formtag/registerForm02";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Member member, Model model) {
		log.info("id : " + member.getUserId());
		log.info("name : " + member.getUserName());
		
		model.addAttribute("member", member);
		return "home/formtag/result";
	}
}
