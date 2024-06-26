package day19.post;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import program.Program;

public class PostManager implements Program {
	private Scanner scan = new Scanner(System.in);
	private List<Post> list = new ArrayList<Post>();
	@Override
	public void printMenu() {
		System.out.print("메뉴\n"
				+"1. 게시글 등록\n"
				+"2. 게시글 수정\n"
				+"3. 게시글 삭제\n"
				+"4. 게시글 조회\n"
				+"5. 프로그램 종료\n");
		System.out.print("메뉴 선택: ");
	}
	@Override
	public void runMenu(int menu) throws Exception {
		switch(menu) {
		case 1:
			System.out.print("제목 : ");
			scan.nextLine();
			String title = scan.nextLine();
			System.out.print("내용 : ");
			String content = scan.nextLine();
			System.out.print("작성자 : ");
			String id = scan.nextLine();
			System.out.print("비밀번호 : ");
			String password = scan.nextLine();
			list.add(new Post(title, content, id, password, 0));
			for(int i = 0; i < list.size() - 1; i++) {
				if(list.getLast().getNum() == list.get(i).getNum()) {
					int num = list.getLast().getNum();
					list.getLast().setNum(++num);
				}
			}
			System.out.println("--------------------------");
			System.out.println(list.getLast().getNum()+"번 게시글을 등록했습니다.");
			System.out.println("--------------------------");
			break;
		case 2:
			System.out.print("수정할 게시글 번호 : ");
			int index = scan.nextInt();
			int count = 0;
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getNum() == index) {
					index = i;
					count = 0;
					break;
				}
				else {
					count = -1;
				}
			}
			if(count == -1) {
				System.out.println("게시글이 없습니다.");
				return;
			}
			System.out.print("아이디 : ");
			id = scan.next();
			System.out.print("비밀번호 : ");
			password = scan.next();
			if(!(list.get(index).getId().equals(id) && list.get(index).getPassword().equals(password))) {
				System.out.println("아이디나 비밀번호가 맞지 않습니다.");
				return;
			}
			System.out.print("제목 : ");
			scan.nextLine();
			title = scan.nextLine();
			System.out.print("내용 : ");
			content = scan.nextLine();
			list.get(index).setTitle(title);
			list.get(index).setContent(content);
			System.out.println("--------------------------");
			System.out.println(list.get(index).getNum()+"번 게시글을 수정했습니다.");
			System.out.println("--------------------------");
			break;
		case 3:
			System.out.print("삭제할 게시글 번호 : ");
			index = scan.nextInt();
			count = 0;
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getNum() == index) {
					index = i;
					count = 0;
					break;
				}
				else {
					count = -1;
				}
			}
			if(count == -1) {
				System.out.println("게시글이 없습니다.");
				return;
			}
			System.out.print("아이디 : ");
			id = scan.next();
			System.out.print("비밀번호 : ");
			password = scan.next();
			if(!(list.get(index).getId().equals(id) && list.get(index).getPassword().equals(password))) {
				System.out.println("아이디나 비밀번호가 맞지 않습니다.");
				return;
			}
			System.out.println("--------------------------");
			System.out.println(list.get(index).getNum()+"번 게시글을 삭제했습니다.");
			removePost(index);
			System.out.println("--------------------------");
			break;
		case 4: 
			System.out.print("검색어(전체는 엔터) : ");
			scan.nextLine();
			String search = scan.nextLine();
			count = 0;
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getTitle().contains(search)) {
					System.out.println(list.get(i).toString());
					count++;
				}
			}
			if(count == 0) {
				System.out.println("검색한 게시글이 없습니다.");
				break;
			}
			System.out.print("게시글을 확인 하시겠습니까?(y/n) ");
			String check = scan.next();
			if(check.equals("n")) {
				System.out.println("메뉴로 돌아갑니다.");
				System.out.println("--------------------------");
				break;
			}
			if(!check.equals("y")) {
				System.out.println("잘못된 입력입니다.");
				break;
			}
			System.out.print("검색 결과 중 확인할 게시글 번호 : ");
			index = scan.nextInt();
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getNum() == index) {
					int view = list.get(i).getView();
					list.get(i).setView(++view);
					System.out.print("제목 : "+list.get(i).getTitle()
							+"\n작성자 : "+list.get(i).getId()
							+"\n내용 : "+list.get(i).getContent()
							+"\n조회수 : "+list.get(i).getView()
							+"\n");
				}
			}
			System.out.println("--------------------------");
			System.out.print("메뉴로 돌아가려면 엔터를 치세요. ");
			scan.nextLine();
			String enter = scan.nextLine();
			if(enter.equals("\n")) {
				System.out.println("--------------------------");
				break;
			}
			break;
		case 5:
			System.out.println("--------------------------");
			System.out.println("프로그램 종료");
			System.out.println("--------------------------");
			break;
		default:
			System.out.println("--------------------------");
			System.out.println("잘못된 메뉴");
			System.out.println("--------------------------");
		}
	}
	private void removePost(int index) {
		for(int i = index; i < list.size(); i++) {
			int num = list.get(i).getNum();
			list.get(i).setNum(--num);
		}
		list.remove(index);
	}
	@Override
	public void run() {
		String filename = "src/day19/post/post.txt";
		load(filename);
		//
		int menu = 0;
		do {
			printMenu();
			menu = scan.nextInt();
			try {
				runMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}while(menu != 5);
		save(filename);
	}
	@Override
	public void save(String filename) {
		try(FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(list);
		} catch (IOException e) {
			System.out.println("입출력 예외 발생");
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void load(String filename) {
		try(FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis)){
			list = (List<Post>)ois.readObject();
		} catch (IOException e) {
			System.out.println("입출력 예외 발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("클래스를 찾을수 없습니다.");
			e.printStackTrace();
		} 
	}
}