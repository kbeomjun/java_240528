package kr.kh.spring3.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.PostVO;
import kr.kh.spring3.pagination.Criteria;
import kr.kh.spring3.pagination.PostCriteria;

public interface PostDAO {
	List<CommunityVO> selectCommunityList();

	List<PostVO> selectPostList(@Param("cri")Criteria cri);

	int selectPostTotalCount(@Param("cri")Criteria cri);
}