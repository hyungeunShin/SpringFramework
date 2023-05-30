package kr.or.ddit.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class BoardDAOImpl implements IBoardDAO {
	
	@Inject
	private SqlSessionTemplate session;
	
	@Override
	public int insertBoard(BoardVO vo) {
		return session.insert("Board.insertBoard", vo);
	}

	@Override
	public void incrementHit(int boNo) {
		session.update("Board.incrementHit", boNo);
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		return session.selectOne("Board.selectBoard", boNo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return session.update("Board.updateBoard", vo);
	}

	@Override
	public int deleteBoard(int boNo) {
		return session.delete("Board.deleteBoard", boNo);
	}

	@Override
	public List<BoardVO> selectBoardList() {
		return session.selectList("Board.selectBoardList");
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		return session.selectOne("Board.selectBoardCount", pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList2(PaginationInfoVO<BoardVO> pagingVO) {
		return session.selectList("Board.selectBoardList2", pagingVO);
	}
}
