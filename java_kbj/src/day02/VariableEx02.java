package day02;

public class VariableEx02 {

	public static void main(String[] args) {
		// 문자열, 클래스, ""를 통해 선언
		String str = "Name";
		System.out.println(str);
		
		/* 변수 : 하나의 값을 저장하는 공간, 여러번 변경 가능 / 소문자로 시작, 단어가 변경될 때 대문자로 구분(카멜표기법) ex) totalCount
		 * 상수 : 값을 저장 후 수정할 수 없는 공간 / 대문자 ex) MAX_AGE
		 * 리터럴 : 그 자체로 값을 의미
		 * 패키지는 소문자, 클래스는 대문자
		 * 이름은 의미있게 작성
		 * 변수 선언 시 항상 초기화 진행
		 */
		int num = 0; final int MAX_NUM = 10;
		
		/* 문자열 + 기본형 => 문자열
		 * 기본형 + 문자열 => 문자열
		 * 더하기는 앞에서부터 차례로 연산되기 때문에, 문자열 뒤의 기본형의 덧셈은 모두 문자열로 출력
		 */
		String str1 = 1 + "1abc", str2 = 1 + 2 + "abc" + 2 + 1;
		System.out.println(str1); System.out.println(str2);
		
		/* 형변환 : 값을 다른 자료형으로 변환하는 것
		 * boolean을 제외한 기본형은 변환 가능
		 * 자동(묵시적) 자료형 변환 : 정수 => 실수, 문자 => 정수, 작은 바이트 => 큰 바이트
		 * 강제(명시적) 자료형 변환 : 실수 => 정수, 큰 바이트 => 작은 바이트
		 */
		double num1 = 3; // 자동, 정수 => 실수
		long num2 = 3; // 자동, 정수 리터럴은 int
		int num3 = 3; // 형변환 X
		long num4 = num3; // 자동
		int num5 = (int)3.14; // 강제, 실수 => 정수
		byte num6 = (byte)num5; // 강제
		System.out.println((byte)123123); //강제 변환을 하여 값이 짤리는 경우
		double res = 1/2; System.out.println(res); // 1/2의 결과인 0이 자동 변환을 통해 0.0으로 변환
		res = (double)1/2; System.out.println(res); // 1을 1.0으로 변환하여 1.0/2를 계산해서 0.5가 저장됨
	}

}
