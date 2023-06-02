package kr.or.ddit.controller.ch06;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxMemberController {
	
	//Ajax 방식 요청 처리 페이지
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String ajaxRegisterForm() {
		return "ch06/ajaxRegisterForm";
	}
	
	@RequestMapping(value="/register/{userId}", method=RequestMethod.GET)
	public ResponseEntity<String> register(@PathVariable("userId") String userId) {
		log.info("id : " + userId);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/register/{userId}/{password}", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister02(
			@PathVariable("userId") String userId,
			@PathVariable("password") String password) {
		log.info("id : " + userId);
		log.info("pw : " + password);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/register03", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister03(@RequestBody Member mem) {
		log.info("mem : " + mem);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/register04", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister04(String userId) {
		//요청본문에서 데이터가 바인딩되지 않아 userId가 null이 된다
		//요청 본문에서 데이터를 가져오려면 @requestBody 어노테이션이 꼭 붙어줘야 한다
		log.info("id : " + userId);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/register05", method=RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister05(String userId, String password) {
		//userId는 쿼리스트링에 담겨져 오는 데이터익 때문에, 일반적인 방식으로 데이터를 받을 수 있지만
		//password는 요청 본문에서 데이터를 바인딩해 받아오지 못하므로 null이 출력
		log.info("id : " + userId);
		log.info("pw : " + password);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/register06/{userId}", method=RequestMethod.POST)
	public ResponseEntity<String> register06(
			@PathVariable("userId") String userId,
			@RequestBody Member mem) {
		log.info("id : " + userId);
		log.info("mem : " + mem);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	//***********************************************************
	@RequestMapping(value="/register07", method=RequestMethod.POST)
	public ResponseEntity<String> register07(@RequestBody List<Member> list) {
		//비동기 처리 시, List 컬렉션으로 데이터를 받을때에는 @RequestBody라는 어노테이션을 이용하여 바인딩한다
		//동기 처리시에는 컨트롤러 메서드 내에서 List타입으로 값을 바인딩 할 수 없지만, 객체 내 존재하는 List타입으로는 데이터를 바인딩 할 수 있다.
		//동기와 비동기 처리 시의 차이점 중요 
		
		for (Member mem : list) {
			log.info("mem : " + mem);
		}
		
		//결과 : 정상
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/register08", method=RequestMethod.POST)
	public ResponseEntity<String> register08(@RequestBody Member mem) {
		log.info("mem : " + mem);
		
		Address addr = mem.getAddress();
		
		if(addr != null) {
			log.info("postcode : " + addr.getPostCode());
			log.info("location : " + addr.getLocation());
		}
		
		//정상
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/register09", method=RequestMethod.POST)
	public ResponseEntity<String> register09(@RequestBody Member mem) {
		log.info("id : " + mem.getUserId());
		log.info("pw : " + mem.getPassword());
		
		List<Card> list = mem.getCardList();
		
		if(list != null) {
			log.info("list 사이즈 : " + list.size());
			
			for (Card card : list) {
				log.info("card : " + card);
			}
		}
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
}
