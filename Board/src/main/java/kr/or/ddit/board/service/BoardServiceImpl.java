package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class BoardServiceImpl implements IBoardService {
	
	@Inject
	private IBoardDAO boardDao;
	
	@Override
	public ServiceResult insertBoard(BoardVO vo) {
		ServiceResult result = null;
		int status = boardDao.insertBoard(vo);
		
		//성공하면 1 실패하면 0
		//vo 안에 no가 세팅되어 들어올 에정
		//selectKey 설정에 의해서 최신 글 번호가 담겨서 들어온다
		if(status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		boardDao.incrementHit(boNo); 
		return boardDao.selectBoard(boNo);
	}

	@Override
	public ServiceResult updateBoard(BoardVO vo) {
		ServiceResult result = null;
		int status = boardDao.updateBoard(vo);
		
		if(status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult deleteBoard(int boNo) {
		ServiceResult result = null;
		int status = boardDao.deleteBoard(boNo);
		
		if(status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public List<BoardVO> selectBoardList() {
		return boardDao.selectBoardList();
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		return boardDao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList2(PaginationInfoVO<BoardVO> pagingVO) {
		return boardDao.selectBoardList2(pagingVO);
	}
}
