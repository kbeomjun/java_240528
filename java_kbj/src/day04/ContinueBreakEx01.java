package day04;

import java.util.Scanner;

public class ContinueBreakEx01 {

	public static void main(String[] args) {
		// continue : 아래 코드를 실행하지 않고 반복문의 조건식으로 돌아감
		int i = 0;
		while(i < 10) {
			i++;
			if(i % 2 != 0) {
				continue;
			}
			System.out.print(i+" ");
		}
		System.out.println();
		
		// break : 반복문을 종료함
		Scanner scan = new Scanner(System.in);
		char ch;
		while(true) {
			System.out.print("y 입력 : ");
			ch = scan.next().charAt(0);
			if(ch == 'y') {
				break;
			}
		}
		System.out.println("y 입력 완료");
		
		int num1 = 8, num2 = 12; i = num1;
		while(true) {
			if(num1 % i == 0 && num2 % i == 0) {
				System.out.println(num1+"과 "+num2+"의 최대공약수 : "+i);
				break;
			}
			i--;
		}
	}

}
