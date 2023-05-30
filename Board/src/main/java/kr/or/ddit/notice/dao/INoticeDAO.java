package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeDAO {
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);
	public int insertNotice(NoticeVO vo);
	public NoticeVO selectNotice(int boNo);
	public void incrementHit(int boNo);
	public int updateNotice(NoticeVO vo);
	public int deleteNotice(int boNo);
	public int findNo(int boNo);
}