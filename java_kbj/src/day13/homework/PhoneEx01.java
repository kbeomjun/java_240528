package day13.homework;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneEx01 {

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		/* 연락처 관리를 위한 프로그램 
		 * 번호가 같으면 등록이 불가 */
		final int EXIT = 5;
		int menu = 0, phoneCount = 0;
		Phone[] list = new Phone[10];
		
		do {
			printMenu();
			try {
				menu = scan.nextInt();
				System.out.println("-------------------------");
				phoneCount = runMenu(list, phoneCount, menu);
				System.out.println("-------------------------");
			}catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴 입력입니다.");
				scan.next();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}while(menu != EXIT);
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
	
	public static int runMenu(Phone[] list, int phoneCount, int menu) throws Exception {
		switch(menu) {
		case 1:
			phoneCount = insert(list, phoneCount);
			break;
		case 2:
			update(list, phoneCount);
			break;
		case 3:
			phoneCount = delete(list, phoneCount);
			break;
		case 4:
			search(list, phoneCount);
			break;
		case 5:
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴 입력입니다.");
		}
		return phoneCount;
	}

	public static int indexOf(Phone[] list, int phoneCount, Phone tmp) {
		if(list == null || phoneCount == 0) {
			return -1;
		}
		for(int i = 0; i < phoneCount; i++) {
			if(list[i].equals(tmp)) {
				return i;
			}
		}
		return -1;
	}
	
	public static int insert(Phone[] list, int phoneCount) throws Exception {
		scan.nextLine();
		System.out.print("이름 : ");
		String name = scan.nextLine();
		System.out.print("번호 : ");
		String number = scan.nextLine();
		Phone tmp = new Phone(name, number);
		int index = indexOf(list, phoneCount, tmp);
		if(index >= 0) {
			System.out.println("이미 등록된 번호입니다.");
			return phoneCount;
		}
		list[phoneCount++] = tmp;
		System.out.println("번호 등록을 완료했습니다.");
		return phoneCount;
	}
	
	public static boolean printPhone(Phone[] list, int phoneCount, String name) {
		if(list == null || phoneCount == 0) {
			System.out.println("등록된 번호가 없습니다.");
			return false;
		}
		int count = 0;
		for(int i = 0; i < phoneCount; i++) {
			if(list[i].getName().contains(name)) {
				System.out.println((i+1)+". "+list[i].toString());
				count++;
			}
		}
		if(count == 0) {
			System.out.println("일치하는 번호가 없습니다.");
			return false;
		}
		return true;
	}

	public static boolean checkPhone(Phone[] list, int phoneCount, String name, int index) {
		if(list == null || phoneCount == 0) {
			return false;
		}
		if(index < 0 || index >= phoneCount) {
			return false;
		}
		return list[index].getName().contains(name);
	}
	
	public static void update(Phone[] list, int phoneCount) throws Exception {
		scan.nextLine();
		System.out.print("이름 입력 : ");
		String name = scan.nextLine();
		if(!printPhone(list, phoneCount, name)) {
			return;
		}
		System.out.print("번호 선택 : ");
		int index = scan.nextInt() - 1;
		if(!checkPhone(list, phoneCount, name, index)) {
			System.out.println("잘못 선택했습니다.");
			return;
		}
		scan.nextLine();
		System.out.print("이름 : ");
		String newName = scan.nextLine();
		System.out.print("번호 : ");
		String newNumber = scan.nextLine();
		Phone tmp = new Phone(newName, newNumber);
		if(indexOf(list, phoneCount, tmp) >= 0) {
			System.out.println("이미 등록된 번호입니다.");
			return;
		}
		list[index] = tmp;
		System.out.println("번호 수정을 완료했습니다.");
	}

	private static int delete(Phone[] list, int phoneCount) {
		scan.nextLine();
		System.out.print("이름 입력 : ");
		String name = scan.nextLine();
		if(!printPhone(list, phoneCount, name)) {
			return phoneCount;
		}
		System.out.print("번호 선택 : ");
		int index = scan.nextInt() - 1;
		if(!checkPhone(list, phoneCount, name, index)) {
			System.out.println("잘못 선택했습니다.");
			return phoneCount;
		}
		phoneCount--;
		if(index != phoneCount) {
			Phone[] tmps = new Phone[list.length];
			System.arraycopy(tmps, index+1, list, index, phoneCount-index);
		}
		list[phoneCount] = null;
		System.out.println("번호 삭제를 완료했습니다.");
		return phoneCount;
	}

	private static void search(Phone[] list, int phoneCount) {
		scan.nextLine();
		System.out.println("검색할 이름 입력(전체 검색 : 엔터) : ");
		String name = scan.nextLine();
		if(!printPhone(list, phoneCount, name)) {
			return;
		}
	}
}

class Phone{
	private String name, number;
	
	public Phone() {}
	public Phone(String name, String number) throws Exception {
		this.name = name;
		setNumber(number);
	}
	@Override
	public String toString() {
		return name+" : "+number;
	}
	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		return Objects.equals(number, other.number);
	}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getNumber() {return number;}
	public void setNumber(String number) throws Exception {
		// 주어진 번호가 전화번호 형태가 아니면 예외를 발생시키고 맞으면 번호에 저장
		String regex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
		if(!Pattern.matches(regex, number)) {
			throw new Exception("주어진 번호는 전화번호 형태가 아닙니다.");
		}
		this.number = number;
	}
}