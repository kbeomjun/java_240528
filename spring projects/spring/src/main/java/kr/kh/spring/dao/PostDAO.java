package kr.kh.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.spring.model.vo.CommunityVO;
import kr.kh.spring.model.vo.FileVO;
import kr.kh.spring.model.vo.PostVO;
import kr.kh.spring.pagination.PostCriteria;

public interface PostDAO {
	List<CommunityVO> selectCommunityList();

	List<PostVO> selectPostList(@Param("cri")PostCriteria cri);

	int selectPostTotalCount(@Param("cri")PostCriteria cri);

	boolean insertPost(@Param("po")PostVO post);

	void insertFile(@Param("fi")FileVO fileVO);

	void updatePostView(@Param("po")PostVO post);

	PostVO selectPost(@Param("po")PostVO post);

	List<FileVO> selectFileList(@Param("po")PostVO post);

	FileVO selectFile(@Param("fi_num")int fi_num);

	void deleteFile(@Param("fi_num")int fi_num);

	boolean updatePost(@Param("po")PostVO post);

	boolean deletePost(@Param("po")PostVO post);

	boolean insertCommunity(@Param("co")CommunityVO community);
}