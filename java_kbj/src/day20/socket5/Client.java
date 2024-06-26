package day20.socket5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import lombok.AllArgsConstructor;

// 연결된 소켓을 이용하여 데이터를 주고 받는(Scanner) 클래스
@AllArgsConstructor
public class Client {
	private String id;
	private Socket socket;
	public final static String EXIT = "-1";
	
	// 소켓에서 보내온 문자열을 받아서 출력하는 쓰레드를 생성하고 실행하는 메서드
	public void receive() {
		Thread t = new Thread(()->{
			String id = "";
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				while(true) {
					id = ois.readUTF();
					String chat = ois.readUTF();
					if(chat.equals(EXIT)) {
						System.out.println("[상대가 전송을 중단했습니다.]");
						break;
					}
					System.out.println(id+" : "+chat);
				}
			} catch (IOException e) {
				System.out.println("["+id+"님이 나갔습니다]");
			} catch (Exception e) {
				System.out.println("[예외 발생]");
			}
		});
		t.start();
	}
	
	// 문자열을 입력해서 소켓으로 전송하는 쓰레드를 생성하고 실행하는 메서드
	public void send() {
		Thread t = new Thread(()->{
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				Scanner scan = new Scanner(System.in);
				while(true) {
					String str = scan.nextLine();
					oos.writeUTF(id);
					oos.writeUTF(str);
					oos.flush();
					if(str.equals(EXIT)) {
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("[전송 종료]");
		});
		t.start();
	}
}