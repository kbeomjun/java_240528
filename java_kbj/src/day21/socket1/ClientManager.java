package day21.socket1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import program.Program;

public class ClientManager implements Program {
	private Scanner scan = new Scanner(System.in);
	private String fileName = "src/day21/socket1/client.txt";
	private List<String> list = new ArrayList<String>();
	private String ip = "192.168.30.22";
	private int port = 5001;
	@Override
	public void printMenu() {
		System.out.print("메뉴\n"
				+"1. 속담 추가\n"
				+"2. 속담 삭제\n"
				+"3. 속담 검색\n"
				+"4. 종료\n"
				+"메뉴 선택 : ");
	}
	@Override
	public void runMenu(int menu) throws Exception {
		switch(menu) {
		case 1:
			insert();
			break;
		case 2:
			delete();
			break;
		case 3:
			print();
			break;
		case 4:
			System.out.println("종료");
			break;
		default:
		}
	}
	private void insert() {
		System.out.print("속담 입력 : ");
		scan.nextLine();
		String contents = scan.nextLine();
		if(!list.contains(contents.trim())) {
			list.add(contents.trim());
			System.out.println("속담을 추가했습니다.");
		}
		else {
			System.out.println("중복된 속담입니다.");
		}
	}
	private void delete() {
		print();
		System.out.print("삭제할 속담 선택 : ");
		int index = scan.nextInt() - 1;
		try {
			list.remove(index);
			System.out.println("속담을 삭제했습니다.");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("잘못된 속담을 선택했습니다.");
		}
	}
	private void print() {
		if(list.size() == 0) {
			System.out.println("등록된 속담이 없습니다.");
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			System.out.println((i+1)+". "+list.get(i));
		}
	}
	@Override
	public void run() {
		int menu = 0;
		load();
		do {
			printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}while(menu != 4);
		save();
	}
	public void save() {
		try {
			Socket socket = new Socket(ip, port);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeUTF("save");
			oos.writeObject(list);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			save(fileName);
		}
	}
	@SuppressWarnings("unchecked")
	public void load() {
		try {
			Socket socket = new Socket(ip, port);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeUTF("load");
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			list = (List<String>)ois.readObject();
			System.out.println(list);
		} catch (Exception e) {
			load(fileName);
		}
	}
	@Override
	public void save(String fileName) {
		try(FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void load(String fileName) {
		try(FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis)){
			list = (List<String>)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}