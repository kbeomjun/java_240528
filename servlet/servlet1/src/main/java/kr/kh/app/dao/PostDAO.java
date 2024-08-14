package kr.kh.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.app.model.vo.CommentVO;
import kr.kh.app.model.vo.CommunityVO;
import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.model.vo.RecommendVO;
import kr.kh.app.pagination.Criteria;
import kr.kh.app.pagination.PageMaker;

public interface PostDAO {
	List<CommunityVO> selectCommunityList();

	CommunityVO selectCommunity(@Param("co_num")int coNum);

	List<PostVO> selectPostList(@Param("cri")Criteria cri);

	int selectPostTotalCount(@Param("cri")Criteria cri);

	boolean insertPost(@Param("post")PostVO post);

	PostVO selectPost(@Param("po_num")int po_num);

	void updatePostView(@Param("po_num")int po_num);

	boolean updatePost(@Param("post")PostVO post);
	
	void deletePost(@Param("post")PostVO post);

	RecommendVO selectRecommend(@Param("re")RecommendVO recommend);

	void deleteRecommend(@Param("re_num")int re_num);

	void insertRecommend(@Param("re")RecommendVO recommend);

	List<CommentVO> selectCommentList(@Param("cri")Criteria cri);

	int selectCommentTotalCount(@Param("cri")Criteria cri);

	boolean insertComment(@Param("cm")CommentVO comment);

	CommentVO selectComment(@Param("cm_num")int cm_num);

	boolean deleteAllComment(@Param("cm_num")int cm_num);

	boolean deleteComment(@Param("cm_num")int cm_num);

	boolean updateComment(@Param("cm")CommentVO comment);
}