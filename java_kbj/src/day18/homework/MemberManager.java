package day18.homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import program.Program;

public class MemberManager implements Program {
	private List<Member> list = new ArrayList<Member>();
	private Scanner scan = new Scanner(System.in);
	private final int INSERT = 1, UPDATE = 2, DELETE = 3, EXIT = 4;
	@Override
	public void printMenu() {
		System.out.println("메뉴\n"
				+ "1. 회원 추가\n"
				+ "2. 회원 수정\n"
				+ "3. 회원 삭제\n"
				+ "4. 이전으로");
		System.out.print("메뉴 선택 : ");
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
		case EXIT:
			break;
		default:
			System.out.println("---------------------------------");
			System.out.println("잘못된 메뉴입니다.");
			System.out.println("---------------------------------");
		}
	}
	private void insert() {
		System.out.println("추가할 회원 정보 입력");
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.println("---------------------------------");
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(id)) {
				System.out.println("중복된 아이디입니다.");
				return;
			}
		}
		list.add(new Member(id, name, new ArrayList<Schedule>()));
		System.out.println("회원이 추가됐습니다.");
		System.out.println("---------------------------------");
	}
	private void update() {
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.println("---------------------------------");
		int index = -1;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(id)) {
				index = i;
			}
		}
		if(index == -1) {
			System.out.println("등록된 아이디가 없습니다.");
			return;
		}
		System.out.print("수정할 이름 : ");
		String name = scan.next();
		System.out.println("---------------------------------");
		list.get(index).setName(name);
		System.out.println("회원 정보를 수정했습니다.");
		System.out.println("---------------------------------");
	}
	private void delete() {
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.println("---------------------------------");
		int index = -1;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(id)) {
				index = i;
			}
		}
		if(index == -1) {
			System.out.println("등록된 아이디가 없습니다.");
			return;
		}
		list.remove(index);
		System.out.println("회원 정보를 삭제했습니다.");
		System.out.println("---------------------------------");
	}
	@Override
	public void run() throws Exception {
		String fileName = "src/day18/homework/member.txt";
		//load(fileName);
		//list = new ArrayList<Member>();
		int menu = 0;
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu);
		}while(menu != EXIT);
		save(fileName);
	}
	@Override
	public void save(String filename) {
		try(FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(list);
			} catch (IOException e) {
				System.out.println("입출력 예외 발생");
				e.printStackTrace();
			}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void load(String filename) {
		try(FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis)){
			list = (List<Member>)ois.readObject();
		} catch (IOException e) {
			System.out.println("입출력 예외 발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("클래스를 찾을수 없습니다.");
			e.printStackTrace();
		} 
	}
}