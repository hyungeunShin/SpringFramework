package kr.or.ddit.controller.intercept;

import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class loginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String USER_INFO = "member";
	
	//지정된 컨트롤러의 동작 이전에 가로채는 역할로 사용
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("preHandle");
		
		String requestURI = request.getRequestURI();			
		
		log.info("PreHandle에서 요청한 URI : " + requestURI);
		
		HttpSession session = request.getSession();
		
		log.info("session : " + session.getAttribute(USER_INFO));
		
		if(session.getAttribute(USER_INFO) == null) {
			Cookie cookie = new Cookie("requestURI", requestURI);
			cookie.setMaxAge(60 * 60);
			cookie.setPath("/member/login");
			response.addCookie(cookie);
			
			response.sendRedirect("/member/signInForm");
			
			return false;
		}
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info("postHandle");
		
		String requestURI = request.getRequestURI();
		
		log.info("postHandle에서 요청한 URI : " + requestURI);
		
		//response.sendRedirect(requestURI);
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
