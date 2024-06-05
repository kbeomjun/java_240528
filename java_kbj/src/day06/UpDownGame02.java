package day06;

import java.util.Arrays;
import java.util.Scanner;

public class UpDownGame02 {

	public static void main(String[] args) {
		// UpDown 게임에 메뉴 기능 추가
		Scanner scan = new Scanner(System.in);
		
		int menu = 0, count = 0, index = 0;
		int [] records = new int[5];
		
		do {
			System.out.println("UpDown 게임");
			System.out.println("메뉴");
			System.out.println("1. 플레이");
			System.out.println("2. 기록확인");
			System.out.println("3. 종료");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			
			if (menu == 1) {
				int min = 1, max = 100;
				int random = (int)(Math.random() * (max - min + 1) + min);
				System.out.println("랜덤한 수 : " + random);
				
				count = 1;
				for( ; ; ) {
					System.out.print("정수 입력 : ");
					int num = scan.nextInt();
					if(random < num) {
						System.out.println("DOWN!");
						count++;
					}
					else if(random > num) {
						System.out.println("UP!");
						count++;
					}
					else {
						System.out.println("정답입니다.");
						System.out.println("맞힌 횟수는 "+count+"회 입니다.");
						if (index > 4) {
							if(records[records.length - 1] > count) {
								System.out.println("기록이 등록됩니다.");
								records[records.length - 1] = count;
								Arrays.sort(records, 0, index);
							}
							else {
								System.out.println("횟수가 너무 높아 등록할 수 없습니다.");
							}
						}
						else {
							System.out.println("기록이 등록됩니다.");
							records[index++] = count;
							Arrays.sort(records, 0, index);
						}
						break;
					}
				}
			}
			else if (menu == 2) {
				System.out.println("기록확인");
				for(int i = 0; i < records.length; i++) {
					if(records[i] == 0) {
						break;
					}
					System.out.println((i + 1)+"등 : "+records[i]+"회");
				}
			}
			else if (menu == 3) {
				System.out.println("프로그램을 종료합니다.");
			}
			else {
				System.out.println("잘못된 선택입니다.");
			}
			System.out.println("--------------------------------------------------");
		}while(menu != 3);
		
	}

}
