package kr.or.ddit.main.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;

@Repository
public class MainDAOImpl implements IMainDAO{
	
	@Inject
	private SqlSessionTemplate session;
	
	public List<BoardVO> selectBoardList() {
		return session.selectList("Main.selectBoardList");
	}

	@Override
	public List<NoticeVO> selectNoticeList() {
		return session.selectList("Main.selectNoticeList");
	}

	@Override
	public List<FreeVO> selectFreeList() {
		return session.selectList("Main.selectFreeList");
	}

	@Override
	public int boardCount() {
		return session.selectOne("Main.boardCount");
	}

	@Override
	public int noticeCount() {
		return session.selectOne("Main.noticeCount");
	}

	@Override
	public int freeCount() {
		return session.selectOne("Main.freeCount");
	}
}
