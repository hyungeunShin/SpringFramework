package kr.or.ddit.controller.ch08.validation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/validation")
@Slf4j
public class ValidationController {
	/*
	 * 입력 유효성 검증
	 * 
	 * 1) 입력값 검증
	 * - 스프링 MVC Bean Validation 기능을 이용해 요청 파라미터 값이 바인딩 된 도메인 클래스(또는 커멘드 클래스)의 입력값 검증을 한다
	 * 
	 * [환경설정]
	 * # 의존관계 정의
	 * - 입력값 검증을 위한 의존 라이브러리를 추가한다
	 * - pom.xml에서 hibernate-validator 추가
	 * 
	 * # 입력값 검증 활성화
	 * - Member 클래스로 넘어가서 임시 테스트로 userId, userName에 규칙을 활성화 한다
	 * - 이 때 규칙을 활성화 하기 위해서 사용할 어노테이션이 있다
	 * 	> @Validation을 지정
	 * 	> 입력값 검증 대상의 도메인 클래스 직후에 BindingResult를 정의
	 * 		> BindingResult에는 요청 데이터의 바인딩 오류와 입력값 검증 오류 정보가 저장된다
	 * 
	 * # 입력값 검증 환경설정 순서
	 * 1. 입력값 검증을 위한 의존 라이브러리 추가
	 * 2. 입력값 검증 활성화
	 * 	> 활성화를 할 도메인 클래스 안에 @Validate 어노테이션을 지정
	 * 3. 도메인 클래스 내 필드에다가 검증을 위한 어노테이션들로 데이터 검증을 설정(@NotBlank, @Size 등등)
	 * 4. 에러를 받을 BindingResult를 설정(컨트롤러 메소드 내에 설정)
	 */
	
	@RequestMapping(value="/registerValidationForm01")
	public String registerValidationForm01(Model model) {
		model.addAttribute("member", new Member());
		return "validation/registerForm01";
	}
	
	@RequestMapping(value="/result", method = RequestMethod.POST)
	public String result(@Validated Member member, BindingResult result) {
		log.info("id : " + member.getUserId());
		log.info("name : " + member.getUserName());
		
		if(result.hasErrors()) {
			return "validation/registerForm01";
		}
		
		log.info("password : " + member.getPassword());
		log.info("email : " + member.getEmail());
		log.info("gender : " + member.getGender());
		return "validation/success";
	}
	
	/*
	 * 입력값 검증 결과
	 * - 입력값 검증 대상의 도메인 클래스 직후에 BindingResult를 정의
	 * 
	 * 	# BindingResult
	 * 	1) 에러 정보를 확인을 위한 BindingResult 메소드
	 * 
	 * 	hasErrors()
	 *  - 에러가 발생한 경우 true를 반환
	 *  hasGlobalErrors()
	 *  - 객체 레벨의 에러가 발생한 경우 true를 반환
	 *  hasFieldErrors()
	 *  - 필드 레벨의 에러가 발생한 경우 true를 반환
	 *  hasFieldErrors(String)
	 *  - 인수에 지정한 필드에서 에러가 발생한 경우 true를 반환 
	 */
	@RequestMapping(value="/registerValidationForm02")
	public String registerValidationForm02(Model model) {
		model.addAttribute("member", new Member());
		return "validation/registerForm02";
	}
	
	@RequestMapping(value="/result2", method = RequestMethod.POST)
	public String result2(@Validated Member member, BindingResult result) {
		log.info("error : " + result.hasErrors());
		
		if(result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErrors = result.getGlobalErrors();
			List<FieldError> fieldErrors = result.getFieldErrors();
			
			log.info("allErrors : " + allErrors.size());
			log.info("globalErrors : " + globalErrors.size());
			log.info("fieldErrors : " + fieldErrors.size());
			
			//객체와 필드 레벨의 에러 정보를 모두 출력
			for (ObjectError err : allErrors) {
				log.info("allErrors : " + err + "\n");
			}
			
			//객체 레벨의 에러 정보를 모두 출력
			for (ObjectError err : globalErrors) {
				log.info("globalErrors : " + err + "\n");
			}
			
			//필드 레벨의 에러 정보를 모두 출력
			for (FieldError err : fieldErrors) {
				log.info("fieldErrors : " + err + "\n");
				log.info("default Message : " + err.getDefaultMessage());
			}
			
			return "validation/registerForm02";
		}
		
		log.info("mem : " + member);
		
		return "validation/success";
	}
	
