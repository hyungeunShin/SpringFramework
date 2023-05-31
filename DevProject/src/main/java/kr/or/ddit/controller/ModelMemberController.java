package kr.or.ddit.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;

@Controller
public class ModelMemberController {
	
	/*
	 * 6장. 데이터 전달자 모델
	 * - Model 객체 이용
	 * 
	 * 1) 매개변수에 선언된 Model 객체의 addAttribute() 메소드를 호출하여 데이터를 뷰에 전달한다
	 */
	
	@RequestMapping(value="/read01", method=RequestMethod.GET)
	public String read01(Model model) {
		model.addAttribute("userId", "hong");
		model.addAttribute("password", "1234");
		model.addAttribute("email", "aaa@ccc.com");
		model.addAttribute("userName", "홍길동");
		model.addAttribute("birthDay", "1999-09-09");
		
		return "member/read01";
	}
	
	//2) Model 객체에 자바빈즈 클래스 객체를 값으로만 전달할 때는 뷰에서 참조할 이름을 클래스명의 앞글자를 소문자로 변환하여 처리
	@RequestMapping(value="/read02", method=RequestMethod.GET)
	public String read02(Model model) {
		Member member = new Member();
		
		member.setUserId("hong");
		member.setPassword("1234");
		member.setEmail("aaa@ccc.com");
		member.setUserName("홍길동");
		member.setBirthDay("1999-09-09");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1999);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 17);
		
		member.setDateOfBirth(cal.getTime());
		model.addAttribute(member);
		
		return "member/read02";
	}
	
	//3) Model 객체에 자바빈즈 클래스 객체를 특정한 이름을 지정하여 전달 가능
	@RequestMapping(value="/read03", method=RequestMethod.GET)
	public String read03(Model model) {
		Member member = new Member();
		
		member.setUserId("hong");
		member.setPassword("1234");
		member.setEmail("aaa@ccc.com");
		member.setUserName("홍길동");
		member.setBirthDay("1999-09-09");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1999);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 17);
		
		member.setDateOfBirth(cal.getTime());
		model.addAttribute("user", member);
		
		return "member/read03";
	}
	
	//4) Model 객체를 통해 배열과 컬렉션 객체를 전달
	@RequestMapping(value="/read04", method=RequestMethod.GET)
	public String read04(Model model) {
		String[] carArray = {"jeep", "bmw"};
		List<String> carList = new ArrayList<>();
		
		carList.add("jeep");
		carList.add("bmw");
		
		String[] hobbyArray = {"music", "movie"};
		List<String> hobbyList = new ArrayList<>();
		hobbyList.add("music");
		hobbyList.add("movie");
		
		model.addAttribute("carArray", carArray);
		model.addAttribute("carList", carList);
		model.addAttribute("hobbyArray", hobbyArray);
		model.addAttribute("hobbyList", hobbyList);
		
		return "member/read04";
	}
	
	//5) Model 객체를 통해 중첩된 자바빈즈 클래스 객체를 전달
	@RequestMapping(value="/read05", method=RequestMethod.GET)
	public String read05(Model model) {
		Member mem = new Member();
		Address addr = new Address();
		
		addr.setPostCode("123456");
		addr.setLocation("daejeon");
		
		mem.setAddress(addr);
		
		List<Card> cardList = new ArrayList<>();
		
		Card c1 = new Card();
		c1.setNo("1234");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2025);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 17);
		c1.setValidMonth(cal.getTime());
		
		cardList.add(c1);
		
		Card c2 = new Card();
		c2.setNo("1234");
		
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 17);
		c2.setValidMonth(cal.getTime());
		
		cardList.add(c2);
		
		mem.setCardList(cardList);
		
		model.addAttribute("user", mem);
		return "member/read05";
	}
	
	//6) Model 객체를 통해 다양한 타입의 값을 전달 가능
	@RequestMapping(value="/read06", method=RequestMethod.GET)
	public String read06(Model model) {
		Member mem = new Member();
		mem.setUserId("hong");
		mem.setPassword("1234");
		mem.setEmail("aaa@ccc.com");
		mem.setUserName("홍길동");
		mem.setBirthDay("1999-09-09");
		mem.setGender("남자");
		mem.setDeveloper("Y");
		mem.setForeigner(true);
		mem.setNationality("korea");
		mem.setCars("Jeep");
		mem.setHobby("music, movie");
		
		String[] carArray = {"jeep", "bmw"};
		mem.setCarArray(carArray);
		
		List<String> carList = new ArrayList<>();
		carList.add("jeep");
		carList.add("bmw");
		mem.setCarList(carList);
		
		String[] hobbyArray = {"music", "movie"};
		mem.setHobbyArray(hobbyArray);
		
		List<String> hobbyList = new ArrayList<>();
		hobbyList.add("music");
		hobbyList.add("sports");
		mem.setHobbyList(hobbyList);
		
		Address addr = new Address();
		addr.setPostCode("12345");
		addr.setLocation("seoul");
		mem.setAddress(addr);
		
		List<Card> cardList = new ArrayList<>();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2025);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 17);
		
		Card c1 = new Card();
		c1.setNo("1234");
		c1.setValidMonth(cal.getTime());
		cardList.add(c1);
		
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 17);
		
		Card c2 = new Card();
		c2.setNo("5678");
		c2.setValidMonth(cal.getTime());
		cardList.add(c2);
		
		mem.setCardList(cardList);
		
		mem.setDateOfBirth(cal.getTime());
		mem.setIntroduction("ㅎㅇㅎㅇ\nㅂㅇㅂㅇ");
		
		model.addAttribute("user", mem);
		return "member/read06";
	}
}
