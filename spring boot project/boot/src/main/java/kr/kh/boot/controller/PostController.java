package kr.kh.boot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		postService.updatePostView(post);
		PostVO postVO = postService.getPost(post);
		model.addAttribute("po", postVO);
		return "post/detail";
	}
	
	@GetMapping("/post/insert/{co_num}")
	public String postInsert(@PathVariable int co_num) {
		return "post/insert";
	}
	@PostMapping("/post/insert")
	public String postInsertPost(PostVO post) {
		boolean res = postService.insertPost(post);
		if(res) {
			return "redirect:/post/list/"+post.getPo_co_num();
		}else {
			return "redirect:/post/insert/"+post.getPo_co_num();
		}
	}
	
	@GetMapping("/post/update/{po_num}")
	public String postUpdate(Model model, @PathVariable int po_num, PostVO post) {
		PostVO postVO = postService.getPost(post);
		model.addAttribute("po", postVO);
		return "post/update";
	}
	@PostMapping("/post/update")
	public String postUpdatePost(PostVO post) {
		boolean res = postService.updatePost(post);
		if(res) {
			return "redirect:/post/detail/"+post.getPo_num();
		}else {
			return "redirect:/post/update/"+post.getPo_num();
		}
	}
	
	@GetMapping("/post/delete/{po_num}")
	public String postDelete(@PathVariable int po_num, PostVO post) {
		PostVO postVO = postService.getPost(post);
		boolean res = postService.deletePost(postVO);
		if(res) {
			return "redirect:/post/list/"+postVO.getPo_co_num();
		}else {
			return "redirect:/post/detail/"+postVO.getPo_num();
		}
	}
}