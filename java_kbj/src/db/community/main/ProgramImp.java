package db.community.main;

import java.util.Scanner;
import db.community.controller.MemberController;
import db.community.controller.PostController;
import db.community.controller.PrintController;
import db.community.model.vo.MemberVO;
import program.Program;

public class ProgramImp implements Program {
	private Scanner scan = new Scanner(System.in);
	private MemberVO member = null;
	private MemberController memberController = new MemberController(scan);
	private PostController postController = new PostController(scan);
	@Override
	public void printMenu() {
		PrintController.printMainMenu();
	}
	@Override
	public void run() {
		char menu = '0';
		do {
			printMenu();
			try {
				menu = scan.next().charAt(0);
				PrintController.printBar();
				runMenu(menu);
				PrintController.printBar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}while(menu != '3');
	}
	@Override
	public void save(String fileName) {}
	@Override
	public void load(String fileName) {}
	@Override
	public void runMenu(int menu) throws Exception {
		char ch = (char)menu;
		switch(ch) {
		case '1':
			login();
			break;
		case '2':
			signup();
			break;
		case '3':
			PrintController.exit();
			break;
		default:
			PrintController.wrongMenu();
		}
	}
	private void login() {
		member = memberController.login();
		if(member == null) {
			PrintController.loginFail();
			return;
		}
		if(member.getMe_authority().equals("ADMIN")) {
			admin();
			return;
		}
		user();
	}
	private void user() {
		System.out.println("사용자입니다.");
	}
	private void admin() {
		PrintController.printBar();
		System.out.println("관리자님 환영합니다.");
		PrintController.printBar();
		char menu = '0';
		do {
			PrintController.printAdminMenu();
			menu = scan.next().charAt(0);
			PrintController.printBar();
			runAdmin(menu);
		}while(menu != '5');
	}
	private void runAdmin(char menu) {
		switch(menu) {
		case '1':
			postController.insertCommunity();
			break;
		case '2':
			
			break;
		case '3':
			
			break;
		case '4':
			
			break;
		case '5':
			PrintController.logout();
			break;
		default:
			PrintController.wrongMenu();
		}
	}
	private void signup() {
		if(memberController.signup()) {
			PrintController.signupSuccess();
		}else {
			PrintController.signupFail();
		}
	}
}