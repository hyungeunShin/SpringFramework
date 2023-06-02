package kr.or.ddit.controller.ch05;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.FileMember;
import kr.or.ddit.vo.Member;
import kr.or.ddit.vo.MultiFileMember;
import kr.or.ddit.vo.allform.MemberAllForm;
import lombok.extern.slf4j.Slf4j;

/*
 * 5장. 컨트롤러 요청 처리
 * 
 * 1. 컨트롤러 메소드 매개 변수
 * 	Model
 * 	 - 이동 대상에 전달할 데이터를 가지고 있는 인터페이스
 *   RedirectAttributes
 *   - 리다이렉트 대상에 전달할 데이터를 가지고 있는 인터페이스
 *   MultipartFile
 *   - 멀티파트 요청을 사용해 업로드 된 파일 정보를 가지고 있는 인터페이스
 *   BindingResult
 *   - 도메인 클래스의 입력값 검증을 가지고 있는 인터페이스
 *   java.security.principal
 *   - 클라이언트 인증을 위한 사용자 정보를 가지고 있는 인터페이스
 */

@Controller
@Slf4j
public class MemberController {
	/*
	 * 5장 컨트롤러 요청 처리 시작 컨트롤러 메소드
	 * - 페이지를 요청해 테스트를 진행합니다
	 */
	
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm");
		return "ch05/registerForm";
	}
	
	//1
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerByParameter(String userId, String password) {
		log.info("id : " + userId);
		log.info("pw : " + password);
		return "ch05/success";
	}
	
	//2
	@RequestMapping(value="/register/{userId}", method=RequestMethod.GET)
	public String registerByPath(String userId) {
		//결과는 null 출력
		//@pathvariable을 이용하지 않으면 받을 수 가 없다.
		log.info("id : " + userId);
		return "ch05/success";
	}
	
	//3
	@RequestMapping(value="/register01", method=RequestMethod.POST)
	public String register01(String userId, String password, int coin) {
		log.info("id : " + userId);
		log.info("pw : " + password);
		log.info("coin : " + coin);
		return "ch05/success";
	}
	
	//4
	@RequestMapping(value="/register02", method=RequestMethod.POST)
	public String register02(String userId, String password) {
		log.info("id : " + userId);
		log.info("pw : " + password);
		return "ch05/success";
	}
	
	//5
	@RequestMapping(value="/register03", method=RequestMethod.POST)
	public String register03(String password, int coin, String userId) {
		log.info("id : " + userId);
		log.info("pw : " + password);
		log.info("coin : " + coin);
		return "ch05/success";
	}
	
	//6
	@RequestMapping(value="/register04", method=RequestMethod.POST)
	public String register04(String userId, String password, String coin) {
		log.info("id : " + userId);
		log.info("pw : " + password);
		log.info("coin : " + coin);
		return "ch05/success";
	}
	
	//7
	@RequestMapping(value="/register05", method=RequestMethod.POST)
	public String register05(String userId, String password, int coin) {
		log.info("id : " + userId);
		log.info("pw : " + password);
		log.info("coin : " + coin);
		return "ch05/success";
	}
	
	/*
	 * 요청 데이터 처리 어노테이션
	 * 
	 * @PathVariables
	 * - URL 에서 경로 변수 값을 가져오기 위한 어노테이션
	 * @RequestParam 
	 * - 요청 파라미터값을 가져오기 위한 어노테이션
	 * @RequestHeader
	 * - 요청 헤더 값을 가져오기 위한 어노테이션
	 * @CookieValue
	 * - 쿠키값을 가져오기 위한 어노테이션
	 */
	
	@RequestMapping(value="/register/{userId}/{coin}", method=RequestMethod.GET)
	public String registerByPath(
			@PathVariable("userId") String userId,
			@PathVariable("coin") int coin) {
		log.info("id : " + userId);
		log.info("coin : " + coin);
		return "ch05/success";
	}
	
	@RequestMapping(value="/register0101", method=RequestMethod.POST)
	public String register0101(String userId) {
		log.info("id : " + userId);
		return "ch05/success";
	}
	
	//클라이언트에서 선언된 필드명은 userId 인데 서버 컨트롤러 메소드에서 받는 필드명이 id이면 받을 수 없다
	//정확하게 각 필드명이 일치했을때만 받을 수 있다
	@RequestMapping(value="/register0201", method=RequestMethod.POST)
	public String register0201(String id) {
		//null이 나옴
		log.info("id : " + id);
		return "ch05/success";
	}
	
	//데이터를 받는 필드명은 동일하게 userId로 하고 사용하는 변수명은 달리 할 수 있다.
	//우리가 페이징을 구현할 때 page를 넘기는 방법을 @requestparam을 써서 구현
	@RequestMapping(value="/register0202", method=RequestMethod.POST)
	public String register0202(@RequestParam("userId") String id) {
		//null이 나옴
		log.info("id : " + id);
		return "ch05/success";
	}
	
	/*
	 * 4. 요청 처리 자바 빈즈
	 */
	
	@RequestMapping(value="/beans/register01", method=RequestMethod.POST)
	public String registerJavaBeans01(Member mem) {
		log.info("mem : " + mem);
		return "ch05/success";
	}
	
	@RequestMapping(value="/beans/register02", method=RequestMethod.POST)
	public String registerJavaBeans02(Member mem, int coin) {
		log.info("mem : " + mem);
		log.info("coin : " + coin);
		return "ch05/success";
	}
	
	@RequestMapping(value="/beans/register03", method=RequestMethod.POST)
	public String registerJavaBeans03(int uid, Member mem) {
		log.info("uid : " + uid);
		log.info("mem : " + mem);
		
		return "ch05/success";
	}
	
	/*
	 * 5. Date 타입 처리
	 * - 스프링 MVC는 Date 타입의 데이터를 처리하는 여러 방법을 제공
	 * 
	 * 받는 매개변수가 String 타입이면 button7,8,9 가능
	 * 
	 */
	
	@RequestMapping(value="/registerByGet01", method=RequestMethod.GET)
	public String registerByGet01(String userId, 
			@DateTimeFormat(pattern="yyyyMMdd") Date dateOfBirth) {
		log.info("userId : " + userId);
		log.info("dateOfBirth : " + dateOfBirth);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerByGet02", method=RequestMethod.GET)
	public String registerByGet02(Member mem) {
		log.info("mem : " + mem);
		return "ch05/success";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Member mem) {
		log.info("mem : " + mem);
		return "ch05/success";
	}
	
	/*
	 * 6. @DateTimeFormat
	 * - @DateTimeFormat 어노테이션의 pattern 속성값에 원하는 날짜 형식을 지정할 수 있다
	 * 
	 * > 테스트는 /register02를 요청하고 파라미터로 받아 줄, Member 클래스의 날짜를 받는 필드의 어노테이션 설정
	 */
	
	/*
	 * 7. 폼 방식 요청 처리
	 */
	
	@RequestMapping(value="/registerUserId", method=RequestMethod.POST)
	public String registerUserId(String userId) {
		log.info("userId : " + userId);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerMemberUserId", method=RequestMethod.POST)
	public String registerMemberUserId(Member mem) {
		log.info("mem : " + mem);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerPassword", method=RequestMethod.POST)
	public String registerPassword(String password) {
		log.info("password : " + password);
		return "ch05/success";
	}
	
	//라디오버튼
	@RequestMapping(value="/registerRadio", method=RequestMethod.POST)
	public String registerRadio(String gender) {
		log.info("gender : " + gender);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerSelect", method=RequestMethod.POST)
	public String registerSelect(String nationality) {
		log.info("nationality : " + nationality);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerMultiSelect01", method=RequestMethod.POST)
	public String registerMultiSelect01(String cars) {
		log.info("cars : " + cars);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerMultiSelect02", method = RequestMethod.POST)
	public String registerMultiSelect02(String[] carArray) {
		if(carArray != null) {
			log.info("배열길이 : " + carArray.length);
			for (int i = 0; i < carArray.length; i++) {
				log.info(i + " : " + carArray[i]);
			}
		} else {
			log.info("carArray is null");
		}
		
		return "ch05/success";
	}
	
	//List로는 데이터를 받을 수 없다
	//가져오려면 배열 형태를 이용하거나 객체를 이용하여 데이터를 얻어온다
	@RequestMapping(value="/registerMultiSelect03", method = RequestMethod.POST)
	public String registerMultiSelect03(ArrayList<String> carList) {
		if(carList != null && carList.size() > 0) {
			log.info("리스트 길이 : " + carList.size());
			for (int i = 0; i < carList.size(); i++) {
				log.info(i + " : " + carList.get(i));
			}
		} else {
			log.info("carList is null");
		}
		
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerCheckbox01", method=RequestMethod.POST)
	public String registerCheckbox01(String hobby) {
		log.info("hobby : " + hobby);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerCheckbox02", method=RequestMethod.POST)
	public String registerCheckbox02(String[] hobbyArray) {
		if(hobbyArray != null) {
			for (int i = 0; i < hobbyArray.length; i++) {
				log.info(i + " : " + hobbyArray[i]);
			}
		} else {
			log.info("hobbyArray is null");
		}
		
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerCheckbox03", method=RequestMethod.POST)
	public String registerCheckbox03(ArrayList<String> hobbyList) {
		//받는 타입을 List로 하게 되면 no primary or default constructor found for interface java.util.List 에러가 발생
		//스프링에서는 List타입으로 데이터를 받는데 문제가 있다(데이터 바인딩 안됨)
		//리스트와 같은 형태의 값을 받으려면 Stirng[]로 여러 데이터를 받아서 list에 담는 방법이나
		//객체를 이용하여 데이터를 바인딩 해야한다
 		if(hobbyList != null && hobbyList.size() > 0) {
			for (int i = 0; i < hobbyList.size(); i++) {
				log.info(i + " : " + hobbyList.get(i));
			}
		} else {
			log.info("hobbyList is null");
		}
		
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerCheckbox04", method=RequestMethod.POST)
	public String registerCheckbox04(String developer) {
		//값이 존재하면 value속성이 들어오고 
		//값이 존재하지 않으면 null이 넘어온다
		log.info("developer : " + developer);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerCheckbox05", method=RequestMethod.POST)
	public String registerCheckbox05(boolean foreigner) {
		//개인정보동의와 같은 기능 (스위칭)을 만들때 사용
		//체크된 값이 존재하면 value 속성에 설정된 true가 넘어오고
		//체크된 값이 존재하지 않으면 false가 넘어온다
		log.info("foreigner : " + foreigner);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerAddress", method=RequestMethod.POST)
	public String registerAddress(Address addr) {
		if(addr != null) {
			log.info("addr : " + addr);
		} else {
			log.info("addr is null");
		}
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerUserAddress", method=RequestMethod.POST)
	public String registerUserAddress(Member mem) {
		//name이 postCode, location일 때는 500에러가 나타난다
		//이를 해결하기 위해서는
		//name="address.postCode"
		//name="address.location"
		log.info("mem : " + mem);
		
		Address address = mem.getAddress();
		if(address != null) {
			log.info(address.getPostCode());
			log.info(address.getLocation());
		} else {
			log.info("address is null");
		}
		
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerUserCardList", method=RequestMethod.POST)
	public String registerUserCardList(Member mem) {
		List<Card> list = mem.getCardList();
		
		if(list != null) {
			log.info("리스트 사이즈  : " + list.size());
			
			for (int i = 0; i < list.size(); i++) {
				log.info("카드번호 : " + list.get(i).getNo());
				log.info("카드 유효일 : " + list.get(i).getValidMonth());
			}
		} else {
			log.info("list is null");
		}
		
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerTextArea", method=RequestMethod.POST)
	public String registerTextArea(String introduction) {
		log.info("introduction : " + introduction);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerDate01", method=RequestMethod.POST)
	public String registerDate01(Date dateOfBirth) {
		//결과로 Date 타입의 값을 받기 위해서는 default 2023/05/24 형태로 값을 설정해서 보내야한다
		//내가 원하는 Date타입의 형식이 존재한다면 @DateTimeFormat(pattern=)속성을 이용하여 
		//원하는 패턴을 설정하여 날짜 형식의 값을 넘겨받는다(pattern='yyyyMMdd', pattern='yyyy-MM-dd')
		log.info("dateOfBirth : " + dateOfBirth);
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerAllForm", method=RequestMethod.GET)
	public String registerAllForm() {
		return "ch05/registerAllForm";
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String registerUser(MemberAllForm member, boolean foreigner, Model model) {
		//전체 폼 페이지에서 넘겨받은 데이터 모두를 콘솔에 출력 후,
		//member 폴더 아래에 있는 result 페이지로 전달
		//result 페이지에서 넘긴 모든 데이터를 영어로 된 값이 아닌 한글로 출력
		//input 요소에 value로 설정되어 있는 값은 한글이 아닌 영어 설정되어 있어야한다
		// ex) 성별 : 남자
		//     국적 : 대한민국
		//     개발자 여부 : 개발자
		//     외국인 여부 : 한국인 / 외국인
		//     소유차량 : BMW, VOLVO
		//     취미 : 운동, 영화, 음악
		//	   .....
		// >> 콘솔에서도 출력하고, result 페이지에서도 출력(jstl 활용)
		
		log.info("아이디 : " + member.getUserId());
		log.info("비밀번호 : " + member.getPassword());
		log.info("이름 : " + member.getUserName());
		log.info("이메일 : " + member.getEmail());
		log.info("생년월일 : " + member.getDateOfBirth());
		
		
		String gender = member.getGender();
		if(gender.equals("male")) {
			log.info("성별 : 남자");
		} else if(gender.equals("female")) {
			log.info("성별 : 여자");
		} else {
			log.info("성별 : 그 외");
		}
		
		String developer = member.getDeveloper();
		if(developer.equals("Y")) {
			log.info("개발자 여부 : 개발자");
		} else {
			log.info("개발자 여부 : 개발자 아님");
		}
		
		if(foreigner) {
			log.info("외국인 여부 : 외국인");
		} else {
			log.info("외국인 여부 : 한국인");
		}
		
		String nation = member.getNationality();
		switch(nation) {
		case "korea" :
			log.info("국적 : 대한민국");
			break;
		case "germany" :
			log.info("국적 : 독일");
			break;
		case "austrailia" :
			log.info("국적 : 호주");
			break;
		case "canada" :
			log.info("국적 : 캐나다");
			break;
		case "usa" :
			log.info("국적 : 미국");
			break;
		}
		
		String[] cars = member.getCars();
		for (int i = 0; i < cars.length; i++) {
			log.info("보유 차량 : " + cars[i]);
		}
		
		String[] hobby = member.getHobby();
		for (int i = 0; i < hobby.length; i++) {
			if(hobby[i].equals("sports")) {
				log.info("취미 : 스포츠");
			} else if(hobby[i].equals("music")) {
				log.info("취미 : 음악");
			} else if(hobby[i].equals("movie")) {
				log.info("취미 : 영화");
			} 
			
		}
		
		log.info(member.getAddress().getPostCode() + "," + member.getAddress().getLocation());
		
		List<Card> list = member.getCardList();
		for (int i = 0; i < list.size(); i++) {
			log.info("카드 번호 : " + list.get(i).getNo());
			log.info("카드 유효년월 : " + list.get(i).getValidMonth());
		}
		
		log.info("자기소개 : " + member.getIntroduction());
		
		model.addAttribute("member", member);
		return "ch05/result";
	}
	
	/*
	 * 8. 파일업로드 폼 방식 요청 처리
	 * 
	 * > 파일 업로드 폼 방식 요청 처리를 위한 의존 라이브러리 추가
	 * 
	 * > commons-fileupload, commons-io 라이브러리가 필요하다.
	 * > web.xml에 모든 경로에 대한 MultipartFilter 등록
	 * 
	 * ***** multi-part 에러가 발생하거나 null 에러가 발생하거나 multipartFile 에러가 난다면
	 * > server > context.xml 내에서
	 * context 태그 내 옵션 추가
	 * allowCasualMultipartParsing="true" path="/"
	 * > local 환경에서는 세팅이 가능
	 */
	@RequestMapping(value="/registerFile01", method=RequestMethod.POST)
	public String registerFile01(MultipartFile picture) {
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerFile02", method=RequestMethod.POST)
	public String registerFile02(String userId, String password, MultipartFile picture) {
		log.info("userId : " + userId);
		log.info("password : " + password);
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerFile03", method=RequestMethod.POST)
	public String registerFile03(Member mem, MultipartFile picture) {
		log.info("mem : " + mem);
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerFile04", method=RequestMethod.POST)
	public String registerFile04(FileMember mem) {
		log.info("mem : " + mem);
		log.info("userId : " + mem.getUserId());
		log.info("password : " + mem.getPassword());
		
		MultipartFile picture = mem.getPicture();
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerFile05", method=RequestMethod.POST)
	public String registerFile05(MultipartFile picture1, MultipartFile picture2) {
		log.info("originalName1 : " + picture1.getOriginalFilename());
		log.info("size1 : " + picture1.getSize());
		log.info("contentType1 : " + picture1.getContentType());
		
		log.info("originalName2 : " + picture2.getOriginalFilename());
		log.info("size2 : " + picture2.getSize());
		log.info("contentType2 : " + picture2.getContentType());
		
		return "ch05/success";
	}
	
	//list는 못받는다
	@RequestMapping(value="/registerFile06", method=RequestMethod.POST)
	public String registerFile06(List<MultipartFile> pictureList) {
		log.info("리스트 사이즈 : " + pictureList.size());
		
		for (int i = 0; i < pictureList.size(); i++) {
			log.info(i + "originalName : " + pictureList.get(i).getOriginalFilename());
			log.info(i + "size : " + pictureList.get(i).getSize());
			log.info(i + "contentType : " + pictureList.get(i).getContentType());
		}
		
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerFile07", method=RequestMethod.POST)
	public String registerFile07(MultiFileMember mem) {
		List<MultipartFile> list = mem.getPictureList();
		log.info("리스트 크기 : " + list.size());
		
		for (int i = 0; i < list.size(); i++) {
			log.info(i + "originalName : " + list.get(i).getOriginalFilename());
			log.info(i + "size : " + list.get(i).getSize());
			log.info(i + "contentType : " + list.get(i).getContentType());
		}
		
		log.info("mem : " + mem);
		
		return "ch05/success";
	}
	
	@RequestMapping(value="/registerFile08", method=RequestMethod.POST)
	public String registerFile08(MultipartFile[] pictureList) {
		log.info("크기 : " + pictureList.length);
		
		for (int i = 0; i < pictureList.length; i++) {
			log.info(i + "originalName : " + pictureList[i].getOriginalFilename());
			log.info(i + "size : " + pictureList[i].getSize());
			log.info(i + "contentType : " + pictureList[i].getContentType());
		}
		
		return "ch05/success";
	}
}
