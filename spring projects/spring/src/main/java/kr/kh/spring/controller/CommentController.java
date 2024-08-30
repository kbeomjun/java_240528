package kr.kh.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.spring.model.vo.CommentVO;
import kr.kh.spring.model.vo.MemberVO;
import kr.kh.spring.pagination.Criteria;
import kr.kh.spring.pagination.PageMaker;
import kr.kh.spring.service.CommentService;

//@RestController // 컨트롤러 안에 있는 메서드에 @ResponseBody를 붙이지 않아도 ajax로 통신
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
	private CommentService commentSerivce;
    
    @ResponseBody
    @PostMapping("/insert")
    public boolean insert(@RequestBody CommentVO comment, HttpSession session) {
    	MemberVO user = (MemberVO)session.getAttribute("user");
    	return commentSerivce.insertComment(comment, user);
    }
    
    @ResponseBody
    @PostMapping("/list")
    public Map<String, Object> list(@RequestBody Criteria cri){
    	cri.setPerPageNum(5);
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	List<CommentVO> list = commentSerivce.getCommentList(cri);
    	map.put("list", list);
    	
    	PageMaker pm = commentSerivce.getCommentPageMaker(cri);
    	map.put("pm", pm);
    	
    	return map;
    }
    
    @PostMapping("/list2")
    public String list2(Model model, @RequestBody Criteria cri){
    	cri.setPerPageNum(5);
    	System.out.println(cri);
    	List<CommentVO> list = commentSerivce.getCommentList(cri);
    	
    	PageMaker pm = commentSerivce.getCommentPageMaker(cri);
    	
    	model.addAttribute("list", list);
    	model.addAttribute("pm", pm);
    	return "comment/pagination";
    }
}