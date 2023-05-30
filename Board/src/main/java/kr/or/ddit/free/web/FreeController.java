package kr.or.ddit.free.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/free")
public class FreeController {
	
	@Inject
	private IFreeService freeService;
	
	@RequestMapping(value="/form.do", method=RequestMethod.GET)
	public String insertForm() {
		return "free/form";
	}
	
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public String insertFree(FreeVO vo, Model model) {
		String goPage = "";
		
		Map<String, String> errors = new HashMap<>();
		
		if(StringUtils.isBlank(vo.getBoTitle())) {
			errors.put("boTitile", "제목을 입력해주세요");
		}
		
		if(StringUtils.isBlank(vo.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("free", vo);
			goPage = "free/form";
		} else {
			vo.setBoWriter("a001");
			
			ServiceResult result = freeService.insertFree(vo);
			
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/free/detail.do?boNo=" + vo.getBoNo();
			} else {
				errors.put("msg", "서버에러");
				model.addAttribute("errors", errors);
				goPage = "free/form";
			}
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/detail.do", method=RequestMethod.GET)
	public String selectFree(int boNo, Model model) {
		FreeVO vo = freeService.selectFree(boNo);
		
		if(vo == null) {
			return "error";
		}
		
		model.addAttribute("free", vo);
		return "/free/view";
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String freeUpdateForm(int boNo, Model model) {
		FreeVO vo = freeService.selectFree(boNo);
		
		if(vo == null) {
			return "error";
		}
		
		model.addAttribute("free", vo);
		model.addAttribute("status", "u");
		return "free/form";
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String updateFree(FreeVO vo, Model model) {
		String goPage = "";
		
		int result = freeService.findNo(vo.getBoNo());
		
		if(result == 0) {
			return "error";
		}
		
		ServiceResult res = freeService.updateFree(vo);
		
		if(res.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/detail.do?boNo=" + vo.getBoNo();
		} else {
			model.addAttribute("free", vo);
			model.addAttribute("status", "u");
			goPage = "free/form";
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/delete.do", method=RequestMethod.POST)
	public String deleteFree(int boNo, Model model) {
		FreeVO vo = freeService.selectFree(boNo);
		
		if(vo == null) {
			return "error";
		}
		
		String goPage = "";
		
		ServiceResult res = freeService.deleteFree(boNo);
		
		if(res.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/list.do";
		} else {
			goPage = "redirect:/free/detail.do?boNo=" + boNo;
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/list.do")
	public String selectList(
			@RequestParam(name="page", required = false,  defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
		PaginationInfoVO<FreeVO> pagingVO = new PaginationInfoVO<FreeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = freeService.selectFreeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<FreeVO> dataList = freeService.selectFreeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("pagingVO", pagingVO);
		
		return "free/list";
	}
}
