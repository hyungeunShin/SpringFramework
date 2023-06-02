package kr.or.ddit.mapper;

import kr.or.ddit.vo.NoticeVO;

public interface NoticeMapper {
	public int insertNotice(NoticeVO notice);

	public void incrementHit(int boNo);

	public NoticeVO selectNotice(int boNo);
}
