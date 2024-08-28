package kr.kh.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.spring.Pagination.PostCriteria;
import kr.kh.spring.model.vo.CommunityVO;
import kr.kh.spring.model.vo.PostVO;

public interface PostDAO {
	List<CommunityVO> selectCommunityList();

	List<PostVO> selectPostList(@Param("cri")PostCriteria cri);

	int selectPostTotalCount(@Param("cri")PostCriteria cri);
}