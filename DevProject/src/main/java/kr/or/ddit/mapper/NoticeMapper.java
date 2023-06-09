package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.NoticeFileVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface NoticeMapper {
	public int insertNotice(NoticeVO notice);

	public void incrementHit(int boNo);

	public NoticeVO selectNotice(int boNo);

	public int updateNotice(NoticeVO notice);

	public int deleteNotice(int boNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);

	public void insertNoticeFile(NoticeFileVO noticeFileVO);

	public NoticeFileVO noticeDownload(int fileNo);

	public void incrementNoticeDowncount(int fileNo);

	public NoticeFileVO selectNoticeFile(Integer integer);

	public void deleteNoticeFile(Integer integer);

	public void deleteNoticeFileByNo(int boNo);
}
