package day21.socket2;

public class ClientMain {
	
	public static void main(String[] args) {
		/* 연락처 관리 프로그램을 구현
		 * 불러오기랑 저장은 소켓 통신을 활용해서 작성 */
		ContactManager cm = new ContactManager();
		cm.run();
	}
}