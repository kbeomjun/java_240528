package day17.contact;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import program.Program;

public class ContactManager implements Program {
	private List<Contact> list = new ArrayList<Contact>();
	private Scanner scan = new Scanner(System.in);
	private final int INSERT = 1, UPDATE = 2, DELETE = 3, SEARCH = 4, EXIT = 5;
	@Override
	public void printMenu() {
		System.out.println("---------------------");
		System.out.print("메뉴\n"
				+ "1. 연락처 추가\n"
				+ "2. 연락처 수정\n"
				+ "3. 연락처 삭제\n"
				+ "4. 연락처 검색\n"
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
			System.out.println("잘못된 메뉴입니다. 올바른 메뉴를 선택하세요.");
		}
	}
	private void insert() {
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("번호 : ");
		String number = scan.next();
		String regex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
		if(!Pattern.matches(regex, number)) {
			System.out.println("잘못된 번호입니다.");
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getNumber().equals(number)) {
				System.out.println("이미 등록된 번호입니다.");
				return;
			}
		}
		list.add(new Contact(number, name));
		System.out.println("연락처를 추가했습니다.");
	}
	private void update() {
		scan.nextLine();
		System.out.print("검색(엔터 : 전체 검색) : ");
		String word = scan.nextLine();
		int count = 0;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().contains(word) || list.get(i).getNumber().contains(word)) {
				System.out.println((i+1)+". "+list.get(i).toString());
				count++;
			}
		}
		if(count == 0) {
			System.out.println("일치하는 연락처가 없습니다.");
			return;
		}
		System.out.print("번호 선택 : ");
		int index = scan.nextInt() - 1;
		if(index < 0 || index >= list.size() || !list.get(index).getName().contains(word)) {
			System.out.println("잘못된 번호입니다.");
			return;
		}
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("번호 : ");
		String number = scan.next();
		String regex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
		if(!Pattern.matches(regex, number)) {
			System.out.println("잘못된 번호입니다.");
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
		list.set(index, new Contact(number, name));
		System.out.println("연락처를 수정했습니다.");
	}
	private void delete() {
		scan.nextLine();
		System.out.print("검색(엔터 : 전체 검색) : ");
		String word = scan.nextLine();
		int count = 0;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().contains(word) || list.get(i).getNumber().contains(word)) {
				System.out.println((i+1)+". "+list.get(i).toString());
				count++;
			}
		}
		if(count == 0) {
			System.out.println("일치하는 연락처가 없습니다.");
			return;
		}
		System.out.print("번호 선택 : ");
		int index = scan.nextInt() - 1;
		if(index < 0 || index >= list.size() || !list.get(index).getName().contains(word)) {
			System.out.println("잘못된 번호입니다.");
			return;
		}
		list.remove(index);
		System.out.println("연락처를 삭제했습니다.");
	}
	private void search() {
		scan.nextLine();
		System.out.print("검색(엔터 : 전체 검색) : ");
		String word = scan.nextLine();
		int count = 0;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().contains(word) || list.get(i).getNumber().contains(word)) {
				System.out.println((i+1)+". "+list.get(i).toString());
				count++;
			}
		}
		if(count == 0) {
			System.out.println("일치하는 연락처가 없습니다.");
			return;
		}
	}
	@Override
	public void run() {
		String fileName = "src/day17/contact/contact.txt";
		load(fileName);
		//list = new ArrayList<Contact>();
		int menu = 0;
		do{
			printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			}catch(InputMismatchException e){
				System.out.println("올바른 타입을 입력하세요.");
				scan.nextLine();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}while(menu != EXIT);
		save(fileName);
	}
	@Override
	public void save(String filename) {
		try(FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(list);
			} catch (Exception e) {
				System.out.println("저장에 실패했습니다.");
			}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void load(String filename) {
		try(FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis)){
			list = (List<Contact>)ois.readObject();
		} catch (Exception e) {
			System.out.println("불러오기에 실패했습니다.");
		}
	}
}