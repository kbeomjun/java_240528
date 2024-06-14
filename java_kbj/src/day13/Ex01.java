package day13;

import java.text.MessageFormat;

public class Ex01 {

	public static void main(String[] args) {
		/* 두 정수와 산술 연산자를 입력받아 산술연산결과를 알려주는 메서드를 정의 및 구현
		 * main 메서드에서 실행하는 코드
		 * 단, 예외 발생 시 예외 처리하는 코드를 추가 */
		int num1 = 1, num2 = 0;
		char cal = '/';
		try {
			double result = calculate(num1, num2, cal);
			String format ="{0} {1} {2} = {3}";
			System.out.println(MessageFormat.format(format, num1, cal, num2, result));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}
	
	public static double calculate(int num1, int num2, char cal) {
		double result = 0.0;
		switch(cal) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			if(num2 == 0) {
				throw new ArithmeticException("0으로 나눌 수 없습니다.");
			}
			result = num1 / (double)num2;
			break;
		case '%':
			if(num2 == 0) {
				throw new ArithmeticException("0으로 나눌 수 없습니다.");
			}
			result = num1 % num2;
			break;
		default:
			System.out.println("잘못된 연산자입니다.");
		}
		return result;
	}
}