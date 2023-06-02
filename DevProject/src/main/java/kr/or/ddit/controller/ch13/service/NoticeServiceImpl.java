package kr.or.ddit.controller.ch13.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.ch13.web.TelegramSendController;
import kr.or.ddit.mapper.NoticeMapper;
import kr.or.ddit.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Inject
	private NoticeMapper noticeMapper;
	
	TelegramSendController tst = new TelegramSendController();
	
	@Override
	public ServiceResult insertNotice(NoticeVO notice) {
		ServiceResult result = null;
		int status = noticeMapper.insertNotice(notice);
		if(status > 0) {
			try {
				tst.sendGet("406호", notice.getBoTitle());
			} catch (Exception e) {
				e.printStackTrace();
			}
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeMapper.incrementHit(boNo);
		return noticeMapper.selectNotice(boNo);
	}
}
