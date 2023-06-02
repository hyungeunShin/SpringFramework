package kr.or.ddit.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.vo.MemberAuth;
import kr.or.ddit.vo.MemberVO;

@Service
public class MemberServiceImpl implements IMemberService {
	
	@Inject
	private MemberMapper mapper;
	
	@Override
	public void register(MemberVO member) {
		List<MemberAuth> authList = new ArrayList<MemberAuth>();
		authList.add(new MemberAuth("ROLE_USER"));
		member.setAuthList(authList);
		mapper.create(member);
	}

	@Override
	public List<MemberVO> list() {
		return mapper.list();
	}

	@Override
	public MemberVO read(int userNo) {
		return mapper.read(userNo);
	}

	@Override
	public void modify(MemberVO member) {
		mapper.modify(member);
		
		int userNo = member.getUserNo();
		mapper.deleteAuth(userNo);
		
		List<MemberAuth> authList = member.getAuthList();
		for (int i = 0; i < authList.size(); i++) {
			MemberAuth memberAuth = authList.get(i);
			String auth = memberAuth.getAuth();
			if(auth == null) {
				continue;
			}
			if(auth.trim().length() == 0) {
				continue;
			}
			memberAuth.setUserNo(userNo);
			mapper.createAuth(memberAuth);
		}
	}

	@Override
	public void remove(int userNo) {
		mapper.deleteAuth(userNo);
		mapper.delete(userNo);
	}
}
