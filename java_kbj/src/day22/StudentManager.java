package day22;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import program.Program;

public class StudentManager implements Program {
	private Scanner scan = new Scanner(System.in);
	private List<Student> list = new ArrayList<Student>();

	public void printStudentMenu() {
		System.out.print("학생 관리 메뉴\n"
				+ "1. 학생 추가\n"
				+ "2. 학생 수정\n"
				+ "3. 학생 삭제\n"
				+ "4. 학생 조회\n"
				+ "5. 이전으로\n"
				+ "메뉴 선택 : ");
	}
	public void runStudentMenu(int menu) {
		switch (menu) {
		case 1:
			studentInsert();
			break;
		case 2:
			studentUpdate();
			break;
		case 3:
			studentDelete();
			break;
		case 4:
			studentSearch();
			break;
		case 5:
			System.out.println("이전으로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	private void studentInsert() {
		Student std = inputStudentExpand();
		if(list.contains(std)) {
			System.out.println("이미 등록된 학년, 반, 번호입니다.");
			return;
		}
		list.add(std);
		System.out.println("학생이 추가되었습니다.");
		System.out.println(list);
	}
	private Student inputStudent() {
		System.out.print("학년 : ");
		int grade = scan.nextInt();
		System.out.print("반 : ");
		int classNum = scan.nextInt();
		System.out.print("번호 : ");
		int num = scan.nextInt();
		return new Student(grade, classNum, num, ""); 
	}
	private Student inputStudentExpand() {
		Student std = inputStudent();
		System.out.print("이름 : ");
		scan.nextLine();
		String name = scan.nextLine();
		std.setName(name);
		return std;
	}
	private void studentUpdate() {
		int menu = 0;
		do {
			printStudentUpdateMenu();
			menu = scan.nextInt();
			runStudentUpdateMenu(menu);
		}while(menu != 5);
	}
	private void printStudentUpdateMenu() {
		System.out.print("학생 수정 메뉴\n"
				+ "1. 학생 정보 수정\n"
				+ "2. 성적 추가\n"
				+ "3. 성적 수정\n"
				+ "4. 성적 삭제\n"
				+ "5. 이전으로\n"
				+ "메뉴 선택 : ");
	}
	private void runStudentUpdateMenu(int menu) {
		switch(menu) {
		case 1:
			Student std = inputStudent();
			int index = -1;
			index = list.indexOf(std);
			if(index == -1) {
				System.out.println("일치하는 학생이 없습니다.");
				return;
			}
			std = inputStudent();
			std = inputStudentExpand();
			if(list.contains(std)) {
				System.out.println("이미 등록된 학생입니다.");
				return;
			}
			list.set(index, std);
			System.out.println("학생 정보를 수정하였습니다.");
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		default:
		}
	}
	private void studentDelete() {
		Student std = inputStudent();
		if(list.remove(std)) {
			System.out.println("학생을 삭제했습니다.");
			return;
		}
		System.out.println("일치하는 학생이 없습니다.");
	}
	private void studentSearch() {
		Student std = inputStudent();
		int index = -1;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(std)) {
				index = i;
				break;
			}
		}
		if(index == -1) {
			System.out.println("일치하는 학생이 없습니다.");
			return;
		}
		list.get(index).print();
	}
	@Override
	public void printMenu() {
		System.out.println("1. 학생 관리\n"
				+ "2. 과목 관리\n"
				+ "3. 프로그램 종료\n"
				+ "메뉴 선택 : ");
	}
	@Override
	public void runMenu(int menu) throws Exception {
		switch(menu) {
		case 1:
			int menu1 = 0;
			do {
				printStudentMenu();
				menu1 = scan.nextInt();
				runStudentMenu(menu1);
			}while(menu1 != 5);
			break;
		case 2:
			int menu2 = 0;
			do {
				printSubjectMenu();
				menu2 = scan.nextInt();
				runSubjectMenu(menu2);
			}while(menu2 != 5);
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴");
		}
	}
	private void printSubjectMenu() {
		System.out.print("과목 관리 메뉴\n"
				+ "1. 과목 추가\n"
				+ "2. 과목 수정\n"
				+ "3. 과목 삭제\n"
				+ "4. 과목 확인\n"
				+ "5. 이전으로\n"
				+ "메뉴 선택 : ");
	}
	private void runSubjectMenu(int menu2) {
		switch(menu2) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴");
		}
	}
	@Override
	public void run() {
		int menu = 0;
		do {
			printMenu();
			menu = scan.nextInt();
			try {
				runMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}while(menu != 5);
	}
	@Override
	public void save(String fileName) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(oos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void load(String fileName) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			try {
				list = (List<Student>) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}