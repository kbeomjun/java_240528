package kr.kh.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.spring.model.vo.CommentVO;
import kr.kh.spring.pagination.Criteria;

public interface CommentDAO {
	boolean insertComment(@Param("cm")CommentVO comment);

	List<CommentVO> selectCommentList(@Param("cri")Criteria cri);

	int selectCommentTotalCount(@Param("cri")Criteria cri);

	CommentVO selectComment(@Param("cm")CommentVO comment);

	boolean deleteComment(@Param("cm")CommentVO comment);

	boolean updateComment(@Param("cm")CommentVO comment);
}