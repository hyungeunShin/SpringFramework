package kr.or.ddit.mapper;

import kr.or.ddit.vo.DDITMemberVO;

public interface LoginMapper {
	public DDITMemberVO idCheck(String memId);
}
