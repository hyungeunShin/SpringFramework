package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class BoardServiceImpl implements IBoardService {
	@Inject
	private IBoardDao dao;
	
	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		return dao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO) {
		return dao.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		dao.updateHit(boNo);
		return dao.selectBoard(boNo);
	}

	@Override
	public int deleteBoard(int boNo) {
		return dao.deleteBoard(boNo);
	}

	@Override
	public int insertBoard(BoardVO vo) {
		return dao.insertBoard(vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return dao.updateBoard(vo);
	}
}
