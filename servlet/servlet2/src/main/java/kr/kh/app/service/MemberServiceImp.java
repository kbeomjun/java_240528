package kr.kh.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

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
		if(!checkRegex(member.getMe_email(), "^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z]{2,}){1,}$")) {
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
}