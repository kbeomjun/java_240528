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

public class ScheduleManager implements Program {
	private List<Schedule> list = new ArrayList<Schedule>();
	private Scanner scan = new Scanner(System.in);
	private final int INSERT = 1, UPDATE = 2, DELETE = 3, CHECK = 4, EXIT = 5;
	@Override
	public void printMenu() {
		System.out.println("메뉴\n"
				+ "1. 일정 추가\n"
				+ "2. 일정 수정\n"
				+ "3. 일정 삭제\n"
				+ "4. 일정 확인\n"
				+ "5. 이전으로");
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
		case CHECK:
			check();
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
		String regexHour = "^[12]\\d:[0-5]\\d$";
		if(!Pattern.matches(regexDate, date)) {
			System.out.println("잘못된 날짜입니다.");
			return;
		}
		if(!Pattern.matches(regexHour, hour)) {
			System.out.println("잘못된 시간입니다.");
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getDate().equals(date) && list.get(i).getHour().equals(hour) && list.get(i).getSchedule().equals(schedule)) {
				System.out.println("이미 등록된 일정입니다.");
				return;
			}
		}
		list.add(new Schedule(date, hour, schedule, detail));
		Collections.sort(list);
		System.out.println("일정을 추가했습니다.");
		System.out.println("---------------------------------");
	}
	private void update() {
		if(list.size() == 0) {
			System.out.println("등록된 일정이 없습니다.");
		}
		System.out.print("날짜(yyyy-MM-dd) : ");
		String date = scan.next();
		String regexDate = "^[2][0][2][4-9]-[01]\\d-[0-3]\\d$";
		if(!Pattern.matches(regexDate, date)) {
			System.out.println("잘못된 날짜입니다.");
			return;
		}
		System.out.println("---------------------------------");
		System.out.println(date+" 일정 리스트");
		System.out.println("---------------------------------");
		List<Schedule> tmps = new ArrayList<Schedule>();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getDate().equals(date)) {
				tmps.add(list.get(i));
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
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(tmps.get(tmpIndex))) {
				index = i;
			}
		}
		System.out.print("날짜(yyyy-MM-dd hh:mm) : ");
		date = scan.next();
		String hour = scan.next();
		System.out.print("일정 : ");
		String schedule = scan.next();
		System.out.print("상세 : ");
		String detail = scan.next();
		System.out.println("---------------------------------");
		String regexHour = "^[12]\\d:[0-5]\\d$";
		if(!Pattern.matches(regexDate, date)) {
			System.out.println("잘못된 날짜입니다.");
			return;
		}
		if(!Pattern.matches(regexHour, hour)) {
			System.out.println("잘못된 시간입니다.");
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getDate().equals(date) && list.get(i).getHour().equals(hour) && list.get(i).getSchedule().equals(schedule)) {
				System.out.println("이미 등록된 일정입니다.");
				return;
			}
		}
		list.set(index, new Schedule(date, hour, schedule, detail));
		Collections.sort(list);
		System.out.println("일정을 수정했습니다.");
		System.out.println("---------------------------------");
	}
	private void delete() {
		if(list.size() == 0) {
			System.out.println("등록된 일정이 없습니다.");
		}
		System.out.print("날짜(yyyy-MM-dd) : ");
		String date = scan.next();
		String regexDate = "^[2][0][2][4-9]-[01]\\d-[0-3]\\d$";
		if(!Pattern.matches(regexDate, date)) {
			System.out.println("잘못된 날짜입니다.");
			return;
		}
		System.out.println("---------------------------------");
		System.out.println(date+" 일정 리스트");
		System.out.println("---------------------------------");
		List<Schedule> tmps = new ArrayList<Schedule>();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getDate().equals(date)) {
				tmps.add(list.get(i));
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
		System.out.print("삭제할 일정 선택 : ");
		int tmpIndex = scan.nextInt() - 1, index = -1;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(tmps.get(tmpIndex))) {
				index = i;
			}
		}
		System.out.println("---------------------------------");
		list.remove(index);
		System.out.println("일정을 삭제했습니다.");
		System.out.println("---------------------------------");
	}
	private void check() {
		if(list.size() == 0) {
			System.out.println("등록된 일정이 없습니다.");
		}
		System.out.print("날짜(yyyy-MM-dd) : ");
		String date = scan.next();
		String regexDate = "^[2][0][2][4-9]-[01]\\d-[0-3]\\d$";
		if(!Pattern.matches(regexDate, date)) {
			System.out.println("잘못된 날짜입니다.");
			return;
		}
		System.out.println("---------------------------------");
		System.out.println(date+" 일정 리스트");
		System.out.println("---------------------------------");
		List<Schedule> tmps = new ArrayList<Schedule>();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getDate().equals(date)) {
				tmps.add(list.get(i));
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
		System.out.print("메뉴로 가시려면 엔터를 입력하세요.");
		scan.nextLine();
		String enter = scan.nextLine();
		if(enter.equals("\n")) {
			System.out.println("---------------------------------");
			return;
		}
	}
	@Override
	public void run() throws Exception {
		String filename = "src/day18/homework/schedule.txt";
		//load(filename);
		//list = new ArrayList<Schedule>();
		int menu = 0;
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu);
		}while(menu != EXIT);
		save(filename);
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
			list = (List<Schedule>)ois.readObject();
		} catch (IOException e) {
			System.out.println("입출력 예외 발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("클래스를 찾을수 없습니다.");
			e.printStackTrace();
		} 
	}
}