package kr.or.ddit.member.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.vo.MemberVO;

@Service
public class MemberServiceImpl implements IMemberService {
	
	@Inject
	private IMemberDao dao;
	
	@Override
	public MemberVO selectMember(Map<String, Object> map) {
		return dao.selectMember(map);
	}

	@Override
	public int insertMember(MemberVO vo) {
		return dao.insertMember(vo);
	}

	@Override
	public int CheckId(String id) {
		return dao.CheckId(id);
	}
}
