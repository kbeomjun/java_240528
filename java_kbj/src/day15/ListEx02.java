package day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ListEx02 {

	public static void main(String[] args) {
		/* 연락처 클래스를 이용한 List 예제
		 * indexOf, remove, contains */
		ArrayList<Contact> list = new ArrayList<Contact>();
		Contact c1 = new Contact("홍길동", "010-1234-5678");
		Contact c2 = new Contact("홍길동", "010-1234-5678");
		list.add(c1);
		System.out.println(list);
		
		// equals 오버라이드에 의해 삭제
		list.remove(c2);
		System.out.println(list);
		
		// 주소를 저장하기 때문에 list 도 같이 바뀜
		list.add(c1);
		System.out.println(list);
		c1.name = "고길동";
		System.out.println(list);
		
		// 복사 생성자를 이용해 객체를 리스트에 저장하고 객체를 밖에서 수정해도 리스트는 영향이 없음
		list.add(new Contact(c2));
		System.out.println(list);
		c2.name = "나길동";
		System.out.println(list);
		
		/* 리스트를 이용하여 정렬하는 방법 */
		List<String> list1 = new ArrayList<String>();
		list1.add("홍길동");
		list1.add("고길동");
		list1.add("둘리");
		System.out.println(list1);
		Collections.sort(list1);
		System.out.println(list1);
		/* Collections.sort(리스트, Comparator 객체)
		 * Comparator 객체 : Comparator 인터페이스를 구현한 구현 클래스의 객체가 필요
		 *  */
		Collections.sort(list1, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return -o1.compareTo(o2); // 내림차순
			}
		});
		System.out.println(list1);
		
		/* 클래스 객체를 갖는 리스트 정렬 방법 */
		List<Student> stds = new ArrayList<Student>();
		stds.add(new Student("2024160001", "홍길동"));
		stds.add(new Student("2024160002", "고길동"));
		stds.add(new Student("2024135001", "둘리"));
		System.out.println(stds);
		Collections.sort(stds, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.number.compareTo(o2.number);
			}
		});
		System.out.println(stds);
	}
}

class Contact{
	public String name, number;
	public Contact(String name, String number) {
		this.name = name;
		this.number = number;
	}
	public Contact(Contact c) {
		this.name = c.name;
		this.number = c.number;
	}
	@Override
	public String toString() {
		return name + " : " + number;
	}
	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(number, other.number);
	}
}

class Student {
	public String number, name;
	public Student(String number, String name) {
		this.number = number;
		this.name = name;
	}
	@Override
	public String toString() {
		return "학번 : "+number+" - 이름 : "+name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(number, other.number);
	}
}