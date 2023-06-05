package kr.or.ddit.controller.ch13.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeService {
	public ServiceResult insertNotice(NoticeVO notice);

	public NoticeVO selectNotice(int boNo);

	public ServiceResult updateNotice(NoticeVO notice);

	public ServiceResult deleteNotice(int boNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);
}
