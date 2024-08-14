package kr.kh.app.service;

import java.util.List;

import kr.kh.app.model.vo.CommentVO;
import kr.kh.app.model.vo.CommunityVO;
import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.model.vo.RecommendVO;
import kr.kh.app.pagination.Criteria;
import kr.kh.app.pagination.PageMaker;

public interface PostService {
	List<CommunityVO> getCommunityList();

	CommunityVO getCommunity(int coNum);
	
	List<PostVO> getPostList(Criteria cri);

	PageMaker getPageMaker(Criteria cri, int displayPageNum);

	boolean insertPost(PostVO post);

	PostVO getPost(int po_num);

	void updatePostView(int po_num);

	PostVO getPost(int po_num, MemberVO user);

	boolean updatePost(PostVO post, MemberVO user);

	void deletePost(PostVO post);

	int insertRecommend(RecommendVO recommend);

	RecommendVO getRecommend(int po_num, MemberVO user);

	List<CommentVO> getCommentList(Criteria cri);

	PageMaker getCommentPageMaker(Criteria cri);

	boolean insertComment(CommentVO comment);

	boolean deleteComment(int cm_num, int cm_ori_num, MemberVO user);

	boolean updateComment(CommentVO comment, MemberVO user);
}