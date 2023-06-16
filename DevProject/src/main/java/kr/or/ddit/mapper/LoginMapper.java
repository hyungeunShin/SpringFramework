package kr.or.ddit.mapper;

import kr.or.ddit.vo.DDITMemberVO;

public interface LoginMapper {
	public DDITMemberVO idCheck(String memId);

	public int signup(DDITMemberVO memberVO);

	public DDITMemberVO loginCheck(DDITMemberVO memberVO);

	public String idForget(DDITMemberVO vo);

	public String pwForget(DDITMemberVO vo);

	public DDITMemberVO readByUserId(String username);

	public void signupAuth(int memNo);
}
