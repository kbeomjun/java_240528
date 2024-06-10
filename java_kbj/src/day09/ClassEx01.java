package day09;

public class ClassEx01 {

	public static void main(String[] args) {
		
		Student2 std1 = new Student2();
		Student2 std2 = new Student2(2, 2, 2, "둘리");
		std1.print();
		std2.print();
		System.out.println(Student2.schoolName); // static 변수는 클래스명으로 호출
		
		Student3 std3 = new Student3(1, 1, 1, "홍길동");
		Student3 std4 = new Student3(1, 1, 2, "임꺽정");
		std3.print();
		std4.print();
		Student3.printSchoolName(); // static 메서드
		
	}

}

/* 고등학생 성적을 관리하기 위한 학생 클래스 추상화
 * 학생 성적은 국어, 영어, 수학만 관리 */
class Student1 {
	int grade, classNum, num; 
	String name;
	int kor, eng, math;
	
	public Student1() {} // 기본 생성자, 생성자는 리턴타입이 없음

}

/* 접근제어자
 * public - 모든 클래스에서 사용 가능
 * protected - 같은 패키지에 있는 다른 클래스에서 사용 가능
 * default - 같은 패키지에 있는 다른 클래스에서 사용 가능
 * private - 다른 클래스에서 사용 불가능
 * 일반적으로 멤버변수들은 private, 메서드 || 생성자는 public
 * 클래스에 public이 붙으려면 클래스 이름과 파일명이 같아야 함 */
// static 변수(클래스 변수) - 하나의 클래스로 만든 모든 인스턴스가 공통적인 값을 가지는 속성이 있는 경우 static을 붙임
// static 메서드(클래스 메서드) - 인스턴스 생성 없이 호출 가능, 객체 변수 호출 불가능
class Student2{
	private int grade, classNum, num;
	private String name;
	public static String schoolName = "KH고등학교";
	
	public int getGrade() {return grade;}
	public void setGrade(int grade) {this.grade = grade;}
	public int getClassNum() {return classNum;}
	public void setClassNum(int classNum) {this.classNum = classNum;}
	public int getNum() {return num;}
	public void setNum(int num) {this.num = num;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public Student2(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}
	
	public Student2() {
		this(1, 1, 1, "홍길동");
	}
	
	public void print() {
		System.out.println(schoolName+" "+grade+"학년 "+classNum+"반 "+num+"번 "+name);
	}
	
}

class Student3{
	public int grade, classNum, num;
	public String name;
	public static String schoolName = "KH고등학교";
	
	public void print() {
		System.out.println(grade+"학년 "+classNum+"반 "+num+"번 "+name);
	}
	
	public static void printSchoolName() {
		System.out.println(schoolName);
	}

	public Student3(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}
	
}
