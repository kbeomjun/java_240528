package kr.kh.spring3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.spring3.dao.MemberDAO;
import kr.kh.spring3.model.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDao;

	public MemberVO getMember(String me_id) {
		return memberDao.selectMember(me_id);
	}
}