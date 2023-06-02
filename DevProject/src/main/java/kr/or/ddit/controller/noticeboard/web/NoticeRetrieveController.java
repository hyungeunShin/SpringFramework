package kr.or.ddit.controller.noticeboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeRetrieveController {
	@RequestMapping(value="/list")
	public String noticeList() {
		return "notice/list";
	}
	
	@RequestMapping(value="/detail")
	public String noticeDetail() {
		return "notice/detail";
	}
}
