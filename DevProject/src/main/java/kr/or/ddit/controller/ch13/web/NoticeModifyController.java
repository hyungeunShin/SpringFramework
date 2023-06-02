package kr.or.ddit.controller.ch13.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.ch13.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/notice")
public class NoticeModifyController {
	
	@Inject
	private INoticeService noticeService;
	
	@GetMapping("/update")
	public String noticeModifyForm(int boNo, Model model) {
		NoticeVO notice = noticeService.selectNotice(boNo);
		model.addAttribute("notice", notice);
		model.addAttribute("status", "u");
		return "notice/form";
	}
	
	@PostMapping("/update")
	public String modify(NoticeVO notice, Model model) {
		String goPage = "";
		ServiceResult result = noticeService.updateNotice(notice);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/detail?boNo=" + notice.getBoNo();
		} else {
			model.addAttribute("message", "수정에 실패하였습니다");
			model.addAttribute("notice", notice);
			model.addAttribute("status", "u");
			goPage = "notice/form";
		}
		
		return goPage;
	}
}
