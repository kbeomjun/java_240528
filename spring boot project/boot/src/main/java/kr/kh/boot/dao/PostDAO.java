package kr.kh.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.boot.model.vo.CommunityVO;
import kr.kh.boot.model.vo.PostVO;
import kr.kh.boot.pagination.Criteria;
import kr.kh.boot.pagination.PostCriteria;

public interface PostDAO {
	List<CommunityVO> selectCommunityList();

	List<PostVO> selectPostList(@Param("cri")Criteria cri);

	int selectPostTotalCount(@Param("cri")PostCriteria cri);

	void updatePostView(@Param("po")PostVO post);

	PostVO selectPost(@Param("po")PostVO post);

	boolean insertPost(@Param("po")PostVO post);

	boolean updatePost(@Param("po")PostVO post);

	boolean deletePost(@Param("po")PostVO post);
}