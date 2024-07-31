package db.community.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db.community.dao.PostDAO;
import db.community.model.vo.CommunityVO;

public class PostServiceImp implements PostService {
	private PostDAO postDao;
	public PostServiceImp() {
		String resource = "db/community/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			postDao = session.getMapper(PostDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean insertCommunity(String community) {
		if(community == null || community.trim().length() == 0) {
			return false;
		}
		community = community.trim();
		CommunityVO communityVO =  postDao.selectCommunity(community);
		if(communityVO != null) {
			return false;
		}
		return postDao.insertCommunity(community);
	}
}