package kr.or.ddit.free.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.dao.IFreeDAO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class FreeServiceImpl implements IFreeService {
	
	@Inject
	private IFreeDAO freeDao;
	
	@Override
	public ServiceResult insertFree(FreeVO vo) {
		ServiceResult result = null;
		int affectRow = freeDao.insertFree(vo);
		
		if(affectRow > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public FreeVO selectFree(int boNo) {
		freeDao.incrementHit(boNo);
		return freeDao.selectFree(boNo);
	}

	@Override
	public ServiceResult updateFree(FreeVO vo) {
		ServiceResult result = null;
		int affectRow = freeDao.updateFree(vo);
		
		if(affectRow > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult deleteFree(int boNo) {
		ServiceResult result = null;
		int affectRow = freeDao.deleteFree(boNo);
		
		if(affectRow > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO) {
		return freeDao.selectFreeCount(pagingVO);
	}

	@Override
	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO) {
		return freeDao.selectFreeList(pagingVO);
	}

	@Override
	public int findNo(int boNo) {
		return freeDao.findNo(boNo);
	}
}
