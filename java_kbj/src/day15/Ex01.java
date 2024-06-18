package day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Ex01 {

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		/* 숫자 야구 게임 코드 */
		int min = 1, max = 9;
		HashSet<Integer> set = new HashSet<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		while(set.size() < 3) {
			int random = (int)(Math.random() * (max - min + 1) + min);
			set.add(random);
		}
		list.addAll(set);
		Collections.shuffle(list);
		System.out.println(list);
		
		List<Integer> myList = new ArrayList<Integer>();
		int strike = 0, ball = 0;
		do {
			System.out.println("------------------------------------");
			myList.clear();
			strike = 0; ball = 0;
			System.out.print("숫자 3개 입력 : ");
			for(int i = 0; i < 3; i++) {
				myList.add(i, scan.nextInt());
			}
			for(int i = 0; i < list.size(); i++) {
				for(int j = 0; j < myList.size(); j++) {
					if(list.get(i) == myList.get(j)) {
						if(i == j) {
							strike++;
						}
						else if(i != j) {
							ball++;
						}
					}
				}
			}
			if(strike == 3) {
				System.out.println(strike+"S 정답");
				break;
			}
			if(strike > 0) {
				System.out.print(strike+"S");
			}
			if(ball > 0) {
				System.out.print(" "+ball+"B");
			}
			if(strike == 0 && ball == 0) {
				System.out.println("OUT");
			}
		}while(strike != 3);
	}
}