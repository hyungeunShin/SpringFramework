package kr.or.ddit.controller.noticeboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String noticeInsertForm() {
		return "notice/form";
	}
}
