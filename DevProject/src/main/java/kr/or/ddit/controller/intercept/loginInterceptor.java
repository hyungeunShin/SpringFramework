package kr.or.ddit.controller.intercept;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class loginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String USER_INFO = "userInfo";
	
	//지정된 컨트롤러의 동작 이전에 가로채는 역할로 사용
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("preHandle");
		
		String requestURL = request.getRequestURL().toString(); //우리가 요청한 url http://localhost/login1
		String requestURI = request.getRequestURI();			//login1
		
		log.info("requestURL : " + requestURL);
		log.info("requestURI : " + requestURI);
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean : " + method.getBean());		//kr.or.ddit.controller.login.LoginController
		log.info("method : " + methodObj);			//public java.lang.string.kr.or.ddit.controller.ch17.LoginController.loginForm()
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(USER_INFO) != null) {
			session.removeAttribute(USER_INFO);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info("postHandle");
		
		String requestURL = request.getRequestURL().toString();
		String requestURI = request.getRequestURI();
		
		log.info("requestURL : " + requestURL);
		log.info("requestURI : " + requestURI);
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean : " + method.getBean());
		log.info("method : " + methodObj);
		
		HttpSession session = request.getSession();
		
		//컨트롤러 메소드를 거쳤다가 postHandle로 넘어오면서 전달된 user라는 키에 value로 member가 담긴 값이 Model에 담겨져 있다.
		//'user'로 넘긴 값이 로그인 후 인증된 회원 1명의 정보가 담긴 MemberVO 자바빈즈 객체가된다
		//객체가 null이면 메인화면으로 리다이렉트 처리한다
		ModelMap modelMap = modelAndView.getModelMap();
		Object member = modelMap.get("user");
		
		if(member != null) {
			log.info("member : " + member);
			session.setAttribute(USER_INFO, member);
			response.sendRedirect("/");
		}
	}
	
	//DispatcherServlet의 화면 처리가 완료된 상태에서 처리
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info("afterCompletion");
		
		String requestURL = request.getRequestURL().toString();
		String requestURI = request.getRequestURI();
		
		log.info("requestURL : " + requestURL);
		log.info("requestURI : " + requestURI);
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean : " + method.getBean());
		log.info("method : " + methodObj);
	}
}
