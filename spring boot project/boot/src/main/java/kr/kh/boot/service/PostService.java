package kr.kh.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.boot.dao.PostDAO;
import kr.kh.boot.model.vo.CommunityVO;
import kr.kh.boot.model.vo.PostVO;
import kr.kh.boot.pagination.Criteria;
import kr.kh.boot.pagination.PageMaker;
import kr.kh.boot.pagination.PostCriteria;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
	private PostDAO postDao;

	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	public List<PostVO> getPostList(Criteria cri) {
		return postDao.selectPostList(cri);
	}

	public PageMaker getPageMaker(PostCriteria cri) {
		int displayPageNum = 1;
		int totalCount = postDao.selectPostTotalCount(cri);
		return new PageMaker(totalCount, displayPageNum, cri);
	}

	public void updatePostView(PostVO post) {
		postDao.updatePostView(post);
	}

	public PostVO getPost(PostVO post) {
		return postDao.selectPost(post);
	}

	public boolean insertPost(PostVO post) {
		return postDao.insertPost(post);
	}

	public boolean updatePost(PostVO post) {
		return postDao.updatePost(post);
	}

	public boolean deletePost(PostVO post) {
		return postDao.deletePost(post);
	}
}