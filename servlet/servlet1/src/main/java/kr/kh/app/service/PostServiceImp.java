package kr.kh.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.kh.app.dao.PostDAO;
import kr.kh.app.model.vo.CommentVO;
import kr.kh.app.model.vo.CommunityVO;
import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.model.vo.RecommendVO;
import kr.kh.app.pagination.Criteria;
import kr.kh.app.pagination.PageMaker;

public class PostServiceImp implements PostService {
	private PostDAO postDao;
	
	public PostServiceImp() {
		String resource = "kr/kh/app/config/mybatis-config.xml";
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
	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	@Override
	public CommunityVO getCommunity(int coNum) {
		return postDao.selectCommunity(coNum);
	}

	@Override
	public List<PostVO> getPostList(Criteria cri) {
		if(cri == null) {
			throw new RuntimeException();
		}
		return postDao.selectPostList(cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri, int displayPageNum) {
		if(cri == null) {
			throw new RuntimeException();
		}
		int totalCount = postDao.selectPostTotalCount(cri);
		return new PageMaker(totalCount, displayPageNum, cri);
	}

	@Override
	public boolean insertPost(PostVO post) {
		if(post == null) {
			return false;
		}
		if(post.getPo_title() == null || post.getPo_title().trim().length() == 0) {
			return false;
		}
		if(post.getPo_content() == null || post.getPo_content().trim().length() == 0) {
			return false;
		}
		return postDao.insertPost(post);
	}

	@Override
	public PostVO getPost(int po_num) {
		return postDao.selectPost(po_num);
	}

	@Override
	public void updatePostView(int po_num) {
		postDao.updatePostView(po_num);
	}

	@Override
	public PostVO getPost(int po_num, MemberVO user) {
		if(user == null) {
			return null;
		}
		PostVO post = postDao.selectPost(po_num);
		if(post == null) {
			return null;
		}
		if(checkWriter(po_num, user)) {
			return post;
		}
		return null;
	}

	@Override
	public boolean updatePost(PostVO post, MemberVO user) {
		if(post == null || user == null) {
			return false;
		}
		if(!checkWriter(post.getPo_num(), user)) {
			return false;
		}
		if(post.getPo_title() == null || post.getPo_title().trim().length() == 0) {
			return false;
		}
		if(post.getPo_content() == null || post.getPo_content().trim().length() == 0) {
			return false;
		}
		return postDao.updatePost(post);
	}
	
	private boolean checkWriter(int po_num, MemberVO user) {
		if(user == null) {
			return false;
		}
		PostVO post = postDao.selectPost(po_num);
		if(post == null) {
			return false;
		}
		if(post.getPo_me_id().equals(user.getMe_id())) {
			return true;
		}
		return false;
	}

	@Override
	public void deletePost(PostVO post) {
		postDao.deletePost(post);
	}

	@Override
	public int insertRecommend(RecommendVO recommend) {
		if(recommend == null) {
			throw new RuntimeException();
		}
		RecommendVO dbRecommend = postDao.selectRecommend(recommend);
		if(dbRecommend != null) {
			if(dbRecommend.getRe_state() != recommend.getRe_state()) {
				postDao.deleteRecommend(dbRecommend.getRe_num());
				postDao.insertRecommend(recommend);
				return 1;
			}else {
				postDao.deleteRecommend(dbRecommend.getRe_num());
				return 0;
			}
		}else {
			postDao.insertRecommend(recommend);
			return 1;
		}
	}

	@Override
	public RecommendVO getRecommend(int po_num, MemberVO user) {
		if(user == null) {
			return null;
		}
		RecommendVO recommend = new RecommendVO(po_num, 0, user.getMe_id());
		return postDao.selectRecommend(recommend);
	}

	@Override
	public List<CommentVO> getCommentList(Criteria cri) {
		if(cri == null) {
			return null;
		}
		return postDao.selectCommentList(cri);
	}

	@Override
	public PageMaker getCommentPageMaker(Criteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount =  postDao.selectCommentTotalCount(cri);
		return new PageMaker(totalCount, 2, cri);
	}
}