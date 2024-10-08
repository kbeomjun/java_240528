package kr.kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.spring.model.vo.CommunityVO;
import kr.kh.spring.service.PostService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    PostService postService;
	
    @RequestMapping("/community")
    public String community(Model model) {
    	List<CommunityVO> list = postService.getCommunityList();
    	model.addAttribute("list", list);
    	return "/admin/community";
    }
    
    @RequestMapping("/community/insert")
    public String communityInsert(Model model, String name) {
    	boolean res = postService.insertCommunity(name);
    	if(res) {
    		model.addAttribute("msg", "커뮤니티를 등록했습니다.");
    	}else {
    		model.addAttribute("msg", "커뮤니티를 등록하지 못했습니다.");
    	}
    	model.addAttribute("url", "/admin/community");
    	return "/main/message";
    }
    
    @ResponseBody
    @RequestMapping("/community/delete")
    public boolean communityDelete(Model model, CommunityVO community) {
    	boolean res = postService.deleteCommunity(community);
    	return true;
    }
    
    @RequestMapping("/community/update")
    public String communityUpdate(Model model, CommunityVO community) {
    	boolean res = postService.updateCommunity(community);
    	if(res) {
    		model.addAttribute("msg", "커뮤니티를 수정했습니다.");
    	}else {
    		model.addAttribute("msg", "커뮤니티를 수정하지 못했습니다.");
    	}
    	model.addAttribute("url", "/admin/community");
    	return "/main/message";
    }
}