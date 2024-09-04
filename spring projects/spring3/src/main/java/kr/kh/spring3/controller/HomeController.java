package kr.kh.spring3.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.kh.spring3.model.dto.MessageDTO;
import kr.kh.spring3.model.vo.MemberVO;
import kr.kh.spring3.service.MemberService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class HomeController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("title", "메인");
		return "/main/home";
	}
	
	@GetMapping("/guest/signup")
	public String guestSignup() {
		return "/member/signup";
	}
	@PostMapping("/guest/signup")
	public String guestSignupPost(Model model, MemberVO member) {
		boolean res = memberService.signup(member);
		MessageDTO message;
		if(res) {
			message = new MessageDTO("/", "회원가입을 했습니다.");
		}else {
			message = new MessageDTO("/guest/signup", "회원가입을 하지 못했습니다.");
		}
		model.addAttribute("message", message);
		return "/main/message";
	}
}