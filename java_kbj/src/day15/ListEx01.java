package day15;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ListEx01 {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		/* 리스트 기본 예제 - 순서 O, 중복 O */
		ArrayList<String> list = new ArrayList<String>();
		
		/* add(객체) : 객체를 리스트에 추가, 컬렉션 인터페이스에서 구현
		 * add(번지, 객체) : 번지 위치에 객체를 추가, 리스트 인터페이스에서 구현 */
		list.add("안녕하세요");
		list.add("Hello");
		list.add(0, "Hi");
		list.add("홍길동");
		System.out.println(list);
		
		/* set(번지, 객체) : 번지에 객체를 수정 */
		list.set(0, "Bye");
		System.out.println(list);
		
		/* remove(객체) : 리스트에서 주어진 객체와 일치하는 객체를 제거, 컬렉션 인터페이스에서 구현 
		 * remove(번지) : 리스트에서 번지 위치에 있는 객체를 삭제하고 삭제된 객체를 반환, 리스트 인터페이스에서 구현 */
		System.out.println(list.remove("H"));
		System.out.println(list.remove("Hi"));
		System.out.println(list.remove(0));
		System.out.println(list);
		
		/* get(번지) : 번지 위치에 있는 객체를 가져옴, 리스트 인터페이스 */
		System.out.println(list.get(0));
		
		/* clear() : 리스트를 비움, 컬렉션 인터페이스 */
		//list.clear();
		
		/* contains(객체) : 리스트에 객체가 있는지 없는지를 알려줌, 컬렉션 인터페이스 */
		System.out.println("리스트에 Hi가 있나? : "+list.contains("Hi"));
		System.out.println("리스트에 홍길동이 있나? : "+list.contains("홍길동"));
		
		/* indexOf(객체) : 리스트에 객체가 몇번지에 있는지 알려줌, 리스트 인터페이스 */
		System.out.println("리스트에 Hi가 몇번지에 있나? : "+list.indexOf("Hi"));
		System.out.println("리스트에 홍길동이 몇번지에 있나? : "+list.indexOf("홍길동"));
		
		/* 향상된 for 문, Iterator */
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("hi");
		list2.add("apple");
		list2.add("banana");
		for(String tmp : list2) {
			System.out.print(tmp+" ");
		}
		System.out.println();
		Iterator<String> it = list2.iterator();
		while(it.hasNext()) {
			String tmp = it.next();
			System.out.print(tmp+" ");
		}
		System.out.println();
		
		/* 전화번호를 5번 입력받아 리스트에 저장, 삭제하는 코드 */
		ArrayList<String> list1 = new ArrayList<String>();
		for(int i = 0; i < 5; i++) {
			System.out.print("전화번호 입력 : ");
			list1.add(scan.next());
		}
		System.out.println(list1);
		System.out.print("삭제할 전화번호 입력 : ");
		if(list1.remove(scan.next())) {
			System.out.println("번호를 삭제했습니다.");
		}
		else {
			System.out.println("번호를 삭제하지 못했습니다.");
		}
		System.out.println(list1);
		
		/* 다음 기능을 가진 프로그램
		 * 메뉴
		 * 1. 번호 추가
		 * 2. 번호 삭제
		 * 3. 번호 조회 (sysout(list))로 대체
		 * 4. 종료 */
		int menu = 0;
		ArrayList<String> contact = new ArrayList<String>();
		do {
			System.out.println("메뉴");
			System.out.println("1. 번호 추가");
			System.out.println("2. 번호 삭제");
			System.out.println("3. 번호 조회");
			System.out.println("4. 종료");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			switch(menu) {
			case 1:
				System.out.print("추가할 번호 : ");
				String number = scan.next();
				if(contact.contains(number)) {
					System.out.println("이미 등록된 번호입니다.");
				}
				else {
					System.out.println("번호를 등록했습니다.");
					contact.add(number);
				}
				break;
			case 2:
				System.out.print("삭제할 번호 : ");
				if(contact.remove(scan.next())) {
					System.out.println("번호를 삭제했습니다.");
				}
				else {
					System.out.println("일치하는 번호가 없습니다.");
				}
				break;
			case 3:
				System.out.println(contact);
				break;
			case 4:
				System.out.println("프로그램 종료");
				break;
			default:
			}
		}while(menu != 4);
	}
}