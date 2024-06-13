package day12;

import java.util.Objects;

public class ObjectEx01 {
	/* Object 클래스 : 모든 클래스의 부모 클래스, 당연히 메서드 override 가 가능함 
	 * toString() - 객체 정보를 문자열로 반환
	 * equals() - 두 객체가 같은지 알려줌, 기본은 주소를 비교
	 * hashCode() - 정보를 저장하거나 검색할 때 사용하는 자료 구조
	 * clone() - 객체를 복사하는 메서드, override 하기 위해서는 Cloneable 인터페이스를 구현한 구현 클래스이어야 함
	 * */
	public static void main(String[] args) throws CloneNotSupportedException {
		Student std1 = new Student(1, 1, 1, "홍길동");
		Student std2 = new Student(1, 1, 1, "홍길동");
		Student std3 = new Student(1, 1, 1, "임꺽정");
		Student std4 = new Student(1, 1, 2, "홍길동");
		// toString 예제
		System.out.println(std1);
		System.out.println(std1.toString());
		// hashCode, equals 예제
		// == 를 이용하여 비교
		System.out.println("---------------------------------");
		System.out.println("std1 == std2 : "+(std1 == std2));
		System.out.println("std1 == std3 : "+(std1 == std3));
		System.out.println("std1 == std4 : "+(std1 == std4));
		// equals 를 이용하여 비교
		System.out.println("---------------------------------");
		System.out.println("std1.equals(std2) : "+(std1.equals(std2)));
		System.out.println("std1.equals(std3) : "+(std1.equals(std3)));
		System.out.println("std1.equals(std4) : "+(std1.equals(std4)));
		// clone 예제
		Student std5 = (Student)std1.clone(); 
		System.out.println("std1 : "+std1);
		System.out.println("std5 : "+std5);
		System.out.println("std1 == std5 : "+(std1 == std5));
		System.out.println("std1.equals(std5) : "+(std1.equals(std5)));
	}
}

class Student implements Cloneable{
	int grade, classNum, num;
	String name;
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [grade=" + grade + ", classNum=" + classNum + ", num=" + num + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(classNum, grade, num);
	}
	@Override
	public boolean equals(Object obj) { // name 은 넣지 않아서 비교하지 않음
		// 주소가 같은 경우 => 같은 객체를 공유
		if (this == obj) 
			return true;
		// 비교대상이 null 인 경우 => 비교할 수가 없음
		if (obj == null) 
			return false;
		// 내 클래스와 매개변수의 클래스가 다른 경우
		if (getClass() != obj.getClass()) 
			return false;
		// 내 클래스와 매개변수의 클래스가 같은 경우
		Student other = (Student) obj;  // 다운캐스팅
		return classNum == other.classNum && grade == other.grade && num == other.num;
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone(); // return 이 Object 이므로 다운캐스팅을 해줘야함
	}
}