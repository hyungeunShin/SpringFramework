package kr.or.ddit.controller.ch14.item02;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.controller.ch14.item02.service.ItemService2;
import kr.or.ddit.vo.Item2;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/item2")
@Slf4j
public class FileUploadController02 {
	/*
	 * 여러개의 이미지 업로드
	 * - 한번에 여러개의 이미지를 업로드 하는 파일 업로드 기능을 구현
	 */
	@Resource(name="uploadPath")
	private String resourcesPath;
	
	@Inject
	private ItemService2 itemService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<Item2> itemList = itemService.list();
		model.addAttribute("itemList", itemList);
		return "ch14_2/list";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String item2RegisterForm() {
		return "ch14_2/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Item2 item, Model model) throws Exception {
		List<MultipartFile> list = item.getPictures();
		
		for (int i = 0; i < list.size(); i++) {
			MultipartFile file = list.get(i);
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
			
			String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
			
			if(i == 0) {
				item.setPictureUrl(savedName);
			} else if(i == 1) {
				item.setPictureUrl2(savedName);
			}
		}
		
		itemService.register(item);
		model.addAttribute("msg", "등록이 완료되었습니다");
		return "ch14_2/success";
	}
	
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
