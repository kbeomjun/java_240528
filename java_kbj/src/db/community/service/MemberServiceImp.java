package db.community.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import db.community.dao.MemberDAO;
import db.community.model.vo.MemberVO;

public class MemberServiceImp implements MemberService {
	private MemberDAO memberDao;
	public MemberServiceImp() {
		String resource = "db/community/config/mybatis-config.xml";
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
	@Override
	public MemberVO login(String id, String pw) {
		MemberVO user = memberDao.selectMember(id);
		if(user == null) {
			return null;
		}
		if(user.getMe_pw().equals(pw)) {
			memberDao.updateFail(user.getMe_id(), 0);
			return user;
		}
		memberDao.updateFail(user.getMe_id(), 1);
		return null;
	}
	@Override
	public boolean signup(String id, String pw, String email) {
		MemberVO user = memberDao.selectMember(id);
		if(user != null) {
			return false;
		}
		String idRegex = "^\\w{6,13}$";
		if(!Pattern.matches(idRegex, id)) {
			return false;
		}
		String pwRegex = "^[a-zA-Z0-99!@#$]{6,15}$";
		if(!Pattern.matches(pwRegex, pw)) {
			return false;
		}
		return memberDao.insertMember(id, pw, email);
	}
}