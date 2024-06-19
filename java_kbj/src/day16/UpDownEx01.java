package day16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UpDownEx01 {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		/* UP DOWN 게임 - List, Class 사용*/
		int menu = 0;
		List<Record> list = new ArrayList<Record>();
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(list, menu);
		}while(menu != 3);
	}
	
	public static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 플레이");
		System.out.println("2. 기록확인");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택 : ");
	}
	
	public static int random(int min, int max) {
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		int random = (int)(Math.random() * (max - min + 1) + min);
		return random;
	}
	
	public static void runMenu(List<Record> list, int menu) {
		switch(menu) {
		case 1:
			int count = play();
			record(list, count);
			break;
		case 2:
			if(list.size() == 0) {
				System.out.println("등록된 기록이 없습니다.");
				break;
			}
			for(int i = 0; i < list.size(); i++) {
				System.out.println((i+1)+". "+list.get(i).toString());
			}
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	
	public static int play() {
		int min = 1, max = 100;
		int random = random(min, max);
		System.out.println("랜덤 : "+random);
		
		int input = 0;
		int count = 0;
		while(input != random) {
			count++;
			System.out.print("입력 : ");
			input = scan.nextInt();
			if(input < random) {
				System.out.println("UP!");
			}
			else if (input > random) {
				System.out.println("DOWN!");
			}
			else {
				System.out.println("정답!");
			}
		}
		return count;
	}
	
	public static void record(List<Record> list, int count) {
		final int MAX_RECORD_COUNT = 5;
		System.out.print("아이디 : ");
		String id = scan.next();
		if(list.size() < MAX_RECORD_COUNT) {
			list.add(new Record(count, id));
			System.out.println("기록을 등록했습니다.");
		}
		else {
			if(count < list.get(MAX_RECORD_COUNT - 1).getCount()) {
				list.set(MAX_RECORD_COUNT - 1, new Record(count,id));
				System.out.println("기록을 등록했습니다.");
			}
			else {
				System.out.println("기록을 등록하지 못했습니다.");
			}
		}
		Collections.sort(list, new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
				return o1.getCount().compareTo(o2.getCount());
			}
		});
	}
}

@Data
@AllArgsConstructor 
@NoArgsConstructor 
class Record{
	private Integer count;
	private String id;
	@Override
	public String toString() {
		return id+" : "+count+"회";
	}
}