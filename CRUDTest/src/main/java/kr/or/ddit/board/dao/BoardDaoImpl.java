package kr.or.ddit.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class BoardDaoImpl implements IBoardDao {
	@Inject
	private SqlSessionTemplate session;
	
	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		return session.selectOne("board.selectBoardCount", pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO) {
		return session.selectList("board.selectBoardList", pagingVO);
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		return session.selectOne("board.selectBoard", boNo);
	}

	@Override
	public void updateHit(int boNo) {
		session.update("board.updateHit", boNo);
	}

	@Override
	public int deleteBoard(int boNo) {
		return session.delete("board.deleteBoard", boNo);
	}

	@Override
	public int insertBoard(BoardVO vo) {
		return session.insert("board.insertBoard", vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return session.update("board.updateBoard", vo);
	}
}
