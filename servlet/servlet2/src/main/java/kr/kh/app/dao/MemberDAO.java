package kr.kh.app.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import kr.kh.app.model.vo.MemberVO;

public interface MemberDAO {
	boolean insertMember(@Param("me")MemberVO member);

	MemberVO selectMember(@Param("me_id")String me_id);

	void updateMemberCookie(@Param("user")MemberVO user);

	MemberVO selectMemberBySid(@Param("sid")String sid);

	void updateMemberFail(@Param("user")MemberVO user2, @Param("i")int i);

	void updateMemberStop(@Param("user")MemberVO user2, @Param("me_stop")Date stop);

	boolean updateMemberPw(@Param("user")MemberVO user, @Param("me_pw")String me_pw);
}