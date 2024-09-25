package kr.kh.boot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.kh.boot.model.vo.CommunityVO;
import kr.kh.boot.model.vo.PostVO;
import kr.kh.boot.pagination.PageMaker;
import kr.kh.boot.pagination.PostCriteria;
import kr.kh.boot.service.PostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PostController {
	private PostService postService;
	
	@GetMapping("/post/list/{co_num}")
	public String postList(Model model, @PathVariable int co_num, PostCriteria cri) {
		List<CommunityVO> coList = postService.getCommunityList();
		
		cri.setCo_num(co_num);
		List<PostVO> poList = postService.getPostList(cri);
		PageMaker pm = postService.getPageMaker(cri);
		
		model.addAttribute("coList", coList);
		model.addAttribute("poList", poList);
		model.addAttribute("pm", pm);
		return "post/list";
	}
	
	@GetMapping("/post/detail/{po_num}")
	public String postDetail(Model model, @PathVariable int po_num, PostVO post) {
		post.setPo_num(po_num);
		postService.updatePostView(post);
		PostVO postVO = postService.getPost(post);
		model.addAttribute("po", postVO);
		return "post/detail";
	}
}