package day03;

import java.util.Scanner;

public class IfEx01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		System.out.print("나이를 입력하세요 : ");
		int age = scan.nextInt();
		if(age >= 19) {
			System.out.println("성인입니다.");
		}
		else {
			System.out.println("미성년자입니다.");
		}
		
		System.out.print("월을 입력하세요 : ");
		int month = scan.nextInt();
		if(month == 3 || month == 4 || month == 5) {
			System.out.println("봄");
		}
		else if(month == 6 || month == 7 || month == 8) {
			System.out.println("여름");
		}
		else if(month == 9 || month == 10 || month == 11) {
			System.out.println("가을");
		}
		else if(month == 12 || month == 1 || month == 2) {
			System.out.println("겨울");
		}
		else {
			System.out.println("잘못된 월");
		}
		
		System.out.print("두 정수와 산술 연산자를 입력하세요 : ");
		int num1 = scan.nextInt();
		char cal = scan.next().charAt(0);
		int num2 = scan.nextInt();
		double result = 0;
		if(cal == '+') {
			result = num1 + num2;
			System.out.println(num1+" "+cal+" "+num2+" = "+(int)result);
		}
		if(cal == '-') {
			result = num1 - num2;
			System.out.println(num1+" "+cal+" "+num2+" = "+(int)result);
		}
		if(cal == '*') {
			result = num1 * num2;
			System.out.println(num1+" "+cal+" "+num2+" = "+(int)result);
		}
		if(cal == '/') {
			result = (double)num1 / num2;
			System.out.println(num1+" "+cal+" "+num2+" = "+result);
		}
		else {System.out.println("잘못된 연산자입니다.");}
		
		System.out.print("정수 입력 : ");
		int num = scan.nextInt();
		if(num % 6 == 0 && num != 0) {
			System.out.println("6의 배수입니다.");
		}
		else if(num % 2 == 0 && num != 0) {
			System.out.println("2의 배수입니다.");
		}
		else if(num % 3 == 0 && num != 0) {
			System.out.println("3의 배수입니다.");
		}
		else {
			System.out.println("2, 3, 6의 배수가 아닙니다.");
		}
		*/
		System.out.print("성적을 입력하세요 : ");
		int grade = scan.nextInt();
		if(grade <= 100 && grade >= 90) {
			System.out.println("A학점입니다.");
		}
		else if(grade < 90 && grade >= 80) {
			System.out.println("B학점입니다.");
		}
		else if(grade < 80 && grade >= 70) {
			System.out.println("C학점입니다.");
		}
		else if(grade < 70 && grade >= 60) {
			System.out.println("D학점입니다.");
		}
		else if(grade < 60 && grade >= 0) {
			System.out.println("F학점입니다.");
		}
		else {
			System.out.println("잘못된 성적입니다.");
		}
		
		//중첩 if문
		int num = 6;
		if (num % 3 == 0) {
			if (num % 2 == 0) {
				System.out.println("6의 배수입니다.");
			}
			else {
				System.out.println("6의 배수가 아닙니다.");
			}
		}
		else {
			System.out.println("6의 배수가 아닙니다.");
		}
		
		
	}

}
