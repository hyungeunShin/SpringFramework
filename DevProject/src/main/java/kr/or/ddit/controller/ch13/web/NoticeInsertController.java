package kr.or.ddit.controller.ch13.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.ch13.service.INoticeService;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeInsertController {
	
	@Resource(name="uploadPath")
	private String resourcesPath;
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String noticeInsertForm() {
		return "notice/form";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(NoticeVO notice, HttpServletRequest req, RedirectAttributes ra, Model model) {
		log.info("");
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
			//HttpSession session = req.getSession();
			//DDITMemberVO vo = (DDITMemberVO) session.getAttribute("SessionInfo");
			
			//if(vo != null) {
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				notice.setBoWriter(user.getUsername());
				ServiceResult result = noticeService.insertNotice(req, notice);
				
				if(result.equals(ServiceResult.OK)) {
					//성공했다는 메세지를 텔레그램 BOT API를 이용하여 처리
					
					goPage = "redirect:/notice/detail?boNo=" + notice.getBoNo();
				} else {
					model.addAttribute("message", "서버에러, 다시 시도해주세요");
					model.addAttribute("notice", notice);
					goPage = "notice/form";
				}
			//} else {
				//ra.addFlashAttribute("message", "로그인 후에 사용 가능합니다");
				//goPage = "redirect:/notice/login";
			}
		//}
		
		return goPage;
	}
}
