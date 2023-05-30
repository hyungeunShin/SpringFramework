package kr.or.ddit.notice.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class NoticeDAOImpl implements INoticeDAO {

	@Inject
	private SqlSessionTemplate session;
	
	@Override
	public int insertNotice(NoticeVO vo) {
		return session.insert("Notice.insertNotice", vo);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return session.selectList("Notice.selectNoticeList", pagingVO);
	}

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return session.selectOne("Notice.selectNoticeCount", pagingVO);
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		return session.selectOne("Notice.selectNotice", boNo);
	}

	@Override
	public void incrementHit(int boNo) {
		session.update("Notice.incrementHit", boNo);
	}

	@Override
	public int updateNotice(NoticeVO vo) {
		return session.update("Notice.updateNotice", vo);
	}

	@Override
	public int deleteNotice(int boNo) {
		return session.delete("Notice.deleteNotice", boNo);
	}

	@Override
	public int findNo(int boNo) {
		return session.selectOne("Notice.findNo", boNo);
	}
}
