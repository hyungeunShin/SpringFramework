package kr.or.ddit;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}
	
	/*
	 * 4장. 컨트롤러 응답
	 * 
	 * 1) void 타입
	 * - 호출 하는 URL과 동일한 뷰 이름을 나태내기 위해 사용
	 */
	
	//요청 경로(/goHome0101)와 동일한 뷰(/goHome0101.jsp)
	@RequestMapping(value="/goHome0101", method=RequestMethod.GET)
	public void goHome0101() {
		log.info("hoHome0101() 실행");
	}
	
	//요청경로(/sub/goHome0102)와 동일한 뷰(/sub/goHome0102.jsp)
	@RequestMapping(value="/sub/goHome0102", method=RequestMethod.GET)
	public void goHome0102() {
		log.info("goHome0102() 실행");
	}
	
	/*
	 * 2) String 타입
	 * - 뷰 파일의 경로와 파일 이름을 나타내기 위해 사용
	 */
	
	//반환값이 home0201이므로 뷰 (/hone0201.jsp)를 가리킨다
	@RequestMapping(value="/goHome0201", method=RequestMethod.GET)
	public String goHome0201() {
		log.info("goHome0201() 실행...");
		return "home0201";
	}
	
	//반환값이 home0202이므로 뷰(/home0202.jsp)
	@RequestMapping(value="/goHome0202", method=RequestMethod.GET)
	public String goHome0202() {
		log.info("goHome0202() 실행...");
		return "home0202";
	}
	
	@RequestMapping(value="/sub/goHome0203", method=RequestMethod.GET)
	public String goHome0203() {
		log.info("goHome0203()");
		return "sub/home0203";
	}
	
	//반환값이 redirect:/로 시작하면 리다이렉트 방식
	@RequestMapping(value="/sub/goHome0204", method=RequestMethod.GET)
	public String goHome0204() {
		return "redirect:/sub/goHome0203";
	}
	
	// '/'로 시작하면 웹 애플리케이션의 컨텍스트 경로에 영향을 받지 않는 절대 경로를 의미한다
	//해당경로 : D드라이브 > workspace > .metadata > .pluguns > ,,, > devproject > web-inf > views > sub > ...jsp
	@RequestMapping(value="/sub/goHome0205", method=RequestMethod.GET)
	public String goHome0205() {
		return "/sub/home0205";
	}
	
	/*
	 * 3) 자바빈즈 클래스 타입(vo)
	 * - JSON 객체 타입의 데이터를 만들어서 반환하는 용도로 사용
	 * 
	 * 1) ResponseBody를 이용하는 방식
	 * 2) RestController를 이용하는 방식
	 * 
	 * @responsebody를 지정하지 않으면 HTTP 404에러가 발생한다(jackson-databind 라이브러리가 설정되어 있지 않은 경우도 포함)
	 * @responsebody가 객체를 리턴하여 객체를 응답 데이터로 보내는 역할을 한다
	 * @responsebody의 리턴 default 데이터 형식은 json이다
	 * @responsebody 대신에 @restcontroller로 대체 가능
	 */
	
	@ResponseBody
	@RequestMapping(value="/goHome0301", method=RequestMethod.GET)
	public Member goHome0301() {
		log.info("member");
		//객체를 리턴하여 데이터를 확인하기 때문에 페이지 정보가 없다
		Member mem = new Member();
		return mem;
	}
	
	/*
	 * 4) 컬렉션 List 타입
	 * - Json 객체 배열 타입의 데이터를 만들어서 반환하는 용도로 사용
	 * 
	 * 1) ResponseBody를 이용하는 방식
	 * 2) RestController를 이용하는 방식
	 */
	
	//반환값이 컬렉션 list 타입이면 Json 객체 배열 타입으로 자동변환된다
	@ResponseBody
	@RequestMapping(value="/goHome0401", method=RequestMethod.GET)
	public List<Member> goHome0401() {
		log.info("goHome0401");
		List<Member> list = new ArrayList<Member>();
		
		Member mem = new Member();
		list.add(mem);
		Member mem2 = new Member();
		mem2.setUserName("abc");
		mem2.setPassword("9876");
		list.add(mem2);
		
		return list;
	}
	
	/*
	 * 5) 컬렉션 map 타입
	 * - map 형태의 컬렉션 자료를 json 객체 타입의 데이터로 만들어서 반환하는 용도로 사용
	 * 
	 * 1) ResponseBody를 이용하는 방식
	 * 2) RestController를 이용하는 방식
	 */
	@ResponseBody
	@RequestMapping(value="/goHome0501", method=RequestMethod.GET)
	public Map<String, Member> goHome0501() {
		log.info("goHome0501");
		Map<String, Member> map = new HashMap<String, Member>();
		Member mem1 = new Member();
		Member mem2 = new Member();
		
		map.put("key1", mem1);
		map.put("key2", mem2);
		
		return map;
	}
	
	/*
	 * 6) responseenity<void> 타입
	 * - response 할 때 http 헤더 정보와 내용을 가공하는 용도로 사용
	 * 
	 * 1) ResponseBody를 이용하는 방식
	 * 2) RestController를 이용하는 방식
	 */
	
	//200 OK 상태코드를 전송
	//Void 타입은 아무런 형태가 아닌데 제네릭타입의 뭔가는 채워야 해서 채우는 placeholder 같은 느낌...
	@ResponseBody
	@RequestMapping(value="/goHome0601", method=RequestMethod.GET)
	public ResponseEntity<Void> goHome0601() {
		//내가 요청한 url로 응답이 나가면서 응답 데이터로 아무런 값이 전달되지 않는다
		//해당 url 요청 후 , 브라우저에서 개발자 도구를 이용해 네트워크 탭을 확인해보면 응답으로 url이 응답으로 나간걸 확인 할 수 있다
		//이때 상태코드 200 으로 정상 응답이 나간걸 확인 가능
		//그리고 다른 기능으로 아무런 형태 없이 응답으로 나가지만 응답의 대한 header 정보를 변경하고자 할때 사용할 수 있다
		return new ResponseEntity<Void>(HttpStatus.OK); 
	}
	
	/*
	 * 7) responseentity<String> 타입
	 * - response할 때 http 헤더 정보와 문자열 데이터를 전달하는 용도
	 * 
	 * 1) ResponseBody를 이용하는 방식
	 * 2) RestController를 이용하는 방식
	 */
	
	//SUCCESS라는 메세지와 상태코드 200을 전송
	@ResponseBody
	@RequestMapping(value="/goHome0701", method=RequestMethod.GET)
	public ResponseEntity<String> goHome0701() {
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	/*
	 * 8) responseentity<자바빈즈 클래스> 타입
	 * - response할 때 http 헤더 정보와 자바빈즈 데이터를 전달하는 용도
	 * 
	 * 1) ResponseBody를 이용하는 방식
	 * 2) RestController를 이용하는 방식
	 */
	
	//객체의 Json 타입의 데이터와 200 ok 상태코드 전송
	@ResponseBody
	@RequestMapping(value="/goHome0801", method=RequestMethod.GET)
	public ResponseEntity<Member> goHome0801() {
		Member mem = new Member();
		
		return new ResponseEntity<Member>(mem, HttpStatus.OK);
	}
	
	/*
	 * 9) responseentity<list> 타입
	 * - response할 때 http 헤더 정보와 객체 배열 데이터를 전달하는 용도
	 * 
	 * 1) ResponseBody를 이용하는 방식
	 * 2) RestController를 이용하는 방식
	 */
	
	//객체의 Json 객체 배열 타입의 데이터와 200 ok 상태코드를 전송
	@ResponseBody
	@RequestMapping(value="/goHome0901", method=RequestMethod.GET)
	public ResponseEntity<List<Member>> goHome0901() {
		List<Member> list = new ArrayList<Member>();
		
		Member mem1 = new Member();
		Member mem2 = new Member();
		list.add(mem1);
		list.add(mem2);
		
		return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
	}
	
	/*
	 * 10) responseEntity<map> 타입
	 * - response할 때 http 헤더 정보와 객체 데이터를 map타입 형태로 전달하는 용도
	 * 
	 * 1) ResponseBody를 이용하는 방식
	 * 2) RestController를 이용하는 방식
	 */
	
	// 객체의 json 타입의 데이터와 200 ok를 전송
	@ResponseBody
	@RequestMapping(value="/goHome1001", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Member>> goHome1001() {
		Map<String, Member> map = new HashMap<String, Member>();
		Member mem1 = new Member();
		Member mem2 = new Member();
		
		map.put("key1", mem1);
		map.put("key2", mem2);
		
		return new ResponseEntity<Map<String,Member>>(map, HttpStatus.OK);
	}
	
	/*
	 * 11) responseEntity<Byte[]> 타입
	 * - response 할 때 http 헤더 정보와 바이너리 파일 데이터를 전달하는 용도
	 * - 파일을 처리하기 위해서 의존라이브러리 commons-io를 추가
	 * 
	 * > 무료/유료 이미지 다운로드 홈페이지를 사용해보면 이미지 미리보기 또는 미리보기 후 다운로드를 할 수 있는 기능이 제공된다
	 * 이와 같은 리턴 타입의 형태를 설정해서 내보내는 것과 같다.
	 * 
	 * 1) ResponseBody를 이용하는 방식
	 * 2) RestController를 이용하는 방식
	 */
	
	@ResponseBody
	@RequestMapping(value="/goHome1101", method= RequestMethod.GET)
	public ResponseEntity<byte[]> goHome1101() {
		
		ResponseEntity<byte[]> en = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		//String path = request.getServletContext().getRealPath("/resources/images") + "/" + filename;
		
		//파일 경로를 나타내는 2가지 방법
		//방법 1('\\')
		//방법 2 ('/')
		try(InputStream in = new FileInputStream("D:/A_TeachingMaterial/WEBPRO/webpr/src/main/webapp/images/2002.jpg")) {
			headers.setContentType(MediaType.IMAGE_JPEG);
			en = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			en = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return en;
	}
	
	@ResponseBody
	@RequestMapping(value="/goHome1102", method=RequestMethod.GET)
	public ResponseEntity<byte[]> goHome1102() {
		String fileName = "data.zip";
		ResponseEntity<byte[]> en = null;
		HttpHeaders headers = new HttpHeaders();
		
		try(InputStream in = new FileInputStream("D:/A_TeachingMaterial/WEBPRO/webpr/src/main/webapp/images/" + fileName)) {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			en = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			en = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return en;
	}
}
