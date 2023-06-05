package kr.or.ddit.controller.ch14.item01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/item")
public class FileUploadController01 {
	/*
	 * 14장 파일 업로드
	 * 
	 * 1. 파일업로드 설명
	 * - 서블릿 3.0에서 지원하는 파일 업로드 기능과 스프링 웹에서 제공하는 컴포넌트를 사용하여 파일을 업로드한다.
	 * 
	 * 	파일업로드 설정
	 * 	1) 서블릿 3.0 이상 지원
	 * 	2) 서블릿 표준 파일 업로드 기능을 활성화
	 * 	3) 스프링 MVC와 연계
	 * 	4) 업로드된 파일 저장 위치 설정
	 * 
	 * 	환경설정
	 * 	1) 의존관계 정의
	 * 	- 파일을 처리하기 위해서 의존 라이브러리를 추가한다.
	 * 		> pom.xml > commons-io, commons-fileupload
	 * 
	 * 	2) 웹 컨테이너 설정
	 * 		> web.xml 서블릿 표준 버전 3.1로 설정
	 * 		> multipart-config 활성화 (web.xml servlet 태그 내 설정)
	 * 
	 * 	3) 스프링 웹 설정
	 * 		> servlet-context.xml
	 * 		> multipartResolver Bean 등록(id는 multipartResolver로 설정)
	 * 
	 * 		[파일 업로드 설정]
	 * 		파일 업로드 방식은 2가지
	 * 		1. StandardServletMultipartResolver 사용 시 설정
	 * 			> servlet 3.0의 part를 이용한 multipartFile 데이터 처리
	 * 			> servlet-context.xml 에 설정
	 * 				> StandardServletMultipartResolver Bean 등록
	 * 			> web.xml에 설정
	 * 				> multipart-config (servlet 태그안에 설정)
	 * 
	 * 		2. CommonsMultipartResolver 사용 시 설정(우리가 사용할 방식)
	 * 			> Commons Fileuplaod API를 이용한 multipart File 데이터 처리
	 * 			> bean 속성으로 maxUploadSize, maxInMemorySize, defaultEncoding 설정을 제공
	 * 			> 기본값 및 허용되는 값에 대한 자세한 내용은 DiskFileUpload 속성을 참조
	 * 			> pom.xml 설정
	 * 				> commons-fileupload 추가
	 * 			> root-context.xml 추가
	 * 				> CommonsMultipartResolver bean 등록
	 * 			
	 * 			> root-context.xml에 설정
	 * 				> uploadPath bean 등록
	 * 			
	 * 			> multipartFilter 필터 등록
	 * 				> uploadPath bean 등록
	 * 
	 * 			데이터 베이스 준비
	 * 			1) item 테이블 생성(item1, item2, item3, item_attach)
	 */
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String itemRegisterForm() {
		return "ch14/register";
	}
}
