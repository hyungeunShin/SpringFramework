package kr.or.ddit.member.service;

import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService {
	public MemberVO selectMember(Map<String, Object> map);
	public int insertMember(MemberVO vo);
	public int CheckId(String id);
}
