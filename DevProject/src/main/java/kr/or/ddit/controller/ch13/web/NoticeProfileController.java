package kr.or.ddit.controller.ch13.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

@Controller
@RequestMapping("/notice")
public class NoticeProfileController {
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String noticeProfile(HttpServletRequest req, RedirectAttributes ra, Model model) {
		String goPage = "";
		
		//HttpSession session = req.getSession();
		//DDITMemberVO vo = (DDITMemberVO) session.getAttribute("SessionInfo");
		
		//if(vo == null) {
			//ra.addFlashAttribute("message", "로그인 후 이용 가능합니다");
			//return "redirect:/notice/login";
		//}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		DDITMemberVO member = noticeService.selectMember(user.getUsername());
		
		if(member != null) {
			model.addAttribute("member", member);
			goPage = "notice/profile";
		} else {
			ra.addFlashAttribute("message", "로그인 후 이용가능합니다");
			goPage = "redirect:/notice/login";
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/profileupdate", method=RequestMethod.POST)
	public String update(HttpServletRequest req, DDITMemberVO vo, RedirectAttributes ra, Model model) {
		String goPage = "";
		
		ServiceResult res = noticeService.profileUpdate(req, vo);
		
		if(res.equals(ServiceResult.OK)) {
			ra.addFlashAttribute("message", "회원정보가 수정되었습니다");
			goPage = "redirect:/notice/profile";
		} else {
			model.addAttribute("member", vo);
			goPage = "notice/profile";
		}
		
		return goPage;
	}
}
