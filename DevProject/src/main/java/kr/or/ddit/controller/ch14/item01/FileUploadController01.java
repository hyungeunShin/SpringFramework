package kr.or.ddit.controller.ch14.item01;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.controller.ch14.item01.service.IItemService;
import kr.or.ddit.vo.Item;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/item")
@Slf4j
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
	
	//root-context.xml에서 bean으로 등록한 path 경로(value값)
	@Resource(name="uploadPath")
	private String resourcesPath;
	
	@Inject
	private IItemService itemService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String itemRegisterForm() {
		return "ch14/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String itemRegister(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();
		log.info("name : " + file.getOriginalFilename());
		log.info("size : " + file.getSize());
		log.info("contentType : " + file.getContentType());
		
		//넘겨받은 파일을 이용하여 파일 업로드를 진행
		//return : UUID + "_" + 원본 파일명
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		item.setPictureUrl(createdFileName);
		itemService.register(item);
		model.addAttribute("msg", "등록이 완료되었습니다");
		
		return "ch14/success";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String itemList(Model model) {
		List<Item> list = itemService.list();
		model.addAttribute("itemList", list);
		return "ch14/list";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String itemModifyForm(int itemId, Model model) {
		Item item = itemService.read(itemId);
		
		model.addAttribute("item", item);
		return "ch14/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();
		
		if(file != null && file.getSize() > 0) {
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
			log.info("contentType : " + file.getContentType());
			
			String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
			item.setPictureUrl(createdFileName);
		}
		
		itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다");
		return "ch14/success";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String itemRemoveForm(int itemId, Model model) {
		Item item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "ch14/remove";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(int itemId, Model model) {
		itemService.remove(itemId);
		model.addAttribute("msg", "삭제되었습니다");
		return "ch14/success";
	}
	
	@RequestMapping(value="/display")
	public ResponseEntity<byte[]> displayFile(int itemId) {
		ResponseEntity<byte[]> entity = null;
		
		String fileName = itemService.getPicture(itemId);
		log.info("filename : " + fileName);
		
		try(InputStream in = new FileInputStream(resourcesPath + File.separator + fileName)) {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1); //확장자 추출
			MediaType mt = getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
		
			if(mt != null) {
				headers.setContentType(mt);
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	private MediaType getMediaType(String formatName) {
		if(formatName != null) {
			if(formatName.toUpperCase().equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}
			if(formatName.toUpperCase().equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}
			if(formatName.toUpperCase().equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
		}
		
		return null;
	}
	
	//String path = request.getServletContext().getRealPath("/resources/images");
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		System.out.println("path : " + resourcesPath);
		UUID uuid = UUID.randomUUID();
		String createdFileName = uuid.toString() + "_" + originalName;
		
		File file = new File(resourcesPath);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		File target = new File(resourcesPath, createdFileName);
		
		//파일복사
		FileCopyUtils.copy(fileData, target);
		
		return createdFileName;
	}
}
