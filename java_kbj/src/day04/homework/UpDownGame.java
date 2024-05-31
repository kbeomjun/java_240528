package day04.homework;

import java.util.Scanner;

public class UpDownGame {

	public static void main(String[] args) {
		// 1 ~ 100 사이의 랜덤한 수를 생성하여 해당 숫자를 맞추는 게임을 작성
		Scanner scan = new Scanner(System.in);
		
		int min = 1, max = 100;
		int random = (int)(Math.random() * (max - min + 1) + min);
		System.out.println("랜덤한 수 : " + random);
		for( ; ; ) {
			System.out.print("정수 입력 : ");
			int num = scan.nextInt();
			if(random < num) {
				System.out.println("DOWN!");
			}
			else if(random > num) {
				System.out.println("UP!");
			}
			else {
				System.out.println("정답입니다.");
				break;
			}
		}
		
	}

}
