package day04;

import java.util.Scanner;

public class ForEx01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		int num = 2;
		for(int i = 1; i < 10; i++) {
			System.out.println(num+" X "+i+" = "+num*i);
		}
		
		for(char ch = 'a'; ; ) {
			System.out.print("y 입력 : ");
			ch = scan.next().charAt(0);
			if(ch == 'y') {
				break;
			}
		}

		System.out.println("y 입력 완료");
		for(int i = 2; i < 10; i++) {
			for(int j = 1; j < 10; j++) {
				System.out.print(i+" X "+j+" = "+i*j+"   ");
			}
			System.out.println();
		}
		*/
		for(int num = 1; num <= 100; num++) {
			int count = 0;
			for(int i = 1; i <= num; i++) {
				if(num % i == 0) {
					count++;
				}
			}
			if(count == 2) {
				System.out.print(num+", ");
			}
		}
		
	}

}
