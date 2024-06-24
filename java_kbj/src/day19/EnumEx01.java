package day19;

import java.util.Scanner;

public class EnumEx01 {

	public static void main(String[] args) {
		/* 열거형 : 상수 데이터들의 집합, enum 이용하여 생성
		 * 열거형 정의
		 * enum 열거형명{
		 * 		값1,
		 * 		값2,
		 * 		...
		 * } 
		 * 열거형 사용
		 * 열거형명 객체명 = 열거형.값; */
		System.out.print("계절 입력(봄:SPRING, 여름:SUMMER, 가을:FALL, 겨울:WINTER) : ");
		Scanner scan = new Scanner(System.in);
		String season = scan.next();
		Season s = Season.valueOf(season);
		System.out.println(s);
		switch(s) {
		case SPRING:
			System.out.println("봄입니다.");
			break;
		case SUMMER:
			System.out.println("여름입니다.");
			break;
		case FALL:
			System.out.println("가을입니다.");
			break;
		case WINTER:
			System.out.println("겨울입니다.");
			break;
		}
		
		System.out.print("계절 입력(봄:1, 여름:2, 가을:3, 겨울:4) : ");
		int num = scan.nextInt();
		s = null;
		/* 열거형.values() : 열거형 객체 안에 있는 모든 상수들을 배열로 반환 
		 * 열거형.ordinal() : 열거형 객체의 순서로 0부터 시작 */
		for(Season tmp : Season.values()) {
			if(num == tmp.ordinal() + 1) {
				s = tmp;
			}
		}
		switch(s) {
		case SPRING:
			System.out.println("봄입니다.");
			break;
		case SUMMER:
			System.out.println("여름입니다.");
			break;
		case FALL:
			System.out.println("가을입니다.");
			break;
		case WINTER:
			System.out.println("겨울입니다.");
			break;
		}
	}
}
enum Season{
	SPRING,
	SUMMER,
	FALL,
	WINTER;
}
enum Season2{
	SPRING(1),
	SUMMER(2),
	FALL(3),
	WINTER(4);
	private final int value;
	private Season2(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	// 정수를 이용하여 열거형 객체를 선택하는 클래스(정적) 메서드
	public static Season2 fromValue(int value) {
		for(Season2 tmp : Season2.values()) {
			if(tmp.getValue() == value) {	
				return tmp;
			}
		}
		throw new IllegalArgumentException("잘못된 계절입니다.");
	}
}