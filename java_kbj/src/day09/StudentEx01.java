package day09;

import java.util.Scanner;

public class StudentEx01 {
	
	/* 학생 성적을 관리하기 위한 프로그램 : 국어, 영어, 수학 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	 	
		int menu = 0, studentCount = 0;
		Student [] list = new Student[3];
		
		do {
			printMenu();
			menu = scan.nextInt();
			switch(menu) {
			case 1:
				studentCount = insertStudent(list, scan, studentCount);
				break;
			case 2:
				updateStudent(list, scan, studentCount);
				break;
			case 3:
				printStudent(list, scan, studentCount);
				break;
			case 4:
				System.out.println("프로그램 종료입니다.");
				break;
			default:
				System.out.println("잘못된 메뉴입니다.");
			}
		}while(menu != 4);
	}
	
	public static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 학생 등록");
		System.out.println("2. 성적 수정");
		System.out.println("3. 성적 확인");
		System.out.println("4. 종료");
		System.out.print("메뉴 선택 : ");
	}
	
	public static int indexOf(Student[] list, int studentCount, Student std) {
		if(list == null || std == null) {
			return -1;
		}
		for(int i = 0; i < studentCount; i++) {
			if(std.getGrade() != list[i].getGrade()) {
				continue;
			}
			if(std.getClassNum() != list[i].getClassNum()) {
				continue;
			}
			if(std.getNum() != list[i].getNum()) {
				continue;
			}
			return i;
		}
		return -1;
	}
	
	public static Student inputStudent(Scanner scan) {
		System.out.print("학년 반 번호 : ");
		int grade = scan.nextInt();
		int classNum = scan.nextInt();
		int num = scan.nextInt();
		return new Student(grade, classNum, num, "");
	}
	
	public static int insertStudent(Student[] list, Scanner scan, int studentCount) {
		if(studentCount == list.length) {
			System.out.println("다 찼습니다.");
			return studentCount;
		}
		Student tmp = inputStudent(scan);
		String name = scan.next();
		tmp.setName(name);
		int index = indexOf(list, studentCount, tmp);
		if(index != -1) {
			System.out.println("이미 등록된 학생입니다.");
			return studentCount;
		}
		list[studentCount++] = tmp;
		return studentCount;
	}
	
	public static void updateStudent(Student[] list, Scanner scan, int studentCount) {
		Student tmp = inputStudent(scan);
		int index = indexOf(list, studentCount, tmp);
		if(index == -1) {
			System.out.println("일치하는 학생이 없습니다.");
			return;
		}
		System.out.print("국어 영어 수학 성적 : ");
		int kor = scan.nextInt();
		int eng = scan.nextInt();
		int math = scan.nextInt();
		list[index].updateScore(kor, eng, math);
	}
	
	public static void printStudent(Student[] list, Scanner scan, int studentCount) {
		Student tmp = inputStudent(scan);
		int index = indexOf(list, studentCount, tmp);
		if(index == -1) {
			System.out.println("일치하는 학생이 없습니다.");
			return;
		}
		list[index].print();
	}

}

class Student{
	private int grade, classNum, num;
	private String name;
	private int kor, eng, math;
	
	public Student() {}
	
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}
	
	public void updateScore(int kor, int eng, int math) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public void print() {
		System.out.println(grade+"학년 "+classNum+"반 "+num+"번 "+name);
		System.out.println("국어 : "+kor+"점, 영어 : "+eng+"점, 수학 : "+math+"점");
	}
	
	public int getGrade() {return grade;}
	public void setGrade(int grade) {this.grade = grade;}
	public int getClassNum() {return classNum;}
	public void setClassNum(int classNum) {this.classNum = classNum;}
	public int getNum() {return num;}
	public void setNum(int num) {this.num = num;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getKor() {return kor;}
	public void setKor(int kor) {this.kor = kor;}
	public int getEng() {return eng;}
	public void setEng(int eng) {this.eng = eng;}
	public int getMath() {return math;}
	public void setMath(int math) {this.math = math;}
	
}
