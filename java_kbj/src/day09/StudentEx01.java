package day09;

import java.util.Scanner;

public class StudentEx01 {
	
	/* 학생 성적을 관리하기 위한 프로그램 : 국어, 영어, 수학 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int menu = 0, grade = 0, classNum = 0, num = 0, studentCount = 0, mismatch = 0;
		String name = null;
		int kor = 0, eng = 0, math = 0;
		Student [] list = new Student[3];
		
		do {
			printMenu();
			menu = scan.nextInt();
			//runMenu(menu);
			switch(menu) {
			case 1:
				if(studentCount == list.length) {
					System.out.println("다 찼습니다.");
					break;
				}
				System.out.print("학년 반 번호 이름 : ");
				grade = scan.nextInt();
				classNum = scan.nextInt();
				num = scan.nextInt();
				name = scan.next();
				list[studentCount++] = new Student(grade, classNum, num, name);
				break;
			case 2:
				System.out.print("학년 반 번호 : ");
				grade = scan.nextInt();
				classNum = scan.nextInt();
				num = scan.nextInt();
				mismatch = 0;
				for(int i = 0; i < list.length; i++) {
					if(list[i].getGrade() == grade && list[i].getClassNum() == classNum && list[i].getNum() == num) {
						System.out.print("국영수 성적 입력 : ");
						kor = scan.nextInt();
						eng = scan.nextInt();
						math = scan.nextInt();
						list[i].updateScore(kor, eng, math);
					}
					else {
						mismatch++;
					}
				}
				if(mismatch == list.length) {
					System.out.println("일치하는 학생이 없습니다.");
				}
				break;
			case 3:
				System.out.print("학년 반 번호 : ");
				grade = scan.nextInt();
				classNum = scan.nextInt();
				num = scan.nextInt();
				mismatch = 0;
				for(int i = 0; i < list.length; i++) {
					if(list[i].getGrade() == grade && list[i].getClassNum() == classNum && list[i].getNum() == num) {
						list[i].print();
						list[i].printScore();
					}
					else {
						mismatch++;
					}
				}
				if(mismatch == list.length) {
					System.out.println("일치하는 학생이 없습니다.");
				}
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
	
	public static void runMenu(int menu) {
		switch(menu) {
		case 1:
			System.out.println("학생 등록입니다.");
			break;
		case 2:
			System.out.println("성적 수정입니다.");
			break;
		case 3:
			System.out.println("성적 확인입니다.");
			break;
		case 4:
			System.out.println("프로그램 종료입니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
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
	}
	
	public void printScore() {
		System.out.println("국어 : "+kor+", 영어 : "+eng+", 수학 : "+math);
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
