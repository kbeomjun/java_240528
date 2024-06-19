package day16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import lombok.AllArgsConstructor;
import lombok.Data;

public class FunctionalInterfaceEx01 {

	public static void main(String[] args) {
		/* 함수형 인터페이스 종류 
		 * Consumer : 매개변수 O, 리턴 X, 주로 출력
		 * Supplier : 매개변수 X, 리턴 O
		 * Function : 매개변수 O, 리턴 O, 매개변수와 리턴타입이 다름
		 * Operator : 매개변수 O, 리턴 O, 매개변수와 리턴타입이 같음
		 * Predicate : 매개변수 O, 리턴 boolean */
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1, 2, 1, "둘리", 60, 60, 70));
		list.add(new Student(1, 1, 1, "홍길동", 100, 20, 30));
		list.add(new Student(1, 1, 2, "고길동", 100, 100, 100));
		
		/* 국어, 영어, 수학 평균을 계산해서 출력하는 코드 */
		double avgKor = average(list, (f)->(double)f.getKor());
		System.out.println("국어 평균 : "+avgKor);
		double avgEng = average(list, (f)->(double)f.getEng());
		System.out.println("영어 평균 : "+avgEng);
		double avgMath = average(list, (f)->(double)f.getMath());
		System.out.println("수학 평균 : "+avgMath);
		System.out.println("-------------------------------------------------------");
		
		/* 각 학생의 국어, 영어, 수학 성적을 출력하는 코드 */
		print(list, (c)->System.out.println(c.toString()));
		System.out.println("-------------------------------------------------------");
		
		/* 국어 성적이 80점 이상인 학생을 출력하는 코드 */
		over80(list, (p)-> p.getKor() >= 80);
		System.out.println();
		System.out.println("-------------------------------------------------------");
		
		/* 1학년 1반 학생들을 출력하는 코드 */
		printStudent(list, (p)-> p.getGrade() == 1 && p.getClassNum() == 1);
		System.out.println("-------------------------------------------------------");
		
		/* 1학년 1반 1번 학생을 출력하는 코드 */
		printStudent(list, (p)-> p.getGrade() == 1 && p.getClassNum() == 1 && p.getNum() == 1);
		System.out.println("-------------------------------------------------------");
		
		/* 리스트 정렬 */
		// 학년 반 번호 오름차순
		Collections.sort(list, (o1, o2) -> {
			if(o1.getGrade() != o2.getGrade())
				return o1.getGrade() - o2.getGrade();
			if(o1.getClassNum() != o2.getClassNum()) 
				return o1.getClassNum() - o2.getClassNum();
			return o1.getNum() - o2.getNum();
		});
		printStudent(list, p->true);
		System.out.println("-------------------------------------------------------");
		// 영어 성적 내림차순
		Collections.sort(list, (o1, o2) -> o2.getEng() - o1.getEng());
		printStudent(list, p->true);
	}
	
	public static Double average(List<Student> list, Function<Student, Double> f) {
		int sum = 0;
		for(Student tmp : list) {
			sum += f.apply(tmp);
		}
		double avg = (double)sum / list.size();
		return avg;
	}
	
	public static void print(List<Student> list, Consumer<Student> c) {
		for(Student tmp : list) {
			c.accept(tmp);
		}
	}
	
	public static void over80(List<Student> list, Predicate<Student> p) {
		System.out.print("80점 이상 : ");
		for(Student tmp : list) {
			if(p.test(tmp)) {
				System.out.print(tmp.getName()+" ");
			}
		}
	}
	
	public static void printStudent(List<Student> list, Predicate<Student> p) {
		for(Student tmp : list) {
			if(p.test(tmp)) {
				System.out.println(tmp.toString());
			}
		}
	}
}

@Data
@AllArgsConstructor
class Student{
	private int grade, classNum, num;
	private String name;
	private int kor, eng, math;
	@Override
	public String toString() {
		return grade+"학년 "+classNum+"반 "+num+"번 "+name+"\n"
				+"국어 : "+kor+", 영어 : "+eng+", 수학 : "+math;
	}
}