package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardService {
	public ServiceResult insertBoard(BoardVO vo);
	public BoardVO selectBoard(int boNo);
	public ServiceResult updateBoard(BoardVO vo);
	public ServiceResult deleteBoard(int boNo);
	public List<BoardVO> selectBoardList();
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList2(PaginationInfoVO<BoardVO> pagingVO);
}
