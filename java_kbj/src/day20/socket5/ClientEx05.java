package day20.socket5;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientEx05 {

	public static void main(String[] args) {
		int port = 5001;
		String ip = "192.168.30.22";
		try {
			System.out.print("아이디 입력 : ");
			Scanner scan = new Scanner(System.in);
			String id = scan.next();
			Socket socket = new Socket(ip, port);
			System.out.println("[연결 성공]");
			Client client = new Client(id, socket);
			client.send();
			client.receive();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}