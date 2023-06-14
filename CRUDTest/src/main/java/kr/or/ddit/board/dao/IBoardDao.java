package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardDao {
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);
	public BoardVO selectBoard(int boNo);
	public void updateHit(int boNo);
	public int deleteBoard(int boNo);
	public int insertBoard(BoardVO vo);
	public int updateBoard(BoardVO vo);
}
