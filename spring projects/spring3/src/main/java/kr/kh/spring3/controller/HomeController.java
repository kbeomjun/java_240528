package kr.kh.spring3.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/guest/login")
	public String guestLogin() {
		return "/member/login";
	}
	@PostMapping("/guest/login")
	public String guestLoginPost(Model model, MemberVO member, HttpSession session) {
		MemberVO user = memberService.login(member);
		MessageDTO message;
		if(user != null && !user.getMe_pw().equals("1")) {
			message = new MessageDTO("/", "로그인을 했습니다.");
			model.addAttribute("user", user);
			session.removeAttribute("me_id");
		}else if(user == null) {
			message = new MessageDTO("/guest/login", "로그인을 하지 못했습니다.");
			session.setAttribute("me_id", "");
		}else {
			message = new MessageDTO("/guest/login", "비밀번호가 틀렸습니다.");
			session.setAttribute("me_id", user.getMe_id());
		}
		model.addAttribute("message", message);
		return "/main/message";
	}
	
	@GetMapping("/member/logout")
	public String memberLogout(Model model, HttpSession session) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		MessageDTO message = new MessageDTO("/", "로그아웃을 했습니다.");
		session.removeAttribute("user");
		model.addAttribute("message", message);
		return "/main/message";
	}
}