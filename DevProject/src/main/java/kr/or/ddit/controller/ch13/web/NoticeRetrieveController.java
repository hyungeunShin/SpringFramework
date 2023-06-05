package kr.or.ddit.controller.ch13.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.controller.ch13.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeRetrieveController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/list")
	public String noticeList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model
			) {
		log.info("currentPage: " + currentPage);
		log.info("searchType: " + searchType);
		log.info("searchWord: " + searchWord);
		
		PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = noticeService.selectNoticeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "notice/list";
	}
	
	@RequestMapping(value="/detail")
	public String noticeDetail(int boNo, Model model) {
		NoticeVO notice = noticeService.selectNotice(boNo);
		model.addAttribute("notice", notice);
		return "notice/detail";
	}
}
