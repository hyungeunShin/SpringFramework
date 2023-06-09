package kr.or.ddit.mapper;

import kr.or.ddit.vo.DDITMemberVO;

public interface ProfileMapper {
	public DDITMemberVO selectMember(DDITMemberVO vo);
	public int profileUpdate(DDITMemberVO vo);
}
