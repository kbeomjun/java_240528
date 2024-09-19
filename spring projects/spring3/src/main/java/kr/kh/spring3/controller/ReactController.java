package kr.kh.spring3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.kh.spring3.model.dto.PersonDTO;
import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.PostVO;
import kr.kh.spring3.pagination.PageMaker;
import kr.kh.spring3.pagination.PostCriteria;
import kr.kh.spring3.service.PostService;

@RestController
@RequestMapping("/react")
public class ReactController {
	@Autowired
	PostService postService;
	
	@GetMapping("/community/list")
	public List<CommunityVO> communityList(){
		return postService.getCommunityList();
	}
	
	@GetMapping("/post/list/{co_num}")
	public Map<String, Object> list(Model model, @PathVariable("co_num")int co_num, PostCriteria cri) {
		cri.setCo_num(co_num);
		cri.setPerPageNum(5);
		List<PostVO> polist = postService.getPostList(cri);
		PageMaker pm = postService.getPageMaker(cri);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", polist);
		map.put("pm", pm);
		return map;
	}
	
	@GetMapping("/post/detail/{po_num}")
	public Object detail(@PathVariable("po_num")int po_num) {
		PostVO post = new PostVO(po_num);
		postService.updatePostView(post);
		PostVO postVo = postService.getPost(post);
		return postVo;
	}
	
	// react1이랑 연동, 데이터 주고 받기
	@GetMapping("/get/str")
	public String getStr() {
		return "Hi";
	}
	@GetMapping("/get/obj")
	public Object getObj() {
		PersonDTO person = new PersonDTO("홍길동", 20);
		return person;
	}
	@PostMapping("/send/person")
	public String sendPerson(@RequestParam("name") String name,	@RequestParam("age") int age) {
		System.out.println(name);
		System.out.println(age);
		return "OK";
	}
	@PostMapping("/send/person2")
	public String sendPerson2(@RequestBody PersonDTO person) {
		System.out.println(person);
		return "OK";
	}
}