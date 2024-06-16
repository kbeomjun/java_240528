package day13.homework;

import java.util.Scanner;

public class PhoneEx01 {

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		/* 연락처 관리를 위한 프로그램 
		 * 번호가 같으면 등록이 불가 */
		int menu = 0, phoneCount = 0, count = 0;
		Phone[] list = new Phone[2];
		
		do {
			printMenu();
			menu = scan.nextInt();
			switch(menu) {
			case 1:
				if(phoneCount == list.length) {
					list = expandPhone(list, list.length+2);
				}
				phoneCount = insertNum(list, phoneCount);
				for(int i = 0; i < phoneCount; i++) {
					list[i].print();
				}
				break;
			case 2:
				count = findNum(list, phoneCount, count);
				if(count == 0) {
					System.out.println("없는 번호입니다.");
					break;
				}
				phoneCount = modifyNum(list, phoneCount);
				for(int i = 0; i < phoneCount; i++) {
					list[i].print();
				}
				break;
			case 3:
				count = findNum(list, phoneCount, count);
				if(count == 0) {
					System.out.println("없는 번호입니다.");
					break;
				}
				list = deletePhone(list, phoneCount--);
				for(int i = 0; i < phoneCount; i++) {
					list[i].print();
				}
				break;
			case 4:
				count = findNum(list, phoneCount, count);
				if(count == 0) {
					System.out.println("없는 번호입니다.");
					break;
				}
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("잘못된 메뉴입니다.");
			}
		}while(menu != 5);
	}
	
	public static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 번호 추가");
		System.out.println("2. 번호 수정");
		System.out.println("3. 번호 삭제");
		System.out.println("4. 번호 검색");
		System.out.println("5. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
	}
	
	public static Phone inputNum() {
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("번호(예 : xxx-xxxx-xxxx) : ");
		String num = scan.next();
		return new Phone(name, num);
	}
	
	public static Phone[] expandPhone(Phone[] list, int size) {
		if(list.length >= size) {
			return list;
		}
		Phone [] tmp = new Phone[size];
		System.arraycopy(list, 0, tmp, 0, list.length);
		return tmp;
	}
	
	public static int insertNum(Phone[] list, int phoneCount) {
		System.out.println("-------------------------");
		Phone tmp = inputNum();
		for(int i = 0; i < phoneCount; i++) {
			if(tmp.getNum().equals(list[i].getNum())) {
				System.out.println("이미 등록된 번호입니다.");
				return phoneCount;
			}
		}
		list[phoneCount++] = tmp;
		System.out.println("번호 등록을 완료했습니다.");
		System.out.println("-------------------------");
		return phoneCount;
	}
	
	public static int findNum(Phone[] list, int phoneCount, int count) {
		System.out.print("이름 입력 : ");
		String name = scan.next();
		count = 0;
		for(int i = 0; i < phoneCount; i++) {
			if(list[i].getName().contains(name)) {
				System.out.print((i+1)+". ");
				list[i].print();
				count++;
			}
		}
		return count;
	}
	
	public static int modifyNum(Phone[] list, int phoneCount) {
		System.out.print("수정할 번호 선택 : ");
		int num = scan.nextInt();
		Phone tmp = inputNum();
		for(int i = 0; i < phoneCount; i++) {
			if(i == num - 1) {
				continue;
			}
			if(tmp.getNum().equals(list[i].getNum())) {
				System.out.println("이미 등록된 번호입니다.");
				return phoneCount;
			}
		}
		list[num - 1] = tmp;
		System.out.println("번호 수정을 완료했습니다.");
		System.out.println("-------------------------");
		return phoneCount;
	}
	
	public static Phone[] deletePhone(Phone[] list, int phoneCount) {
		System.out.print("삭제할 번호 선택 : ");
		int num = scan.nextInt();
		Phone[] tmp = new Phone[phoneCount - 1];
		for(int i = num - 1; i < phoneCount - 1; i++) {
			tmp[i] = list[i + 1];
		}
		System.out.println("번호 삭제를 완료했습니다.");
		System.out.println("-------------------------");
		return tmp;
	}
}

class Phone{
	private String name, num;
	
	public Phone() {}

	public Phone(String name, String num) {
		this.name = name;
		this.num = num;
	}
	
	public void print() {
		System.out.println("이름 : "+name+" | 번호 : "+num);
	}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getNum() {return num;}
	public void setNum(String num) {this.num = num;}
}