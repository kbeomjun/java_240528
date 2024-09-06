package kr.kh.spring3.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.FileVO;
import kr.kh.spring3.model.vo.PostVO;
import kr.kh.spring3.pagination.Criteria;

public interface PostDAO {
	List<CommunityVO> selectCommunityList();

	List<PostVO> selectPostList(@Param("cri")Criteria cri);

	int selectPostTotalCount(@Param("cri")Criteria cri);

	PostVO selectPost(@Param("po")PostVO post);

	void updatePostView(@Param("po")PostVO post);

	List<FileVO> selectFileList(@Param("po")PostVO po);

	boolean insertPost(@Param("po")PostVO post);

	void insertFile(@Param("fi")FileVO file);
}