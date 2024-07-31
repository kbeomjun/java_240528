package db.community.controller;

import java.util.Scanner;
import db.community.service.PostService;
import db.community.service.PostServiceImp;

public class PostController {
	private Scanner scan;
	private PostService postService = new PostServiceImp();
	public PostController(Scanner scan) {
		this.scan = scan;
	}
	public void insertCommunity() {
		System.out.println("커뮤니티명 : ");
		String community = scan.nextLine();
		if(postService.insertCommunity(community)) {
			System.out.println("커뮤니티 등록 성공!");
			return;
		}
		System.out.println("커뮤니티 등록 실패!");
	}
}