package kr.or.ddit.free.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class FreeDAOImpl implements IFreeDAO {
	
	@Inject
	private SqlSessionTemplate session;
	
	@Override
	public int insertFree(FreeVO vo) {
		return session.insert("Free.insertFree", vo);
	}

	@Override
	public FreeVO selectFree(int boNo) {
		return session.selectOne("Free.selectFree", boNo);
	}

	@Override
	public int updateFree(FreeVO vo) {
		return session.update("Free.updateFree", vo);
	}

	@Override
	public int deleteFree(int boNo) {
		return session.delete("Free.deleteFree", boNo);
	}

	@Override
	public void incrementHit(int boNo) {
		session.update("Free.incrementHit", boNo);
	}

	@Override
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO) {
		return session.selectOne("Free.selectFreeCount", pagingVO);
	}

	@Override
	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO) {
		return session.selectList("Free.selectFreeList", pagingVO);
	}

	@Override
	public int findNo(int boNo) {
		return session.selectOne("Free.findNo", boNo);
	}
}
