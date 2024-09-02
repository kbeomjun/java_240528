package kr.kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.spring.model.vo.CommunityVO;
import kr.kh.spring.model.vo.FileVO;
import kr.kh.spring.model.vo.MemberVO;
import kr.kh.spring.model.vo.PostVO;
import kr.kh.spring.pagination.PageMaker;
import kr.kh.spring.pagination.PostCriteria;
import kr.kh.spring.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostService postService;
	
	@GetMapping("/list")
	public String list(Model model, PostCriteria cri) {
		List<CommunityVO> list = postService.getCommunityList();
		
		cri.setPerPageNum(2);
		List<PostVO> postList = postService.getPostList(cri);
		
		PageMaker pm = postService.getPageMaker(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("postList", postList);
		model.addAttribute("pm", pm);
		return "/post/list";
	}
	
	@GetMapping("/insert")
	public String insert(Model model, Integer co_num) {
		model.addAttribute("co_num", co_num);
		return "/post/insert";
	}
	@PostMapping("/insert")
	public String insertPost(Model model, PostVO post, MultipartFile[] fileList, HttpSession session) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(postService.insertPost(post, user, fileList)) {
			model.addAttribute("url", "/post/list?co_num="+post.getPo_co_num());
			model.addAttribute("msg", "게시글을 등록했습니다.");
		}else {
			model.addAttribute("url", "/post/insert?co_num="+post.getPo_co_num());
			model.addAttribute("msg", "게시글을 등록하지 못했습니다.");
		}
		return "/main/message";
	}
	
	@GetMapping("/detail")
	public String detail(Model model, PostVO post, PostCriteria cri) {
		postService.updatePostView(post);
		post = postService.getPost(post);
		List<FileVO> list = postService.getFileList(post);
		model.addAttribute("po", post);
		model.addAttribute("list", list);
		model.addAttribute("cri", cri);
		return "/post/detail";
	}
	
	@GetMapping("/update")
	public String update(Model model, PostVO post, PostCriteria cri) {
		post = postService.getPost(post);
		List<FileVO> list = postService.getFileList(post);
		model.addAttribute("po", post);
		model.addAttribute("list", list);
		model.addAttribute("cri", cri);
		return "/post/update";
	}
	@PostMapping("/update")
	public String updatePost(Model model, PostVO post, int[] fi_nums, MultipartFile[] fileList, PostCriteria cri) {
		if(postService.updatePost(post, fi_nums, fileList)) {
			model.addAttribute("url", "/post/detail?po_num="+post.getPo_num()+"&"+cri);
			model.addAttribute("msg", "게시글을 수정했습니다.");
		}else {
			model.addAttribute("url", "/post/detail?po_num="+post.getPo_num()+"&"+cri);
			model.addAttribute("msg", "게시글을 수정하지 못했습니다.");
		}
		return "/main/message";
	}
	
	@GetMapping("/delete")
	public String delete(Model model, PostVO post, PostCriteria cri) {
		if(postService.deletePost(post)) {
			model.addAttribute("url", "/post/list?"+cri);
			model.addAttribute("msg", "게시글을 삭제했습니다.");
		}else {
			model.addAttribute("url", "/post/detail?po_num="+post.getPo_num()+"&"+cri);
			model.addAttribute("msg", "게시글을 삭제하지 못했습니다.");
		}
		return "/main/message";
	}
	
	@ResponseBody
	@PostMapping("/community/list")
	public List<CommunityVO> communityList(){
		return postService.getCommunityList();
	}
}