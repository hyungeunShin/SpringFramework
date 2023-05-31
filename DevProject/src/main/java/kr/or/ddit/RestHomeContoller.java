package kr.or.ddit;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

/*
 * @restcontroller는 @controller와 @responsebody를 포함하고 있는 어노테이션이다
 */
@RestController
@Slf4j
public class RestHomeContoller {
	//반환값이 객체 타입이면 JSON 타입으로 자동변환
	@RequestMapping(value="/goRestHome0301", method=RequestMethod.GET)
	public Member restHome0301() {
		log.info("restHome0301");
		return new Member();
	}
	
	//반환값이 컬렉션 list 타입이면 json 자동변환
	@RequestMapping(value="/goRestHome0401", method=RequestMethod.GET)
	public List<Member> restHome0401() {
		List<Member> list = new ArrayList<Member>();
		
		for(int i = 0; i < 3; i++) {
			Member mem = new Member();
			list.add(mem);
		}
		
		for (Member member : list) {
			System.out.println(member);
		}
		
		return list;
	}
	
	@RequestMapping(value="/goRestHome0501", method=RequestMethod.GET)
	public Map<String, Member> restHome0501() {
		Map<String, Member> map = new HashMap<String, Member>();
		
		Member mem1 = new Member();
		Member mem2 = new Member();
		
		map.put("key1", mem1);
		map.put("key2", mem2);
		
		return map;
	}
	
	@RequestMapping(value="/goRestHome0601", method=RequestMethod.GET)
	public ResponseEntity<Void> restHome0601() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//SUCCESS라는 메세지와 상태코드 200을 전송
	@RequestMapping(value="/goRestHome0701", method=RequestMethod.GET)
	public ResponseEntity<String> restHome0701() {
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	//객체의 Json 타입의 데이터와 200 ok 상태코드 전송
	@RequestMapping(value="/goRestHome0801", method=RequestMethod.GET)
	public ResponseEntity<Member> restHome0801() {
		Member mem = new Member();
		
		return new ResponseEntity<Member>(mem, HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome0901", method=RequestMethod.GET)
	public ResponseEntity<List<Member>> restHome0901() {
		List<Member> list = new ArrayList<Member>();
		
		Member mem1 = new Member();
		Member mem2 = new Member();
		
		list.add(mem1);
		list.add(mem2);
		
		return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
	}
	
	// 객체의 json 타입의 데이터와 200 ok를 전송
	@RequestMapping(value="/goRestHome1001", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Member>> restHome1001() {
		Map<String, Member> map = new HashMap<String, Member>();
		Member mem1 = new Member();
		Member mem2 = new Member();
		
		map.put("key1", mem1);
		map.put("key2", mem2);
		
		return new ResponseEntity<Map<String,Member>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome1101", method=RequestMethod.GET)
	public ResponseEntity<byte[]> restHome1101() {
		ResponseEntity<byte[]> en = null;
		HttpHeaders headers = new HttpHeaders();
		
		try(InputStream in = new FileInputStream("D:/A_TeachingMaterial/WEBPRO/webpr/src/main/webapp/images/2002.jpg")) {
			headers.setContentType(MediaType.IMAGE_JPEG);
			en = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			en = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return en;
	}
	
	//파일의 데이터를 브라우저 다운로드 받도록 한다
	@RequestMapping(value="/goRestHome1102", method=RequestMethod.GET)
	public ResponseEntity<byte[]> restHome1102() {
		String fileName = "data.zip";
		ResponseEntity<byte[]> en = null;
		HttpHeaders headers = new HttpHeaders();
		
		try(InputStream in = new FileInputStream("D:/A_TeachingMaterial/WEBPRO/webpr/src/main/webapp/images/2002.jpg")) {
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
