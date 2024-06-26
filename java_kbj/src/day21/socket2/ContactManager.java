package day21.socket2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import program.Program;

public class ContactManager implements Program {
	private Scanner scan = new Scanner(System.in);
	private List<Contact> list = new ArrayList<Contact>();
	private String fileName = "src/day21/socket2/clientContact.txt";
	private String ip = "192.168.30.22";
	private int port = 5001;
	private final int INSERT = 1, UPDATE = 2, DELETE = 3, SEARCH = 4, EXIT = 5;
	@Override
	public void printMenu() {
		System.out.print("메뉴\n"
				+ "1. 연락처 추가\n"
				+ "2. 연락처 수정\n"
				+ "3. 연락처 삭제\n"
				+ "4. 연락처 확인\n"
				+ "5. 프로그램 종료\n"
				+ "메뉴 선택 : ");
	}
	@Override
	public void runMenu(int menu) throws Exception {
		switch(menu) {
		case INSERT:
			insert();
			break;
		case UPDATE:
			update();
			break;
		case DELETE:
			delete();
			break;
		case SEARCH:
			search();
			break;
		case EXIT:
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private void search() {
		System.out.print("검색(전체 검색 : 엔터) : ");
		scan.nextLine();
		String name = scan.nextLine();
		List<Contact> tmps = new ArrayList<Contact>();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().contains(name)) {
				tmps.add(list.get(i));
			}
		}
		for(int i = 0; i < tmps.size(); i++) {
			System.out.println((i+1)+". "+tmps.get(i).toString());
		}
		System.out.print("메뉴로 가려면 엔터를 입력하세요.");
		String enter = scan.nextLine();
		if(enter.equals("\n")) {
			return;
		}
	}
	private void delete() {
		System.out.print("이름 입력 : ");
		String name = scan.next();
		List<Contact> tmps = new ArrayList<Contact>();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().contains(name)) {
				tmps.add(list.get(i));
			}
		}
		for(int i = 0; i < tmps.size(); i++) {
			System.out.println((i+1)+". "+tmps.get(i).toString());
		}
		System.out.print("삭제할 연락처 선택 : ");
		int index = scan.nextInt() - 1;
		if(index < 0 || index > tmps.size() - 1) {
			System.out.println("잘못 선택하였습니다.");
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(tmps.get(index))) {
				index = i;
			}
		}
		list.remove(index);
		System.out.println("연락처를 삭제했습니다.");
	}
	private void update() {
		System.out.print("이름 입력 : ");
		String name = scan.next();
		List<Contact> tmps = new ArrayList<Contact>();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().contains(name)) {
				tmps.add(list.get(i));
			}
		}
		for(int i = 0; i < tmps.size(); i++) {
			System.out.println((i+1)+". "+tmps.get(i).toString());
		}
		System.out.print("수정할 연락처 선택 : ");
		int index = scan.nextInt() - 1;
		if(index < 0 || index > tmps.size() - 1) {
			System.out.println("잘못 선택하였습니다.");
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(tmps.get(index))) {
				index = i;
			}
		}
		System.out.print("이름 : ");
		name = scan.next();
		System.out.print("번호 : ");
		String number = scan.next();
		String regex = "^010-\\d{4}-\\d{4}$";
		if(!Pattern.matches(regex, number)) {
			System.out.println("번호 형식이 아닙니다.");
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			if(i == index) {
				continue;
			}
			if(list.get(i).getNumber().equals(number)) {
				System.out.println("이미 등록된 번호입니다.");
				return;
			}
		}
		list.set(index, new Contact(name, number));
		Collections.sort(list);
		System.out.println("연락처를 수정했습니다.");
	}
	private void insert() {
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("번호 : ");
		String number = scan.next();
		String regex = "^010-\\d{4}-\\d{4}$";
		if(!Pattern.matches(regex, number)) {
			System.out.println("번호 형식이 아닙니다.");
			return;
		}
		for(Contact tmp : list) {
			if(tmp.getNumber().equals(number)) {
				System.out.println("이미 등록된 번호입니다.");
				return;
			}
		}
		list.add(new Contact(name, number));
		Collections.sort(list);
		System.out.println("연락처를 추가했습니다.");
	}
	@Override
	public void run() {
		load();
		int menu = 0;
		do {
			printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}while(menu != EXIT);
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
			list = (List<Contact>)ois.readObject();
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
			list = (List<Contact>)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}