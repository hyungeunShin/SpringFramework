package kr.or.ddit.controller.ch07;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/el")
public class JSPELController {
	/*
	 * 8. EL함수
	 * 
	 * 	1) EL 함수 목록
	 * 	- fn:contains(str1, str2)
	 * 	- 지정한 문자열이 대문자/소문자를 구분하지 않고 포함되어 있는지 판단
	 * 
	 * 	2) fn:containsIgnoreCase(str1, str2)
	 * 	- 지정한 문자열이 대문자/소문자를 구분하지 않고 포함되어 있는지 판단
	 * 
	 * 	3) fn:startsWith(str1, str2)
	 * 	- 지정한 문자열로 시작하는지 판단
	 * 
	 * 	4) fn:endsWith(str1, str2)
	 * 	- 지정한 문자열로 끝나는지 판단
	 * 
	 * 	5) fn:indexOf(str1, str2)
	 * 	- 지정한 문자열이 처음으로 나왔을 떄의 인덱스
	 * 
	 * 	6) fn:length(obj)
	 * 	- 컬렉션, 배열, 문자열의 길이를 계산
	 * 
	 * 	7) fn:escapeXml(str)
	 * 	- 지정한 문자열을 XML구문으로 해석되지 않도록 이스케이프
	 * 
	 * 	8) fn:replace(str, src, dest)
	 * 	- 문자열을 치환
	 * 
	 * 	9) fn:toLowerCase(str)
	 * 	- 소문자로 변환
	 * 
	 * 	10) fn:toUpperCase(str)
	 * 	- 대문자로 변환
	 * 
	 * 	11) fn:trim(str)
	 * 	- 문자열을 trim
	 * 
	 * 	12) fn:substring(str, idx1, idx2)
	 * 	- 지정한 범위에 해당하는 문자열을 자른다
	 * 
	 * 	13) fn:substringAfter(str1, str2)
	 * 	- 지정한 문자열에 위치하는 이후의 문자열을 자른다
	 * 
	 * 	14) fn:substringBefore(str1, str2)
	 * 	- 지정한 문자열에 위치하는 이전의 문자열을 자른다 
	 * 
	 * 	15) fn:join(array, str2)
	 * 	- 문자열을 구분자로 분할해서 하나의 문자열로 만든다
	 * 
	 * 	16) fn:split(str1, str2)
	 * 	- 문자열을 구분자로 분할해서 문자열 배열을 만든다
	 */
	
	@RequestMapping(value="/home0101")
	public String home0101(Model model) {
		String str = "<font>Hello World</font>"; 
		model.addAttribute("str", str);
		return "ch07/el/home0101";
	}
}
