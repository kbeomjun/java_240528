package day04;

import java.util.Scanner;

public class WhileEx01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int i = 0;
		while(i < 9) {
			i++;
			System.out.println("2 X "+i+" = "+2*i);
		}
		
		i = 0; int sum = 0;
		while(i < 10) {
			i++;
			sum += i;
		}
		System.out.println("1부터 10까지 합 : "+sum);
		
		i = 0; sum = 0;
		while(i < 10) {
			i += 2;
			sum += i;
		}
		System.out.println("1부터 10까지 짝수의 합 : "+sum);
		
		i = 0; int num = 12;
		System.out.print(num+"의 약수 : ");
		while(i < num) {
			i++;
			if(num % i == 0) {
				System.out.print(i + (i != num ? ", " : "\n"));
			}
		}
		
		i = 0; num = 4; int count = 0;
		while(i < num) {
			i++;
			if(num % i == 0) {
				count++;
			}
		}
		if (count == 2) {
			System.out.println(num+"는 소수입니다.");
		}
		else {
			System.out.println(num+"는 소수가 아닙니다.");
		}
		
		i = 0; int num1 = 8, num2 = 12, divMax = 1;
		while(i < num1) {
			i++;
			if (num1 % i == 0 && num2 % i == 0) {
				divMax = i;
			}
		}
		System.out.println(num1+"과 "+num2+"의 최대공약수는 : "+divMax);

		i = 0;
		while(i < 26) {
			System.out.print((char)('a'+i));
			i++;
		}
		System.out.println();
		
		char ch = 'a';
		while(ch != 'y') {
			System.out.print("y 입력 : ");
			ch = scan.next().charAt(0);
		}
		System.out.println("y 입력 완료");
	}

}
