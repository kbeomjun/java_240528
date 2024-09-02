package kr.kh.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.spring.dao.CommentDAO;
import kr.kh.spring.model.vo.CommentVO;
import kr.kh.spring.model.vo.MemberVO;
import kr.kh.spring.pagination.Criteria;
import kr.kh.spring.pagination.PageMaker;

@Service
public class CommentService {
	@Autowired
	CommentDAO commentDao;

	public boolean insertComment(CommentVO comment, MemberVO user) {
		if(comment == null || user == null) {
			return false;
		}
		comment.setCm_me_id(user.getMe_id());
		return commentDao.insertComment(comment);
	}

	public List<CommentVO> getCommentList(Criteria cri) {
		if(cri == null) {
			return null;
		}
		return commentDao.selectCommentList(cri);
	}

	public PageMaker getCommentPageMaker(Criteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount = commentDao.selectCommentTotalCount(cri);
		return new PageMaker(2, cri, totalCount);
	}

	public boolean deleteComment(CommentVO comment, MemberVO user) {
		CommentVO commentVO = commentDao.selectComment(comment);
		if(!commentVO.getCm_me_id().equals(user.getMe_id())) {
			return false;
		}
		return commentDao.deleteComment(commentVO);
	}

	public CommentVO getComment(CommentVO comment, MemberVO user) {
		CommentVO commentVO = commentDao.selectComment(comment);
		if(!commentVO.getCm_me_id().equals(user.getMe_id())) {
			return null;
		}
		return commentVO;
	}

	public boolean updateComment(CommentVO comment, MemberVO user) {
		CommentVO commentVO = commentDao.selectComment(comment);
		if(!commentVO.getCm_me_id().equals(user.getMe_id())) {
			return false;
		}
		return commentDao.updateComment(comment);
	}
}