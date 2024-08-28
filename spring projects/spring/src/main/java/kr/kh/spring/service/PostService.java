package kr.kh.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.spring.Pagination.PageMaker;
import kr.kh.spring.Pagination.PostCriteria;
import kr.kh.spring.dao.PostDAO;
import kr.kh.spring.model.vo.CommunityVO;
import kr.kh.spring.model.vo.PostVO;

@Service
public class PostService {
	@Autowired
	PostDAO postDao;

	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	public List<PostVO> getPostList(PostCriteria cri) {
		if(cri == null) {
			return null;
		}
		return postDao.selectPostList(cri);
	}

	public PageMaker getPageMaker(PostCriteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount = postDao.selectPostTotalCount(cri);
		return new PageMaker(2, cri, totalCount);
	}
}