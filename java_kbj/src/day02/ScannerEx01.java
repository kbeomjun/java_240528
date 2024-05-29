package day02;

import java.util.Scanner; // ctrl+shift+O

public class ScannerEx01 {

	public static void main(String[] args) {
		// 콘솔창에서 원하는 값을 읽어와서 변수에 저장
		Scanner scan = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요 : ");
		int num1 = scan.nextInt();
		System.out.println(num1);
		
		System.out.print("실수를 입력하세요 : ");
		double num2 = scan.nextDouble();
		System.out.println(num2);
		
		System.out.print("단어를 입력하세요 : ");
		String str1 = scan.next(); // 콘솔에서 공백(엔터, 스페이스) 전까지 문자열을 가져옴
		System.out.println(str1);
		
		scan.nextLine(); // 앞에서 입력한 엔터를 비워줌
		System.out.print("문장을 입력하세요 : ");
		String str2 = scan.nextLine(); // 엔터 전까지 문자열을 가져옴
		System.out.println(str2);
		
		System.out.print("문자를 입력하세요 : ");
		char ch1 = scan.next().charAt(0);
		System.out.println(ch1);
		
		// 예제
		System.out.print("정수1을 입력하세요 : ");
		int num3 = scan.nextInt();
		System.out.print("정수2를 입력하세요 : ");
		int num4 = scan.nextInt();
		System.out.print("연산자를 입력하세요 : ");
		char cal = scan.next().charAt(0);
		System.out.println(""+num3+cal+num4);
	}

}
