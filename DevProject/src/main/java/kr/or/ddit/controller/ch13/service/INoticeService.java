package kr.or.ddit.controller.ch13.service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.NoticeVO;

public interface INoticeService {
	public ServiceResult insertNotice(NoticeVO notice);

	public NoticeVO selectNotice(int boNo);
}
