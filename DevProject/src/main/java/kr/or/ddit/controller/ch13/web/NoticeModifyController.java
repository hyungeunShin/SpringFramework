package kr.or.ddit.controller.ch13.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.ch13.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

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
	public String modify(NoticeVO notice, HttpServletRequest req, Model model) {
		String goPage = "";
		ServiceResult result = noticeService.updateNotice(req, notice);
		
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
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(int boNo, HttpServletRequest req, Model model) {
		String goPage = "";
		
		ServiceResult result = noticeService.deleteNotice(req, boNo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/list";
		} else{
			model.addAttribute("message", "다시 시도해주세요");
			goPage = "redirect:/notice/detail?boNo=" + boNo;
		}
		
		
		return goPage;
	}
}
