package kr.kh.app.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import kr.kh.app.model.vo.MemberVO;

public interface MemberService {
	boolean signup(MemberVO member);

	boolean checkId(String me_id);

	boolean checkUser(MemberVO user);

	Cookie createCookie(MemberVO user, HttpServletRequest request);

	void updateMemberCookie(MemberVO user);

	MemberVO getMemberBySid(String sid);
}