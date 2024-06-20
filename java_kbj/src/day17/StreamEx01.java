package day17;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;

public class StreamEx01 {

	public static void main(String[] args) {
		// asList(매개변수들) : 매개변수들을 리스트로 만들어줌
		List<Person> list = Arrays.asList(
				new Person("홍길동", 21, "남"),
				new Person("임꺽정", 40, "남"),
				new Person("유관순", 18, "여"),
				new Person("황진이", 20, "여"));
		
		/* 스트림
		 * 한 번 사용시 재사용 불가능, 따라서 또 사용하려면 재선언 해야함 */
		Stream<Person> stream = list.stream();
		
		// 성인 여성만 조회
		// filter(Predicate p) : 조건을 만족하는 요소들만 선택
		// forEach(Consumer c) : 요소를 하나씩 꺼내 처리
		stream.filter(p->p.getAge() >= 20)
			.filter(p->p.getGender().equals("여"))
			.forEach(p->System.out.println(p));
		
		// map(Function<T, R> f) : T를 가공해서 R로 변환
		stream = list.stream();
		stream.map(p->p.getName())
			.forEach(name->System.out.println(name));
		
		// count() : 요소의 수를 반환
		stream = list.stream();
		long count = stream.filter(p->p.getGender().equals("여")).count();
		System.out.println("등록된 여성 수 : "+count);
		stream = list.stream();
		count = stream.filter(p->p.getAge() >= 20).count();
		System.out.println("등록된 성인 수 : "+count);
		
		// average() : 평균
		stream = list.stream();
		OptionalDouble ageAverage = stream.mapToInt(Person::getAge).average();
		if(ageAverage.isPresent()) {
			System.out.println("평균 나이 : "+ageAverage.getAsDouble());
		}else {
			System.out.println("일치하는 사람이 없습니다.");
		}
		
		// collect() : 리스트로 반환
		stream = list.stream();
		List<Person> list2 = stream.filter(p->p.getAge() < 20).collect(Collectors.toList());
		System.out.println(list2);
	}
}

@Data
@AllArgsConstructor
class Person{
	private String name;
	private int age;
	private String gender;
}