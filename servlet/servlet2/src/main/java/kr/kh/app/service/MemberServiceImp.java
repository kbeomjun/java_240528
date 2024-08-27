package kr.kh.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.kh.app.dao.MemberDAO;
import kr.kh.app.model.vo.MemberVO;

public class MemberServiceImp implements MemberService {
	private MemberDAO memberDao;
	
	public MemberServiceImp() {
		String resource = "kr/kh/app/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			memberDao = session.getMapper(MemberDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkRegex(String str, String regex) {
		if(str == null || regex == null) {
			return false;
		}
		return Pattern.matches(regex, str);
	}
	
	@Override
	public boolean signup(MemberVO member) {
		if(member == null) {
			return false;
		}
		if(!checkRegex(member.getMe_id(), "^[a-z0-9]{6,13}$")) {
			return false;
		}
		if(!checkRegex(member.getMe_pw(), "^[a-zA-Z0-9!@#$]{6,15}$")) {
			return false;
		}
		if(!checkRegex(member.getMe_email(), "^\\w{6,13}@\\w{4,8}.[a-z]{2,3}$")) {
			return false;
		}
		try {
			return memberDao.insertMember(member);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean checkId(String me_id) {
		return memberDao.selectMember(me_id) == null;
	}

	@Override
	public String checkUser(MemberVO user) {
		String res = "";
		if(user == null) {
			res = "아이디를 입력하세요.";
			return res;
		}
		MemberVO user2 = memberDao.selectMember(user.getMe_id());
		if(user2 == null) {
			res = "없는 아이디입니다.";
			return res;
		}
		Date date = new Date(System.currentTimeMillis());
		if(user2.getMe_stop() != null && user2.getMe_stop().compareTo(date) > 0) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.format(user2.getMe_stop());
			res = "계정이 잠겼습니다. "+format.format(user2.getMe_stop())+"까지 서비스 이용이 불가능합니다.";
			return res;
		}
		if(!user2.getMe_pw().equals(user.getMe_pw())) {
			memberDao.updateMemberFail(user2, 1);
			int fail = user2.getMe_fail() + 1;
			if(fail < 5) {
				res = "비밀번호가 틀렸습니다. 5회 이상 틀릴시 계정이 잠깁니다.("+fail+"/5)";
			}else {
				int time = 60 * 30;
				Date stop = new Date(System.currentTimeMillis() + time * 1000);
				memberDao.updateMemberStop(user2, stop);
				res = "비밀번호가 틀렸습니다. 5회 이상 틀려서 계정이 30분동안 잠깁니다.("+fail+"/5)";
			}
			return res;
		}
		
		Date stop = null;
		memberDao.updateMemberFail(user2, 0);
		memberDao.updateMemberStop(user2, stop);
		return res;
	}

	@Override
	public Cookie createCookie(MemberVO user, HttpServletRequest request) {
		if(user == null) {
			return null;
		}
		HttpSession session = request.getSession();
		//쿠키는 이름, 값, 만료시간, path가 필요
		String me_cookie = session.getId();
		//쿠키 이름이 AL, 값은 현재 세션 아이디값
		Cookie cookie = new Cookie("AL", me_cookie);
		cookie.setPath("/");
		int time = 60 * 60 * 24 * 7;
		cookie.setMaxAge(time);
		user.setMe_cookie(me_cookie);
		//만료시간은 현재 시간 + 1주일뒤
		Date date = new Date(System.currentTimeMillis() + time * 1000);
		user.setMe_limit(date);
		memberDao.updateMemberCookie(user);
		return cookie;
	}

	@Override
	public void updateMemberCookie(MemberVO user) {
		memberDao.updateMemberCookie(user);
	}

	@Override
	public MemberVO getMemberBySid(String sid) {
		return memberDao.selectMemberBySid(sid);
	}
}