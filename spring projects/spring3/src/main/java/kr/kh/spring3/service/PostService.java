package kr.kh.spring3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.spring3.dao.PostDAO;
import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.FileVO;
import kr.kh.spring3.model.vo.MemberVO;
import kr.kh.spring3.model.vo.PostVO;
import kr.kh.spring3.pagination.Criteria;
import kr.kh.spring3.pagination.PageMaker;
import kr.kh.spring3.utils.UploadFileUtils;

@Service
public class PostService {
	@Autowired
	PostDAO postDao;
	@Resource
	String uploadPath;

	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	public List<PostVO> getPostList(Criteria cri) {
		return postDao.selectPostList(cri);
	}

	public PageMaker getPageMaker(Criteria cri) {
		int totalCount = postDao.selectPostTotalCount(cri);
		return new PageMaker(2, cri, totalCount);
	}

	public PostVO getPost(PostVO post) {
		return postDao.selectPost(post);
	}

	public void updatePostView(PostVO post) {
		postDao.updatePostView(post);
	}

	public List<FileVO> getFileList(PostVO po) {
		return postDao.selectFileList(po);
	}

	public boolean insertPost(PostVO post, MultipartFile[] fiList, MemberVO user) {
		if(post == null || user == null) {
			return false;
		}
		
		post.setPo_me_id(user.getMe_id());
		boolean res = postDao.insertPost(post);
		if(!res) {
			return false;
		}
		
		if(fiList == null || fiList.length == 0) {
			return true;
		}
		for(MultipartFile file : fiList) {
			uploadFile(file, post.getPo_num());
		}
		return true;
	}

	private void uploadFile(MultipartFile file, int po_num) {
		if(file == null || file.getOriginalFilename().length() == 0) {
			return;
		}
		try {
			String fi_ori_name = file.getOriginalFilename();
			// 첨부파일을 서버에 업로드 후 경로가 포함된 파일명을 가져옴
			String fi_name = UploadFileUtils.uploadFile(uploadPath, fi_ori_name, file.getBytes());
			FileVO fileVO = new FileVO(fi_name, fi_ori_name, po_num);
			postDao.insertFile(fileVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}