	/*
	 * 입력값 검증 규칙
	 * - 입력값 검증 규칙은 Bean Validation이 제공하는 제약 어노테이션이다
	 * 
	 * 	검사 규칙은 크게 다음 세가지로 분류 할 수 있다
	 * 	- Bean Validation 표준 제약 어노테이션
	 * 	- 서드파티에서 구현한 제약 어노테이션
	 * 	- 직접 구현한 제약 어노테이션
	 * 
	 * 	1) Member 클래스에서 테스트를 위한 어노테이션으로 설정
	 * 	- @NotNull : 빈 값이 아닌지를 검사한다
	 * 	- @Null : null 인지를 검사한다
	 * 	- @NotBlank : 문자열이 null이 아니고 trim한 길이가 0보다 큰 지 검사한다
	 * 	- @NotEmpty : 문자열이 null이거나 비어있는지 검사한다
	 * 	- @Size : 글자 수나 컬렉션의 요소 개수를 검사한다
	 * 		> max = ?, min = ?
	 * 	- @Max(value=) : value보다 작거나 같은걸 검사한다
	 * 	- @Min(value=) : value보다 크거나 같은걸 검사한다
	 * 	- @Email : email 주소 형식인지를 검사한다
	 * 	- @Past : 과거 날짜인지를 검사한다
	 * 	- @Future : 미래 날짜인지를 검사한다
	 * 	- @Pattern(regexp=) : CharSequence는 지정된 정규식과 일치해야 하고, 정규식은 Java 정규식 규칙을 따른다
	 * 	- Positive : 양수여야 한다(0은 에러)
	 * 	- PositiveOrZero : 양수 또는 0이어야 한다
	 * 	- Length(min=, max=) : 문자열의 길이가 min과 max 사이인지를 검사한다
	 * 
	 * 	[텍스트]
	 * 	- Member 클래스의 검증 활성화 추가
	 * 		> userId, password, userName, email, dateOfBirth
	 * 	
	 * 
	 * 
	 * 중첩된 자바빈즈 입력값 검증
	 * - 중첩된 자바빈즈와 자바빈즈의 컬렉션에서 정의한 프로퍼티에 대해 입력값 검증을 할 때는 @Valid를 지정
	 * 		1) 중첩된 자바빈즈 클래스를 정의하고 @Valid를 지정
	 * 		- Member 클래스 내 Address address 필드에 @Valid 어노테이션을 지정
	 * 		- Member 클래스 내 List<Card> cardList 필드에 @Valid 어노테이션을 지정
	 * 
	 * 		2) Address 클래스에도 validation을 설정
	 * 		3) Card 클래스에도 validation을 설정
	 */
	
	@RequestMapping(value="/registerValidationForm03")
	public String registerValidationForm03(Model model) {
		model.addAttribute("member", new Member());
		return "validation/registerForm03";
	}
	
	@RequestMapping(value="/result3", method=RequestMethod.POST)
	public String result3(@Validated Member member, BindingResult result) {
		if(result.hasErrors()) {
			return "validation/registerForm03";
		}
		
		log.info("mem : " + member);
		
		Address addr = member.getAddress();
		if(addr != null) {
			log.info("postcode : " + addr.getPostCode());
			log.info("location : " + addr.getLocation());
		}
		
		List<Card> list = member.getCardList();
		
		if(list != null) {
			for (Card card : list) {
				log.info("no : " + card.getNo());
				log.info("valid : " + card.getValidMonth());
			}
		}
		
		return "validation/success";
	}
}
