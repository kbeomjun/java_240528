package kr.kh.spring3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.kh.spring3.dao.MemberDAO;
import kr.kh.spring3.model.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public boolean signup(MemberVO member) {
		if(member == null) {
			return false;
		}
		String encPw = passwordEncoder.encode(member.getMe_pw());
		member.setMe_pw(encPw);
		return memberDao.insertMember(member);
	}

	public MemberVO login(MemberVO member) {
		if(member == null) {
			return null;
		}
		MemberVO memberVO = memberDao.selectMember(member);
		if(memberVO == null) {
			return null;
		}
		if(!passwordEncoder.matches(member.getMe_pw(), memberVO.getMe_pw())) {
			memberVO.setMe_pw("1");
		}
		return memberVO;
	}
}