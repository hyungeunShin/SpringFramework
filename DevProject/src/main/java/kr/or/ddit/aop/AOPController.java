package kr.or.ddit.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//@Component : 스프링 빈으로 등록하기 위한 어노테이션
//@Aspect : 어노테이션을 붙여 이 클래스가 Aspect를 나태나는 클래스 라는걸 명시
//AOPContoller 클래스 빈 등록 시, aOPController로 할지 aoPController로 할지 명확하지 않을 수 있어서 이름을 명시해줌
@Component("aopController")
@Aspect
@Slf4j
public class AOPController {
	/*
	 * 1. AOP 설명
	 * 	[AOP 예시]
	 * 	홍길동이 신입으로 프로젝트를 진행 중.
	 * 	이길동이 '홍길동님 지금 개발중인 서비스 전체에 각각의 처리 속도 좀 로그로 남겨주세요'라는 부탁을 했다
	 * 	홍길동은 로그를 메소드마다 하나하나씩 넣어야 하는지 의문이 생김
	 * 		'서비스 로직에서 제일 중요한 로직은 본래의 기능이 제일 중요하고 지금 내가 작성하는 로직은 옵션(부가기능)인데 이걸 하나로 묶어서 처리는 불가능한가?'
	 * 
	 * 	시간을 측정하고 권한을 체크하는 등의 기능은 옵션과 같은 부가기능으로 일종의 인프라 로직이라고 하는데, 이 인프라 로직은 애플리케이션 전 영역에서 나타날 수 있고
	 * 	중복코드를 만들어 내 개발의 효율성을 저하시키고 유지보수가 힘들어 질 수 있다.
	 * 	
	 * 	이러한 인프라 로직은 아래처럼 하나의 관심사를 가질 수 있는데 이런 관심사들의 중복이 횡단으로 나타나는데, 이것을 가르켜 '횡단 관심사(cross-cutting concern)'이라고 함
	 * 
	 * 	[처리속도 측정]	[처리속도 측정]	[처리속도 측정]	[처리속도 측정]
	 * 	-----------------------------------------------
	 * 	[비즈니스 로직]	[비즈니스 로직]	[비즈니스 로직]	[비즈니스 로직]
	 * 	[처리내용 로깅]	[처리내용 로깅]	[처리내용 로깅]	[처리내용 로깅]
	 * 	-----------------------------------------------
	 * 		로그인	      회원가입	     게시판 목록	    게시판 등록
	 * 
	 * 	이런 횡단 관심사를 통해 프로그래밍하는 것이 AOP라고 한다
	 * 
	 * 	- Aspect : AOP의 단위가 되는 횡단 관심사
	 * 	- 횡단관심사(Cross-Cutting Concern) : 핵심(core) 비즈니스 로직(메소드 실행 시작 시간 출력, 메소드 처리 후 시간 출력 등)과 다소 거리가 있지만.
	 * 	  여러 모듈에서 공통적이고 반복적인 처리를 요구하는 내용(메소드 실행 시작 시간 출력 등)
	 * 	- 횡단관심사 분리(Separation Of Cross-Cutting Concern) : 횡단 관심사에 해당하는 부분(메소드 실행 시작 시간 출력, 메소드 처리 후 시간 출력 등)을 분리해서 한 곳으로 모으는 것을 의미
	 * 	- @Component : @Aspect와 짝, component-scan 시 대상 파악
	 * 	- JoinPoint : 어드바이스(부가기능)이 적용될 수 있는 위치
	 * 	- Advice : 어떤 부가기능(메소드 실행 시작 전, 메소드 실행 후 등)을 언제 사용할지 정의
	 * 		언제?
	 * 		> Before : 조인포인트 전에 실행
	 * 		> After : 조인 포인트에서 처리가 완료된 후 실행
	 * 		> After Returning : 조인 포인트가 정상적으로 종료 후 실행
	 * 		> After Throwing : 조인 포인트에서 예외 발생 시 실행. 예외가 발생안되면 실행 안함
	 * 		> Around : 조인 포인트 전후에 실행
	 * 
	 * AOP(관점지향 프로그래밍(Aspect Oriented Programming))
	 * 1. 관점 지향 프로그래밍의 약자
	 * 		1) 관점 지향 프로그래밍
	 * 		소스코드의 여기저기에 흩어져 있는 횡단 관심사를 중심으로 설계와 구현을 하는 프로그래밍 기법
	 * 		간단하게 설명하면 관점 지향 프로그래밍은 횡단 관심사의 분리를 실현하는 방법
	 * 
	 * 		- 횡단 관심사 
	 * 		> 핵심 비즈니스 로직과 거리가 있지만, 여러 모듈에서 공통적이고 반복적인 처리를 요구하는 내용
	 * 
	 * 		- 횡단 관심사의 분리
	 * 		> 어플리케이션을 개발할 때 횡단 관심사에 해당하는 부분을 분리해서 한곳으로 모으는 것을 의미
	 * 		
	 * 		2) AOP 개발순서
	 * 		- 핵심 비즈니스 로직에만 근거해서 코드를 작성
	 * 		- 주변 로직에 해당하는 관심사들을 분리해서 따로 작성
	 * 		- 핵심 비즈니스 로직 대상 객체에 어떤 관심사들을 결합할 것인지를 결정
	 * 
	 * 		3) AOP 사용 예(로, 보, 트, 에)
	 * 		- 로깅
	 * 		- 보안 적용	
	 * 		- 트랜잭션 관리
	 * 		- 예외 처리
	 * 
	 * 		AOP의 사용된 예에서 우리는 로깅을 이용. 보안은 스프링 시큐리티에서, 트랙잭션 관리는 @Transactional 어노테이션활용, 예외처리는 xml설정
	 * 
	 * 		4) AOP 관련 용어
	 * 		- 어스팩트(Aspect)
	 * 		> AOP의 단위가 되는 횡단 관심사
	 * 
	 * 		- 조인포인트(JoinPoint)
	 * 		> 횡단 관심사가 실행될 지점이나 시점(메소드 실행이나 예외 발생 등)을 말한다
	 * 		> 어디에 적용할 것인지 결정, 메소드? 객체생성 시? 필드 접근시? 등등
	 * 		
	 * 		- 어드바이스(Advice)
	 * 		> 특정 조인 포인트에서 실행되는 코드로, 횡단 관심사를 실제로 구현해서 처리하는 부분
	 * 		> 어떤 부가 기능을 구현할 것인지 결정(Before, After, AfterReturning, AfterThrowing, Around)
	 * 
	 * 		- 위빙(Weaving)
	 * 		> 어플리케이션 코드의 적절한 지점에 Aspect를 적용하는 것을 말한다
	 * 		
	 * 		- 타겟(target)
	 * 		> AOP 처리에 의해 처리 흐름에 변화가 생긴 객체를 말한다
	 * 		> 어떤 대상에 대해서 부가 기능을 설정할 것인지 결정
	 * 
	 * 		5) 스프링 지원 어드바이스 유형(부가기능: Advice)
	 * 		- Before
	 * 		> 조인 프인트 전에 실행
	 * 		> 예외가 발생하는 경우만 제외하고 항상 실행
	 * 
	 * 		- After Returning
	 * 		> 조인 프인트가 정상적으로 종료한 후에 실행
	 * 		> 예외가 발생하면 실행되지 않는다
	 * 
	 * 		- After Throwing
	 * 		> 조인 프인트에서 예외가 발생했을 때 실행
	 * 		> 예외가 발생하지 않고 정상적으로 종료하면 실행되지 않는다
	 * 
	 * 		- After
	 * 		> 조인 프인트에서 처리가 완료된 후에 실행
	 * 		> 예외가 발생하지 않고 정상적으로 종료하면 실행되지 않는다
	 * 
	 * 		- Before
	 * 		> 조인 프인트 전후에 실행
	 * 
	 * 		6) AOP의 기능을 활용하기 위한 설정
	 * 			(1) 의존관계 등록
	 * 			- aspectjrt
	 * 			- aspectjweaver
	 * 		
	 * 			(2) 스프링 AOP 설정
	 * 			- root-context.xml 설정
	 *			: 이곳에서는 AOP를 활성화하기 위한 태그를 작성 	
	 *
	 *	2. 포인트 컷 표현식
	 *	- execution 지시자에 대해서 알아본다
	 *		1) execution 지시자의 표현 방식
	 *		- execution 지시자를 활용해 포인트 컷을 표현한 것
	 *
	 *			포인트 컷 표현 요소
	 *			ex) execution(Board kr.or.ddit.service.IBoardService.BoardService*.read*(..))
	 *			- execution : 지시자
	 *			- Board : 반환값
	 *			- kr.or.ddit.service : 패키지
	 *			- BoardService* : 클래스(타입)
	 *			- read* : 메소드
	 *			- (..) : 인수, 파라미터
	 *
	 * 		2) 포인트컷 표현식에 사용되는 와일드 카드
	 * 		* : 임의의 패키지 1개 계층을 의미 하거나 임의의 인수 1개를 의미
	 * 		.. : 임의의 패키지 0개 이상 계층을 의미하거나 임의의 인수 0개 이상을 의미
	 * 		+ : 클래스명 뒤에 붙여 쓰며, 해당 클래스와 해당 클래스의 서브 클래스, 혹은 구현 클래스 모두를 의미
	 * 
	 * 		3) 포인트 컷 표현식을 적용한 모습
	 * 		- @Before("execution(* kr.or.ddit.service.IBoardService.BoardService*.*(..))")
	 * 		public void startLog(JoinPoint jp) {
	 *			log.info("startLog : " + jp.getSignature()); 		
	 * 		}
	 * 
	 * 	3. Before 어드바이스
	 * 	- 조인포인트 전에 실행. 예외가 발생하는 경우만 제외하고 항상 실행
	 * 
	 * 		AOP는 서비스 내의 비즈니스 로직을 가로채서 컨트롤을 하는데, 이때 프록시를 이용하여 해당 기능을 실행하기 전 후 또는 예외 시에 로그를 출력하는 등의 이벤트를 실행
	 * 
	 * 		[AOP 프록시]
	 * 		클라이언트가 요청한 해당 요청을 서비스 로직으로 전달되기 전 해당 비즈니스 로직 부를 실제 대상인것처럼 위장해서 클라이언트의 요청을 받는다
	 * 		AOP에서 프록시 개념은 대리자이다
	 * 		우리는 서비스에서 이뤄지는 전역의 비즈니스 로직들에서 관심사 별로 위빙을 지정해 해당 서비스의 비즈니스 로직이 실행될때
	 * 		인터페이스 기반의 프록시를 이용하여 설정된 횡단 관심사들을 실행
	 * 		
	 * 		[ch12] 참고
	 */
	
	@Before("execution(* kr.or.ddit.service.IBoardService.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("[@Before] startLog : " + jp.getSignature());
		//getSignature() : 어떤 클래스의 어떤 메소드가 실행되었는지를 보여준다
		//파라미터 타입은 무엇인지 보여준다
		log.info("[@Before] startLog : " + Arrays.toString(jp.getArgs()));
		//getArgs() : 전달 된 파라미터 정보를 보여준다
		//ex) [BoardVO [boardNo=127, title=제목]]
	}
}
