package day13;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionEx01 {

	public static void main(String[] args) {
		/* Exception 클래스 */
		
		/* RuntimeException 클래스
		 * 대표적인 자식 클래스들
		 * NullPointerException
		 * ArrayIndexOutOfBoundsException
		 * NumberFormatException
		 * ClassCastException
		 * ArithmeticException */
		
		/* try-catch문
		 * 여러 catch 를 통해 다양한 예외처리를 할 수 있음
		 * catch 는 순서대로 동작하기 때문에 부모 예외가 자식 예외보다 위에 오면 안됨 
		 * Exception - RuntimeException - 나머지 순서대로 상속 */
		int num1 = 1, num2 = 0;
		try {
			System.out.println(num1 / num2);
		}
		catch(ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다.");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("잘못된 번지에 접근했습니다.");
		}
		catch(RuntimeException e) {
			System.out.println("실행 예외가 발생했습니다.");
		}
		catch(Exception e) {
			System.out.println("예외가 발생했습니다.");
		}
		
		String str = "2024/06/14";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			// 아래 코드에서 ParseException이 발생할 수 있고, ParseException은 RuntimeException이 아니므로 반드시 예외처리 해야함
			date = format.parse(str);
			System.out.println(date);
		} catch (ParseException e) {
			System.out.println("문자열이 날짜 형식이 아닙니다.");
		}
		
		int num3 = 1, num4 = 0;
		try {
			System.out.println(num3 % num4);
		}catch(Exception e) {
			System.out.println("예외 발생");
		}
		System.out.println("프로그램을 종료합니다.");
		
		try {
			System.out.println(num3 % num4);
		}catch(Exception e) {
			System.out.println("예외 발생");
		}finally {
			System.out.println("프로그램을 종료합니다.");
		}
		System.out.println("--------------------------");
		test1();
		test2();
	}
	
	public static void test1() {
		int num3 = 1, num4 = 0;
		try {
			System.out.println(num3 % num4);
		}catch(Exception e) {
			System.out.println("예외 발생");
			return;
		}
		System.out.println("프로그램을 종료합니다.");
	}
	
	public static void test2() {
		int num3 = 1, num4 = 0;
		try {
			System.out.println(num3 % num4);
		}catch(Exception e) {
			System.out.println("예외 발생");
			return;
		}finally { // 메서드에서 return 으로 빠져나가도 무조건 실행
			System.out.println("프로그램을 종료합니다.");
		}
	}
}