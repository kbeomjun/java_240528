package day04;

import java.util.Scanner;

public class DoWhileEx01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		char ch = 'a';
		do {
			System.out.print("y 입력 : ");
			ch = scan.next().charAt(0);
		}while(ch != 'y');
		System.out.println("y 입력 완료");
		*/
		int num = 0;
		do {
			System.out.println("메뉴");
			System.out.println("1. 프로그램 실행");
			System.out.println("2. 프로그램 저장");
			System.out.println("3. 프로그램 불러오기");
			System.out.println("4. 프로그램 종료");
			System.out.print("메뉴 선택 : ");
			num = scan.nextInt();
		}while(num != 4);
		System.out.println("프로그램을 종료합니다.");
	}

}
