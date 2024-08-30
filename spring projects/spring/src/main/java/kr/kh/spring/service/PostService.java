package kr.kh.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.spring.dao.PostDAO;
import kr.kh.spring.model.vo.CommunityVO;
import kr.kh.spring.model.vo.FileVO;
import kr.kh.spring.model.vo.MemberVO;
import kr.kh.spring.model.vo.PostVO;
import kr.kh.spring.pagination.PageMaker;
import kr.kh.spring.pagination.PostCriteria;
import kr.kh.spring.utils.UploadFileUtils;

@Service
public class PostService {
	@Autowired
	PostDAO postDao;
	@Resource
	String uploadPath;

	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	public List<PostVO> getPostList(PostCriteria cri) {
		if(cri == null) {
			return null;
		}
		return postDao.selectPostList(cri);
	}

	public PageMaker getPageMaker(PostCriteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount = postDao.selectPostTotalCount(cri);
		return new PageMaker(2, cri, totalCount);
	}

	public boolean insertPost(PostVO post, MemberVO user, MultipartFile[] fileList) {
		if(post == null || user == null) {
			return false;
		}
		
		boolean res;
		try {
			post.setPo_me_id(user.getMe_id());
			res = postDao.insertPost(post);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(!res) {
			return false;
		}
		
		if(fileList == null || fileList.length == 0) {
			return true;
		}
		for(MultipartFile file : fileList) {
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

	public void updatePostView(PostVO post) {
		postDao.updatePostView(post);
	}

	public PostVO getPost(PostVO post) {
		if(post == null) {
			return null;
		}
		return postDao.selectPost(post);
	}

	public List<FileVO> getFileList(PostVO post) {
		if(post == null) {
			return null;
		}
		return postDao.selectFileList(post);
	}

	public boolean updatePost(PostVO post, int[] fi_nums, MultipartFile[] fileList) {
		if(post == null) {
			return false;
		}
		
		boolean res;
		try {
			res = postDao.updatePost(post);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(!res) {
			return false;
		}
		
		if(fi_nums != null) {
			for(int fi_num : fi_nums) {
				deleteFile(fi_num);
			}
		}
		
		if(fileList == null || fileList.length == 0) {
			return true;
		}else {
			for(MultipartFile file : fileList) {
				uploadFile(file, post.getPo_num());
			}
		}
		return true;
	}

	private void deleteFile(int fi_num) {
		FileVO file = postDao.selectFile(fi_num);
		UploadFileUtils.delteFile(uploadPath, file.getFi_name());
		postDao.deleteFile(fi_num);
	}

	public boolean deletePost(PostVO post) {
		List<FileVO> fileList = postDao.selectFileList(post);
		if(fileList != null) {
			for(FileVO file : fileList) {
				UploadFileUtils.delteFile(uploadPath, file.getFi_name());
			}
		}
		return postDao.deletePost(post);
	}
}