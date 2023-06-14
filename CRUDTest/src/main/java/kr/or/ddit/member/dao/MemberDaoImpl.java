package kr.or.ddit.member.dao;

import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

@Repository
public class MemberDaoImpl implements IMemberDao {
	@Inject
	private SqlSessionTemplate session;
	
	@Override
	public MemberVO selectMember(Map<String, Object> map) {
		return session.selectOne("member.selectMember", map);
	}

	@Override
	public int insertMember(MemberVO vo) {
		return session.insert("member.insertMember", vo);
	}

	@Override
	public int CheckId(String id) {
		return session.selectOne("member.CheckId", id);
	}
}
