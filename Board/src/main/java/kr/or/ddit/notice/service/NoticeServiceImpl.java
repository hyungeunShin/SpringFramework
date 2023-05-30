package kr.or.ddit.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.dao.INoticeDAO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Inject
	private INoticeDAO noticeDao;
	
	@Override
	public ServiceResult insertNotice(NoticeVO vo) {
		ServiceResult result = null;
		int affectRow = noticeDao.insertNotice(vo); 
		
		if(affectRow > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeDao.selectNoticeCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeDao.selectNoticeList(pagingVO);
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeDao.incrementHit(boNo);
		return noticeDao.selectNotice(boNo);
	}

	@Override
	public ServiceResult updateNotice(NoticeVO vo) {
		ServiceResult result = null;
		int affectRow = noticeDao.updateNotice(vo); 
		
		if(affectRow > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult deleteNotice(int boNo) {
		ServiceResult result = null;
		int affectRow = noticeDao.deleteNotice(boNo); 
		
		if(affectRow > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public int findNo(int boNo) {
		return noticeDao.findNo(boNo);
	}
}
