package kr.or.ddit.free.dao;

import java.util.List;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeDAO {
	public int insertFree(FreeVO vo);
	public FreeVO selectFree(int boNo);
	public int updateFree(FreeVO vo);
	public int deleteFree(int boNo);
	public void incrementHit(int boNo);
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO);
	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO);
	public int findNo(int boNo);
}
