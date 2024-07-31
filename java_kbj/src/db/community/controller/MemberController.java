package db.community.controller;

import java.util.Scanner;
import db.community.model.vo.MemberVO;
import db.community.service.MemberService;
import db.community.service.MemberServiceImp;

public class MemberController {
	private Scanner scan;
	private MemberService memberService = new MemberServiceImp();
	public MemberController(Scanner scan) {
		this.scan = scan;
	}
	public MemberVO login() {
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.print("비번 : ");
		String pw = scan.next();
		MemberVO user = memberService.login(id, pw);
		return user;
	}
	public boolean signup() {
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.print("비번 : ");
		String pw = scan.next();
		System.out.print("비번 확인 : ");
		String pw2 = scan.next();
		System.out.print("이메일 : ");
		String email = scan.next();
		if(!pw.equals(pw2)) {
			return false;
		}
		return memberService.signup(id, pw, email);
	}
}