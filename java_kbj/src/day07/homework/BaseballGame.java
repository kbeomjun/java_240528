package day07.homework;

import java.util.Arrays;
import java.util.Scanner;

public class BaseballGame {

	public static void main(String[] args) {
		/* 중복되지 않은 1~9 사이의 랜덤한 수를 생성
		 * 스트라이크 : 해당 숫자가 있고, 위치도 같은 경우
		 * 볼 : 숫자가 있지만 위치가 다른 경우
		 * 아웃 : 일치하는 숫자가 하나도 없는 경우
		 * 
		 */
		Scanner scan = new Scanner(System.in);
		
		int [] list = new int[3];
		int min = 1, max = 9, count = 0, i = 0;
		while (count < list.length){
			int random = (int)(Math.random() * (max - min + 1) + min);
			for(i = 0; i < count; i++) {
				if(list[i] == random) {
					break;
				}
			}
			if (i == count) {
				list[count] = random;
				count++;
			}
		}
		System.out.println(Arrays.toString(list));
		
		int [] answer = new int[3];
		int strike = 0, ball = 0;
		do {
			strike = 0; ball = 0;
			System.out.print("숫자 입력 : ");
			
			for(i = 0; i < answer.length; i++) {
				answer[i] = scan.nextInt();
				if(answer[i] == list[i]) {
					strike++;
				}
			}
			
			for(i = 0; i < answer.length; i++) {
				for(int tmp : list) {
					if(tmp == answer[i]) {
						ball++;
					}
				}
			}
			
			if(strike == 3) {
				System.out.println("3S 정답입니다.");
				break;
			}
			
			if(strike > 0 && (ball-strike) == 0) {
				System.out.println(strike+"S");
			}
			else if(strike == 0 && (ball-strike) > 0) {
				System.out.println((ball-strike)+"B");
			}
			else if(strike > 0 && (ball-strike) > 0) {
				System.out.println(strike+"S "+(ball-strike)+"B");
			}
			else {
				System.out.println("OUT");
			}
			
		}while(strike != 3);
		
	}

}
