package kr.or.ddit.controller.ch14.item02;

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
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String item2ModifyForm(int itemId, Model model) {
		Item2 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "ch14_2/modify";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modify(Item2 item, Model model) throws Exception {
		List<MultipartFile> pictures = item.getPictures();
		
		for (int i = 0; i < pictures.size(); i++) {
			MultipartFile file = pictures.get(i);
			
			if(file != null && file.getSize() > 0) {
				log.info("name : " + file.getOriginalFilename());
				
				String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
				if(i == 0) {
					item.setPictureUrl(savedName);
				} else if(i == 1) {
					item.setPictureUrl2(savedName);
				}
			}
		}
		
		itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다");
		return "ch14_2/success";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String removeForm(int itemId, Model model) {
		Item2 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "ch14_2/remove";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(int itemId, Model model) {
		itemService.remove(itemId);
		model.addAttribute("msg", "삭제되었습니다");
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
	
	@RequestMapping(value="/display2")
	public ResponseEntity<byte[]> displayFile2(int itemId) {
		ResponseEntity<byte[]> entity = null;
		
		String fileName = itemService.getPicture2(itemId);
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
}
