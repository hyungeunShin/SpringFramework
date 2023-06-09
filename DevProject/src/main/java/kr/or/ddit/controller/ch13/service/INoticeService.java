package kr.or.ddit.controller.ch13.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeService {
	public ServiceResult insertNotice(HttpServletRequest req, NoticeVO notice);

	public NoticeVO selectNotice(int boNo);

	public ServiceResult updateNotice(NoticeVO notice);

	public ServiceResult deleteNotice(int boNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);

	public ServiceResult idCheck(String memId);
	
	public ServiceResult signup(HttpServletRequest req, DDITMemberVO memberVO);

	public DDITMemberVO loginCheck(DDITMemberVO memberVO);

	public String idForget(DDITMemberVO vo);

	public String pwForget(DDITMemberVO vo);
}
