package kr.kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kh.spring.Pagination.PageMaker;
import kr.kh.spring.Pagination.PostCriteria;
import kr.kh.spring.model.vo.CommunityVO;
import kr.kh.spring.model.vo.PostVO;
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
}