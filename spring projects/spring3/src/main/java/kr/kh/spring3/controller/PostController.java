package kr.kh.spring3.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.spring3.model.dto.MessageDTO;
import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.FileVO;
import kr.kh.spring3.model.vo.MemberVO;
import kr.kh.spring3.model.vo.PostVO;
import kr.kh.spring3.pagination.PageMaker;
import kr.kh.spring3.pagination.PostCriteria;
import kr.kh.spring3.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostService postService;
	
	@GetMapping("/list/{co_num}")
	public String postList(Model model, @PathVariable("co_num")int co_num, PostCriteria cri) {
		cri.setCo_num(co_num);
		List<CommunityVO> colist = postService.getCommunityList();
		List<PostVO> polist = postService.getPostList(cri);
		PageMaker pm = postService.getPageMaker(cri);
		
		
		model.addAttribute("colist", colist);
		model.addAttribute("polist", polist);
		model.addAttribute("pm", pm);
		return "/post/list";
	}
	
	@GetMapping("/detail/{po_num}")
	public String postDetail(Model model, @PathVariable("po_num")int po_num) {
		PostVO post = new PostVO(po_num);
		postService.updatePostView(post);
		PostVO po = postService.getPost(post);
		List<FileVO> fiList = postService.getFileList(po);
		
		model.addAttribute("po", po);
		model.addAttribute("fiList", fiList);
		return "/post/detail";
	}
	
	@GetMapping("/insert/{co_num}")
	public String postInsert(Model model, @PathVariable("co_num")int co_num) {
		model.addAttribute("co_num", co_num);
		return "/post/insert";
	}
	@PostMapping("/insert/{co_num}")
	public String postInsertPost(Model model, PostVO post, MultipartFile[] fiList, HttpSession session) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		boolean res = postService.insertPost(post, fiList, user);

		MessageDTO message;
		if(res) {
			message = new MessageDTO("/post/list/"+post.getPo_co_num(), "게시글을 등록했습니다.");
		}else {
			message = new MessageDTO("/post/list/"+post.getPo_co_num(), "게시글을 등록하지 못했습니다.");
		}
		model.addAttribute("message", message);
		return "/main/message";
	}
}