package kr.or.ddit.controller.ch13.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.or.ddit.controller.ch13.service.INoticeService;
import kr.or.ddit.controller.ch13.view.NoticeDownloadView;
import kr.or.ddit.vo.NoticeFileVO;

@Controller
@RequestMapping("/notice")
public class NoticeDownloadController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/download")
	public View noticeDownload(int fileNo, Model model) {
		NoticeFileVO vo = noticeService.noticeDownload(fileNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileName", vo.getFileName());
		map.put("fileSize", vo.getFileSize());
		map.put("fileSavepath", vo.getFileSavepath());
		
		model.addAttribute("noticeFileMap", map);
		
		//리턴되는 noticeDownloadView는 jsp페이지로 존재하는 페이지 이름을 요청하는게 아니라
		//클래스를 요청하는 것인데 해당 클래스가 스프링에서 제공하는 abstractView 클래스를 상속받아
		//그 클래스는 renderMergeOutputModel 함수를 재정의할 때 view로 취급될 수 있게 해준다
		return new NoticeDownloadView();
	}
}
