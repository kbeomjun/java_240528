package day03;

import java.util.Scanner;

public class SwitchEx01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		int num = 3;
		switch(num % 2) {
		case 0:
			System.out.println("짝");
			break;
		default:
			System.out.println("홀");
		}
		
		System.out.print("월을 입력하세요 : ");
		int month = scan.nextInt();
		switch(month) {
		case 1: case 2: case 12:
			System.out.println("겨울");
			break;
		case 3: case 4: case 5:
			System.out.println("봄");
			break;
		case 6: case 7: case 8:
			System.out.println("여름");
			break;
		case 9: case 10: case 11:
			System.out.println("가을");
			break;
		default:
			System.out.println("잘못된 월");
		}
		*/
		System.out.print("산술연산자와 두 정수를 입력하세요 : ");
		int num1 = scan.nextInt();
		char cal = scan.next().charAt(0);
		int num2 = scan.nextInt();
		double result = 0.0;
		
		switch(cal) {
		case '+':
			result = num1 + num2;
			System.out.println(num1+" "+cal+" "+num2+" = "+(int)result);
			break;
		case '-':
			result = num1 - num2;
			System.out.println(num1+" "+cal+" "+num2+" = "+(int)result);
			break;
		case '*':
			result = num1 * num2;
			System.out.println(num1+" "+cal+" "+num2+" = "+(int)result);
			break;
		case '/':
			result = (double)num1 / num2;
			System.out.println(num1+" "+cal+" "+num2+" = "+result);
			break;
		default:
			System.out.println("잘못된 연산자입니다.");
		}
		
	}

}
