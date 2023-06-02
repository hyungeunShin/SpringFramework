package kr.or.ddit.controller.ch13.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.ch13.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String noticeInsertForm() {
		return "notice/form";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(NoticeVO notice, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<>();
		
		if(StringUtils.isBlank(notice.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");
		}
		if(StringUtils.isBlank(notice.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("notice", notice);
			goPage = "notice/form";
		} else {
			notice.setBoWriter("a001"); 
			ServiceResult result = noticeService.insertNotice(notice);
			
			if(result.equals(ServiceResult.OK)) {
				//성공했다는 메세지를 텔레그램 BOT API를 이용하여 처리
				
				goPage = "redirect:/notice/detail?boNo=" + notice.getBoNo();
			} else {
				model.addAttribute("message", "서버에러, 다시 시도해주세요");
				model.addAttribute("notice", notice);
				goPage = "notice/form";
			}
		}
		
		return goPage;
	}
}
