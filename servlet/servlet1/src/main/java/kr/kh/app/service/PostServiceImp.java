package kr.kh.app.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.kh.app.dao.PostDAO;
import kr.kh.app.model.vo.CommentVO;
import kr.kh.app.model.vo.CommunityVO;
import kr.kh.app.model.vo.FileVO;
import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.model.vo.RecommendVO;
import kr.kh.app.pagination.Criteria;
import kr.kh.app.pagination.PageMaker;
import kr.kh.app.utils.FileUploadUtils;

public class PostServiceImp implements PostService {
	private PostDAO postDao;
	private String uploadPath = "D:\\uploads";
	
	public PostServiceImp() {
		String resource = "kr/kh/app/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			postDao = session.getMapper(PostDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	@Override
	public CommunityVO getCommunity(int coNum) {
		return postDao.selectCommunity(coNum);
	}

	@Override
	public List<PostVO> getPostList(Criteria cri) {
		if(cri == null) {
			throw new RuntimeException();
		}
		return postDao.selectPostList(cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri, int displayPageNum) {
		if(cri == null) {
			throw new RuntimeException();
		}
		int totalCount = postDao.selectPostTotalCount(cri);
		return new PageMaker(totalCount, displayPageNum, cri);
	}

	@Override
	public boolean insertPost(PostVO post, ArrayList<Part> files) {
		if(post == null) {
			return false;
		}
		if(post.getPo_title() == null || post.getPo_title().trim().length() == 0) {
			return false;
		}
		if(post.getPo_content() == null || post.getPo_content().trim().length() == 0) {
			return false;
		}
		boolean res =  postDao.insertPost(post);
		
		if(!res) {
			return false;
		}
		if(files == null || files.size() == 0) {
			return true;
		}
		for(Part file : files) {
			if("fileList".equals(file.getName())) {
				uploadFile(post.getPo_num(), file);
			}
		}
		return true;
	}

	private void uploadFile(int po_num, Part file) {
		if(file == null) {
			return;
		}
		String fileName = FileUploadUtils.getFileName(file);
		if(fileName == null || fileName.trim().length() == 0) {
			return;
		}
		
		// 첨부파일을 업로드하고 업로드된 경로와 파일명을 가져옴
		String uploadFileName = FileUploadUtils.upload(uploadPath, file);
		FileVO fileVO = new FileVO(po_num, fileName, uploadFileName);
		postDao.insertFile(fileVO);
	}

	@Override
	public PostVO getPost(int po_num) {
		return postDao.selectPost(po_num);
	}

	@Override
	public void updatePostView(int po_num) {
		postDao.updatePostView(po_num);
	}

	@Override
	public PostVO getPost(int po_num, MemberVO user) {
		if(user == null) {
			return null;
		}
		PostVO post = postDao.selectPost(po_num);
		if(post == null) {
			return null;
		}
		if(checkWriter(po_num, user)) {
			return post;
		}
		return null;
	}

	@Override
	public boolean updatePost(PostVO post, MemberVO user, List<Part> fileList, String[] fiNumStr) {
		if(post == null || user == null) {
			return false;
		}
		if(!checkWriter(post.getPo_num(), user)) {
			return false;
		}
		if(post.getPo_title() == null || post.getPo_title().trim().length() == 0) {
			return false;
		}
		if(post.getPo_content() == null || post.getPo_content().trim().length() == 0) {
			return false;
		}
		boolean res = postDao.updatePost(post);
		
		if(!res) {
			return false;
		}
		for(Part file : fileList) {
			if("fileList".equals(file.getName())) {
				uploadFile(post.getPo_num(), file);
			}
		}
		if(fiNumStr == null) {
			return true;
		}
		for(String num : fiNumStr) {
			try {
				int fi_num = Integer.parseInt(num);
				deleteFile(fi_num);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	private void deleteFile(int fi_num) {
		FileVO file = postDao.selectFile(fi_num);
		if(file == null) {
			return;
		}
		deleteFile(file);
		postDao.deleteFile(fi_num);
	}

	private boolean checkWriter(int po_num, MemberVO user) {
		if(user == null) {
			return false;
		}
		PostVO post = postDao.selectPost(po_num);
		if(post == null) {
			return false;
		}
		if(post.getPo_me_id().equals(user.getMe_id())) {
			return true;
		}
		return false;
	}

	@Override
	public void deletePost(PostVO post) {
		List<FileVO> fileList = postDao.selectFileList(post.getPo_num());
		for(FileVO file : fileList) {
			deleteFile(file);
		}
		postDao.deletePost(post);
	}

	private void deleteFile(FileVO file) {
		if(file == null) {
			return;
		}
		File realFile = new File(uploadPath + file.getFi_name().replace('/', File.separatorChar));
		if(realFile.exists()) {
			realFile.delete();
		}
	}

	@Override
	public int insertRecommend(RecommendVO recommend) {
		if(recommend == null) {
			throw new RuntimeException();
		}
		RecommendVO dbRecommend = postDao.selectRecommend(recommend);
		if(dbRecommend != null) {
			if(dbRecommend.getRe_state() != recommend.getRe_state()) {
				postDao.deleteRecommend(dbRecommend.getRe_num());
				postDao.insertRecommend(recommend);
				return 1;
			}else {
				postDao.deleteRecommend(dbRecommend.getRe_num());
				return 0;
			}
		}else {
			postDao.insertRecommend(recommend);
			return 1;
		}
	}

	@Override
	public RecommendVO getRecommend(int po_num, MemberVO user) {
		if(user == null) {
			return null;
		}
		RecommendVO recommend = new RecommendVO(po_num, 0, user.getMe_id());
		return postDao.selectRecommend(recommend);
	}

	@Override
	public List<CommentVO> getCommentList(Criteria cri) {
		if(cri == null) {
			return null;
		}
		return postDao.selectCommentList(cri);
	}

	@Override
	public PageMaker getCommentPageMaker(Criteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount =  postDao.selectCommentTotalCount(cri);
		return new PageMaker(totalCount, 2, cri);
	}

	@Override
	public boolean insertComment(CommentVO comment) {
		if(comment == null) {
			return false;
		}
		if(comment.getCm_content() == null || comment.getCm_content().trim().length() == 0) {
			return false;
		}
		return postDao.insertComment(comment);
	}

	@Override
	public boolean deleteComment(int cm_num, int cm_ori_num, MemberVO user) {
		if(user == null) {
			return false;
		}
		CommentVO comment = postDao.selectComment(cm_num);
		if(comment == null) {
			return false;
		}
		if(!comment.getCm_me_id().equals(user.getMe_id())) {
			return false;
		}
		if(cm_num == cm_ori_num) {
			return postDao.deleteAllComment(cm_num);
		}else {
			return postDao.deleteComment(cm_num);
		}
	}

	@Override
	public boolean updateComment(CommentVO comment, MemberVO user) {
		if(user == null || comment == null) {
			return false;
		}
		CommentVO dbComment = postDao.selectComment(comment.getCm_num());
		if(dbComment == null) {
			return false;
		}
		if(!dbComment.getCm_me_id().equals(user.getMe_id())) {
			return false;
		}
		return postDao.updateComment(comment);
	}

	@Override
	public List<FileVO> getFileList(int po_num) {
		return postDao.selectFileList(po_num);
	}
}