package kr.or.ddit.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fmttag")
public class JSPfmttagController {
	/*
	 * 7. 숫자 및 날짜 포멧팅 처리 태그
	 * - 숫자 및 날짜의 포맷팅과 관련된 태그입니다.
	 * - 문자열을 숫자로, 문자열을 날짜로 변형하여 사용합니다.
	 * 
	 *  1) <fmt:formatNumber>
	 *  - 숫자를 형식화합니다
	 *  	- value(String of Number) : 서식에 맞춰 출력할 숫자
	 *  	- type(String) : 어떤 서식으로 출력할지를 정한다
	 *  	- pattern(String) : 직접 숫자를 출력할 서식을 지정한다
	 *  	- var(String) : 포맷팅한 결과를 지정할 변수 이름
	 *  
	 *  	** type 속성 : number일 경우 숫자 형식으로, percent일 경우 % 형식으로, currency일 경우 통화 형식으로 출력
	 *  	  기본값은 number이다. currency는 속해있는 국가코드에 맞는 통화 형식이 부여된다.
	 *  
	 *  2) <fmt:parseNumber>
	 * 	- 문자열을 숫자로 변환한다
	 * 		- value(String) : 파싱할 문자열
	 * 		- type(String) : value 속성의 문자열 타입을 지정
	 * 		- pattern(String) : 파싱할 때 직접 사용할 서식을 지정
	 * 		- var(String) : 파싱한 결과를 저장할 변수 이름을 지정
	 * 
	 * 		** type 속성 : number, currency, percent가 올 수 있다
	 * 
	 *  3) <fmt:formatDate>
	 *  - Date 객체를 문자열로 변환
	 *  	- value(java.util.Date) : 포맷팅할 날짜 및 시간 값
	 *  	- type(String) : 날짜, 시간 또는 둘 다 포맷팅 할지의 여부를 지정
	 *   	- dateStyle(String) : 날짜에 대해 미리 정의된 포맷팅 스타일을 지정
	 *   	- timeStyle(String) : 시간에 대해 미리 정의된 포맷팅 스타일을 지정 
	 *   	- pattern(String) : 파싱할 때 직접 사용할 서식을 지정
	 *   	- var(String) : 파싱한 결과를 저장할 변수 이름을 지정
	 * 		
	 * 		** type 속성 : time, date, both 중 한가지 값을 가질 수 있으며 기본값은 date이다
	 * 		dateStyle 속성 : default, short, medium, long, full 중 한가지 값을 가질 수 있으면 기본값은 default이다 
	 * 		timeStyle 속성 : default, short, medium, long, full 중 한가지 값을 가질 수 있으면 기본값은 default이다 
	 *  	
	 *  4) <fmt:parseDate>
	 *  - 문자열을 Date 객체로 변환 
	 *  	- value(String) : 파싱할 문자열
	 *  	- type(String) : 날짜, 시간 또는 둘 다 포맷팅 할지의 여부
	 *  	- dateStyle(String) : 날짜에 대해 미리 정의된 포맷팅 스타일을 지정
	 *   	- timeStyle(String) : 시간에 대해 미리 정의된 포맷팅 스타일을 지정 
	 *   	- pattern(String) : 파싱할 때 직접 사용할 서식을 지정
	 *   	- var(String) : 파싱한 결과를 저장할 변수 이름을 지정
	 *   
	 *   	** type 속성 : time, date, both 중 한가지 값을 가질 수 있으며 기본값은 date이다
	 * 		dateStyle 속성 : default, short, medium, long, full 중 한가지 값을 가질 수 있으면 기본값은 default이다 
	 * 		timeStyle 속성 : default, short, medium, long, full 중 한가지 값을 가질 수 있으면 기본값은 default이다
	 */
	
	//1) type 속성을 지정하거나 pattern 속성으 ㄹ지정하여 숫자를 형식화 한다
	@RequestMapping(value="/home0101")
	public String home0101(Model model) {
		int coin = 100;
		model.addAttribute("coin", coin);
		return "home/fmttag/home0101";
	}
	
	//2) type 속성이 지정되지 않으면 기본값은 number
	@RequestMapping(value="/home0201")
	public String home0201(Model model) {
		String coin = "1000";
		model.addAttribute("coin", coin);
		return "home/fmttag/home0201";
	}
	
//	//3) type => currency
//	@RequestMapping(value="/home0202")
//	public String home0202(Model model) {
//		String coin = "￦1000";
//		model.addAttribute("coin", coin);
//	    return "home/fmttag/home0202";
//	}
	
	@RequestMapping(value="/home0202")
	public String home0202(Model model) {
		String coin = "￦1000";
		model.addAttribute("coin", coin);
		//return "home/fmttag/home0202";
		return "home/fmttag/test";
	}
	
	@RequestMapping(value="/home0203")
	public String home0203(Model model) {
		String coin = "1,000.25";
		model.addAttribute("coin", coin);
		return "home/fmttag/home0203";
	}
	
	@RequestMapping(value="/home0301")
	public String home0301(Model model) {
		Date date = new Date();
		model.addAttribute("now", date);
		return "home/fmttag/home0301";
	}
	
	@RequestMapping(value="/home0302")
	public String home0302(Model model) {
		Date date = new Date();
		model.addAttribute("now", date);
		return "home/fmttag/home0302";
	}
	
	@RequestMapping(value="/home0303")
	public String home0303(Model model) {
		Date date = new Date();
		model.addAttribute("now", date);
		return "home/fmttag/home0303"; 
	}
	
	@RequestMapping(value="/home0304")
	public String home0304(Model model) {
		Date date = new Date();
		model.addAttribute("now", date);
		return "home/fmttag/home0304"; 
	}
	
	//dateStyle 속성을 지정하지 않으면 기본값은 default
	@RequestMapping(value="/home0401")
	public String home0401(Model model) {
		String dateValue = "2023. 10. 30";
		model.addAttribute("dateValue", dateValue);
		return "home/fmttag/home0401";
	}
	
	@RequestMapping(value="/home0402")
	public String home0402(Model model) {
		String dateValue = "23. 2. 1"; //short 형태
		//String dateValue = "2020. 2. 1"; // medium형태
		//String dateValue = "2020년 2월 1일 (금)"; // long
		//String dateValue = "2020년 2월 1일 금요일"; // full
		//각 dateStyle로 지정된 값이 페이지로 넘어가 pasing 될 때 parseDate 내에 dateStyle을 각 값과 일치하는 스타일 형태로 지정해주어야 값이 파싱
		model.addAttribute("dateValue", dateValue);
		return "home/fmttag/home0402";
	}
	
	@RequestMapping(value="/home0403")
	public String home0403(Model model) {
		String dateValue = "2022-02-01 15:00:24";
		model.addAttribute("dateValue", dateValue);
		return "home/fmttag/home0403";
	}
}
