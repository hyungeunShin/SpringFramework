package kr.or.ddit.controller.ch14.item03;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.controller.ch14.item03.service.ItemService3;
import kr.or.ddit.vo.Item3;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/item3")
@Slf4j
public class FileUploadController03 {
	/*
	 * 비동기 방식 업로드
	 * - 비동기 방식으로 여러 개의 이미지를 업로드 하는 파일 업로드 기능을 구현
	 * 
	 * 	1. 환경설정
	 * 		1) 의존 관계 정의
	 * 		- commons-io: 파일을 처리하기 위한 의존 라이브러리
	 * 		- imgscalr-lib: 이미지 변환을 처리하기 위한 의존 라이브러리
	 * 		- jackson-databind: json데이터 바인딩을 위한 의존 라이브러리
	 * 
	 */
	//root-context.xml에서 설정한 uplaodPath
	@Resource(name="uploadPath")
	private String resourcePath;
	
	@Inject
	private ItemService3 itemService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String itemRegisterForm() {
		return "ch14_3/register";
	}
	
	//uploadAjax 메소드는 브라우저에서 넘겨받은 파일을 업로드 하는 기능
	@ResponseBody
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		log.info("name : " + file.getOriginalFilename());
		
		String savedName = UploadFileUtils.uploadFile(resourcePath, file.getOriginalFilename(), file.getBytes());
		return new ResponseEntity<String>(savedName, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String item3Register(Item3 item, Model model) {
		String[] files = item.getFiles();
		
		for (int i = 0; i < files.length; i++) {
			log.info("files[" + i + "] : " + files[i]);
		}
		
		itemService.register(item);
		model.addAttribute("msg", "등록이 완료되었습니다");
		return "ch14_3/success";
	}
}
