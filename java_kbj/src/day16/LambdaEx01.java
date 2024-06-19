package day16;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LambdaEx01 {

	public static void main(String[] args) {
		/* 람다식 사용법
		 * 1. 추상 메서드가 1개인 인터페이스를 생성
		 * 2. main 메서드가 있는 클래스에서 1에서 생성한 인터페이스를 구현한 익명 클래스 객체 생성
		 * 3. 2에서 생성한 객체를 이용하여 기능 호출 */
		
		// 원래 방식
		A a1 = new A() {
			@Override
			public int sum(int n1, int n2) {
				return n1 + n2;
			}
		};
		
		// 람다식 사용
		A a2 = (n1, n2) ->{
			return n1 + n2;
		};
		
		// 구현부가 한 줄인 경우 {}를 생략 가능하고, {}를 생략하면 return 도 생략해야 함
		A a3 = (n1, n2) -> n1+n2;
		System.out.println(a3.sum(1, 2));
		
		// 매개변수가 1개이면 () 생략 가능
		B b1 = n1 -> System.out.println(n1);
		b1.print(10);
		
		/* 함수형 인터페이스 종류 
		 * Consumer : 매개변수 O, 리턴 X, 주로 출력
		 * Supplier : 매개변수 X, 리턴 O
		 * Function : 매개변수 O, 리턴 O, 매개변수와 리턴타입이 다름
		 * Operator : 매개변수 O, 리턴 O, 매개변수와 리턴타입이 같음
		 * Predicate : 매개변수 O, 리턴 boolean */
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("홍길동", "1", 21));
		list.add(new Person("고길동", "2", 40));
		list.add(new Person("둘리", "3", 10000));
		
		print(list, (c)->{
			System.out.println(c.getName());
		});
		print(list, (c)->System.out.println(c.getAge()));
		print(list, c->System.out.println(c));
		
		int r = randomNumber(()->{
			int min = 0, max = 10000;
			return (int)(Math.random() * (max - min + 1) + min);
		});
		System.out.println(r);
		
		printString(list, (f)->f.getName());
		
		increaseAge(list, (op)->{
			op.setAge(op.getAge() + 1);
			return op;
		});
		System.out.println(list);
		
		print2(list, p->p.getAge()>30);
	}
	
	public static void print(List<Person> list, Consumer<Person> c) {
		for(Person tmp : list) {
			c.accept(tmp);
		}
	}
	public static Integer randomNumber(Supplier<Integer> s) {
		return s.get();
	}
	public static void printString(List<Person> list, Function<Person, String> f) {
		for(Person tmp : list) {
			System.out.println(f.apply(tmp));
		}
	}
	public static void increaseAge(List<Person> list, UnaryOperator<Person> op) {
		for(int i = 0; i < list.size(); i++) {
			list.set(i, op.apply(list.get(i)));
		}
	}
	public static void print2(List<Person> list, Predicate<Person> p) {
		for(Person tmp : list) {
			if(p.test(tmp)) {
				System.out.println(tmp);
			}
		}
	}
}

interface A{ 
	int sum(int num1, int num2);
}
interface B{
	void print(int num1);
}
@FunctionalInterface // 이 어노테이션은 인터페이스가 함수형 인터페이스인지 판별해줌, 추상 메서드가 1개가 아니면 에러 표시
interface C{
	void test();
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person{
	private String name;
	private String num;
	private int age;
	@Override
	public String toString() {
		return num+" "+name+"("+age+")";
	}
}