package day07;

import java.util.Arrays;
import java.util.Scanner;

public class Exam01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		int menu = 0;
		do {
			System.out.println("메뉴");
			System.out.println("1. 추가");
			System.out.println("2. 검색");
			System.out.println("3. 종료");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			System.out.println("------------");
			switch(menu) {
			case 1: 
				System.out.println("추가했습니다.");
				System.out.println("------------");
				break;
			case 2:
				System.out.println("검색했습니다.");
				System.out.println("------------");
				break;
			case 3:
				System.out.println("프로그램을 종료합니다.");
				System.out.println("------------");
				break;
			default:
				System.out.println("잘못된 메뉴입니다.");
				System.out.println("------------");
			}
		}while(menu != 3);
		
		String [] list = new String[2];
		String word;
		int index = 0;
		while(true){
			System.out.print("단어 입력 (종료:-1) : ");
			word = scan.next();
			if(word.equals("-1")) {
				System.out.println("종료합니다.");
				break;
			}
			if(index < list.length) {
				list[index] = word;
				index++;
				System.out.println("단어를 추가했습니다.");
			}
			else {
				System.out.println("단어를 추가하지 못했습니다.");
			}
		}
		
		String [] list = new String[] {"cat","dog","banana"};
		String word;
		while(true){
		boolean result = false;
			System.out.print("단어 입력 (종료:-1) : ");
			word = scan.next();
			if(word.equals("-1")) {
				System.out.println("종료합니다.");
				break;
			}
			for (String tmp : list) {
				if(tmp.equals(word)) {
					result = true;
				}
			}
			if(result) {
				System.out.println("있는 단어입니다.");
			}
			else {
				System.out.println("없는 단어입니다.");
			}
		}
		*/
		int menu = 0, index = 0;
		String [] list = new String[2];
		String word;
		do {
			System.out.println("메뉴");
			System.out.println("1. 추가");
			System.out.println("2. 검색");
			System.out.println("3. 종료");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			System.out.println("------------");
			switch(menu) {
			case 1: 
				while(true){
					System.out.print("단어 입력 (종료:-1) : ");
					word = scan.next();
					if(word.equals("-1")) {
						System.out.println("종료합니다.");
						break;
					}
					if(index < list.length) {
						list[index] = word;
						index++;
						System.out.println("단어를 추가했습니다.");
					}
					else {
						System.out.println("단어를 추가하지 못했습니다.");
					}
				}
				System.out.println("메뉴로 돌아갑니다.");
				System.out.println("------------");
				break;
			case 2:
				while(true){
					boolean result = false;
					System.out.print("단어 입력 (종료:-1) : ");
					word = scan.next();
					if(word.equals("-1")) {
						System.out.println("종료합니다.");
						break;
					}
					for (String tmp : list) {
						if(tmp.equals(word)) {
							result = true;
						}
					}
					if(result) {
						System.out.println("있는 단어입니다.");
					}
					else {
						System.out.println("없는 단어입니다.");
					}
				}
				System.out.println("메뉴로 돌아갑니다.");
				System.out.println("------------");
				break;
			case 3:
				System.out.println("프로그램을 종료합니다.");
				System.out.println("------------");
				break;
			default:
				System.out.println("잘못된 메뉴입니다.");
				System.out.println("------------");
			}
		}while(menu != 3);
		
	}

}
