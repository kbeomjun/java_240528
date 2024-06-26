package day18.homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import program.Program;

public class Manager implements Program {
	private List<Member> mlist = new ArrayList<Member>();
	private Scanner scan = new Scanner(System.in);
	private final int MEMBER = 1, SCHEDULE = 2, EXIT = 3;
	@Override
	public void printMenu() {
		System.out.println("메뉴\n"
				+ "1. 회원 관리\n"
				+ "2. 일정 관리\n"
				+ "3. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
	}
	@Override
	public void runMenu(int menu) throws Exception {
		switch(menu) {
		case MEMBER:
			member();
			break;
		case SCHEDULE:
			System.out.print("아이디 : ");
			String id = scan.next();
			int index = -1;
			for(int i = 0; i < mlist.size(); i++) {
				if(mlist.get(i).getId().equals(id)) {
					index = i;
				}
			}
			if(index == -1) {
				System.out.println("없는 아이디입니다.");
				return;
			}
			schedule(mlist.get(index));
			break;
		case EXIT:
			System.out.println("---------------------------------");
			System.out.println("프로그램을 종료합니다.");
			System.out.println("---------------------------------");
			break;
		default:
			System.out.println("---------------------------------");
			System.out.println("잘못된 메뉴입니다.");
			System.out.println("---------------------------------");
		}
	}
	private void member() {
		int menu = 0;
		do {
			System.out.println("메뉴\n"
					+ "1. 회원 추가\n"
					+ "2. 회원 수정\n"
					+ "3. 회원 삭제\n"
					+ "4. 이전으로");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			switch(menu) {
			case 1:
				System.out.println("추가할 회원 정보 입력");
				System.out.print("아이디 : ");
				String id = scan.next();
				for(int i = 0; i < mlist.size(); i++) {
					if(mlist.get(i).getId().equals(id)) {
						System.out.println("중복된 아이디입니다.");
						return;
					}
				}
				System.out.print("이름 : ");
				String name = scan.next();
				System.out.println("---------------------------------");
				mlist.add(new Member(id, name, new ArrayList<Schedule>()));
				System.out.println("회원이 추가됐습니다.");
				System.out.println("---------------------------------");
				break;
			case 2:
				System.out.print("아이디 : ");
				id = scan.next();
				System.out.println("---------------------------------");
				int index = -1;
				for(int i = 0; i < mlist.size(); i++) {
					if(mlist.get(i).getId().equals(id)) {
						index = i;
					}
				}
				if(index == -1) {
					System.out.println("등록된 아이디가 없습니다.");
					return;
				}
				System.out.print("수정할 이름 : ");
				name = scan.next();
				System.out.println("---------------------------------");
				mlist.get(index).setName(name);
				System.out.println("회원 정보를 수정했습니다.");
				System.out.println("---------------------------------");
				break;
			case 3:
				System.out.print("아이디 : ");
				id = scan.next();
				System.out.println("---------------------------------");
				index = -1;
				for(int i = 0; i < mlist.size(); i++) {
					if(mlist.get(i).getId().equals(id)) {
						index = i;
					}
				}
				if(index == -1) {
					System.out.println("등록된 아이디가 없습니다.");
					return;
				}
				mlist.remove(index);
				System.out.println("회원 정보를 삭제했습니다.");
				System.out.println("---------------------------------");
				break;
			case 4:
				break;
			default:
				System.out.println("---------------------------------");
				System.out.println("잘못된 메뉴입니다.");
				System.out.println("---------------------------------");
			}
		}while(menu != 4);
	}
	private void schedule(Member m) {
		List<Schedule> slist = m.getSlist();
		int menu = 0;
		do {
			System.out.println("메뉴\n"
					+ "1. 일정 추가\n"
					+ "2. 일정 수정\n"
					+ "3. 일정 삭제\n"
					+ "4. 일정 확인\n"
					+ "5. 이전으로");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			switch(menu) {
			case 1:
				System.out.println("---------------------------------");
				System.out.print("날짜(yyyy-MM-dd hh:mm) : ");
				String date = scan.next();
				String hour = scan.next();
				System.out.print("일정 : ");
				String schedule = scan.next();
				System.out.print("상세 : ");
				String detail = scan.next();
				System.out.println("---------------------------------");
				String regexDate = "^[2][0][2][4-9]-[01]\\d-[0-3]\\d$";
				String regexHour = "^[0-2]\\d:[0-5]\\d$";
				if(!Pattern.matches(regexDate, date)) {
					System.out.println("잘못된 날짜입니다.");
					return;
				}
				if(!Pattern.matches(regexHour, hour)) {
					System.out.println("잘못된 시간입니다.");
					return;
				}
				for(int i = 0; i < slist.size(); i++) {
					if(slist.get(i).getDate().equals(date) && slist.get(i).getHour().equals(hour) && slist.get(i).getSchedule().equals(schedule)) {
						System.out.println("이미 등록된 일정입니다.");
						return;
					}
				}
				slist.add(new Schedule(date, hour, schedule, detail));
				Collections.sort(slist);
				System.out.println("일정을 추가했습니다.");
				System.out.println("---------------------------------");
				break;
			case 2:
				if(slist.size() == 0) {
					System.out.println("등록된 일정이 없습니다.");
					return;
				}
				System.out.print("날짜(yyyy-MM-dd) : ");
				date = scan.next();
				regexDate = "^[2][0][2][4-9]-[01]\\d-[0-3]\\d$";
				if(!Pattern.matches(regexDate, date)) {
					System.out.println("잘못된 날짜입니다.");
					return;
				}
				System.out.println("---------------------------------");
				System.out.println(date+" 일정 리스트");
				System.out.println("---------------------------------");
				List<Schedule> tmps = new ArrayList<Schedule>();
				for(int i = 0; i < slist.size(); i++) {
					if(slist.get(i).getDate().equals(date)) {
						tmps.add(slist.get(i));
					}
				}
				int count = 0;
				for(int i = 0; i < tmps.size(); i++) {
					System.out.println((i+1)+". "+tmps.get(i).toString());
					count++;
				}
				if(count == 0) {
					System.out.println("등록된 일정이 없습니다.");
					return;
				}
				System.out.println("---------------------------------");
				System.out.print("수정할 일정 선택 : ");
				int tmpIndex = scan.nextInt() - 1, index = -1;
				for(int i = 0; i < slist.size(); i++) {
					if(slist.get(i).equals(tmps.get(tmpIndex))) {
						index = i;
					}
				}
				System.out.print("날짜(yyyy-MM-dd hh:mm) : ");
				date = scan.next();
				hour = scan.next();
				System.out.print("일정 : ");
				schedule = scan.next();
				System.out.print("상세 : ");
				detail = scan.next();
				System.out.println("---------------------------------");
				if(!Pattern.matches(regexDate, date)) {
					System.out.println("잘못된 날짜입니다.");
					return;
				}
				regexHour = "^[0-2]\\d:[0-5]\\d$";
				if(!Pattern.matches(regexHour, hour)) {
					System.out.println("잘못된 시간입니다.");
					return;
				}
				for(int i = 0; i < slist.size(); i++) {
					if(slist.get(i).getDate().equals(date) && slist.get(i).getHour().equals(hour) && slist.get(i).getSchedule().equals(schedule)) {
						System.out.println("이미 등록된 일정입니다.");
						return;
					}
				}
				slist.set(index, new Schedule(date, hour, schedule, detail));
				Collections.sort(slist);
				System.out.println("일정을 수정했습니다.");
				System.out.println("---------------------------------");
				break;
			case 3:
				if(slist.size() == 0) {
					System.out.println("등록된 일정이 없습니다.");
					return;
				}
				System.out.print("날짜(yyyy-MM-dd) : ");
				date = scan.next();
				regexDate = "^[2][0][2][4-9]-[01]\\d-[0-3]\\d$";
				if(!Pattern.matches(regexDate, date)) {
					System.out.println("잘못된 날짜입니다.");
					return;
				}
				System.out.println("---------------------------------");
				System.out.println(date+" 일정 리스트");
				System.out.println("---------------------------------");
				tmps = new ArrayList<Schedule>();
				for(int i = 0; i < slist.size(); i++) {
					if(slist.get(i).getDate().equals(date)) {
						tmps.add(slist.get(i));
					}
				}
				count = 0;
				for(int i = 0; i < tmps.size(); i++) {
					System.out.println((i+1)+". "+tmps.get(i).toString());
					count++;
				}
				if(count == 0) {
					System.out.println("등록된 일정이 없습니다.");
					return;
				}
				System.out.println("---------------------------------");
				System.out.print("삭제할 일정 선택 : ");
				tmpIndex = scan.nextInt() - 1; index = -1;
				for(int i = 0; i < slist.size(); i++) {
					if(slist.get(i).equals(tmps.get(tmpIndex))) {
						index = i;
					}
				}
				System.out.println("---------------------------------");
				slist.remove(index);
				System.out.println("일정을 삭제했습니다.");
				System.out.println("---------------------------------");
				break;
			case 4:
				if(slist.size() == 0) {
					System.out.println("등록된 일정이 없습니다.");
					return;
				}
				System.out.print("날짜(yyyy-MM-dd) : ");
				date = scan.next();
				regexDate = "^[2][0][2][4-9]-[01]\\d-[0-3]\\d$";
				if(!Pattern.matches(regexDate, date)) {
					System.out.println("잘못된 날짜입니다.");
					return;
				}
				System.out.println("---------------------------------");
				System.out.println(date+" 일정 리스트");
				System.out.println("---------------------------------");
				tmps = new ArrayList<Schedule>();
				for(int i = 0; i < slist.size(); i++) {
					if(slist.get(i).getDate().equals(date)) {
						tmps.add(slist.get(i));
					}
				}
				count = 0;
				for(int i = 0; i < tmps.size(); i++) {
					System.out.println((i+1)+". "+tmps.get(i).toString());
					count++;
				}
				if(count == 0) {
					System.out.println("등록된 일정이 없습니다.");
					return;
				}
				System.out.println("---------------------------------");
				System.out.print("메뉴로 가시려면 엔터를 입력하세요.");
				scan.nextLine();
				String enter = scan.nextLine();
				if(enter.equals("\n")) {
					System.out.println("---------------------------------");
					return;
				}
				break;
			case 5:
				break;
			default:
				System.out.println("---------------------------------");
				System.out.println("잘못된 메뉴입니다.");
				System.out.println("---------------------------------");
			}
		}while(menu != 5);
	}
	@Override
	public void run() {
		String filename = "src/day18/homework/manager.txt";
		load(filename);
		//list = new ArrayList<Member>();
		int menu = 0;
		do {
			printMenu();
			menu = scan.nextInt();
			try {
				runMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}while(menu != EXIT);
		save(filename);
	}
	@Override
	public void save(String filename) {
		try(FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(mlist);
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
			mlist = (List<Member>)ois.readObject();
		} catch (IOException e) {
			System.out.println("입출력 예외 발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("클래스를 찾을수 없습니다.");
			e.printStackTrace();
		} 
	}
}