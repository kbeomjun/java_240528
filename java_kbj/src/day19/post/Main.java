package day19.post;

public class Main {

	public static void main(String[] args) {
		/* 회원가입 없이 게시글을 등록/수정/삭제/조회하는 프로그램 */
		PostManager pm = new PostManager();
		try {
			pm.run();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다.");
			e.printStackTrace();
		}
	}
}