package day10;

import java.util.Scanner;

public class VocabularyEx01 {
	
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		int menu = 0, wordCount = 0, index = 0;
		final int WORD_MAX = 2;
		Word[] list = new Word[WORD_MAX];
		
		do {
			printMenu();
			menu = scan.nextInt();
			switch(menu) {
			case 1:
				wordCount = insertWord(list, wordCount);
				if(wordCount == list.length) {
					list = expandWordList(list, list.length + 2);
				}
				for(int i = 0; i < wordCount; i++) {
					list[i].print();
				}
				break;
			case 2:
				index = findWord(list, wordCount);
				if(index == -1) {
					break;
				}
				list = modifyWord(list, wordCount, index);
				for(int i = 0; i < wordCount; i++) {
					list[i].print();
				}
				break;
			case 3:
				index = findWord(list, wordCount);
				if(index == -1) {
					break;
				}
				list[index].print();
				break;
			case 4:
				index = findWord(list, wordCount);
				if(index == -1) {
					break;
				}
				list = deleteWord(list, wordCount, index);
				wordCount--;
				for(int i = 0; i < wordCount; i++) {
					list[i].print();
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
		System.out.println("1. 단어 등록");
		System.out.println("2. 단어 수정");
		System.out.println("3. 단어 검색");
		System.out.println("4. 단어 삭제");
		System.out.println("5. 종료");
		System.out.print("메뉴 선택 : ");
	}
	
	public static Word inputWord() {
		System.out.print("단어 : ");
		String word = scan.next();
		System.out.print("품사 : ");
		String wordClass = scan.next();
		System.out.print("의미 : ");
		scan.nextLine();
		String meaning = scan.nextLine();
		return new Word(word, wordClass, meaning);
	}
	
	public static void sortWord(Word[] list, int wordCount) {
		for(int i = 0; i < wordCount - 1; i++) {
			for(int j = 0; j < wordCount - 1; j++) {
				if(list[j].getWord().compareTo(list[j+1].getWord()) > 0) {
					Word tmp = list[j];
					list[j] = list[j+1];
					list[j+1] = tmp;
				}
			}
		}
	}
	
	public static int insertWord(Word[] list, int wordCount) {
		Word tmp = inputWord();
		list[wordCount++] = tmp;
		sortWord(list, wordCount);
		System.out.println("단어 등록을 완료했습니다.");
		System.out.println("----------------------------------------------------");
		return wordCount;
	}
	
	public static Word[] expandWordList(Word[] list, int size) {
		if(list.length >= size) {
			return list;
		}
		Word [] tmp = new Word[size];
		System.arraycopy(list, 0, tmp, 0, list.length);
		return tmp;
	}
	
	public static int findWord(Word[] list, int wordCount) {
		System.out.print("단어 : ");
		String word = scan.next();
		int count = 0;
		for(int i = 0; i < wordCount; i++) {
			if(list[i].getWord().equals(word)) {
				System.out.print((i+1)+". ");
				list[i].print();
				count++;
			}
		}
		if(count == 0) {
			System.out.println("일치하는 단어가 없습니다.");
			return -1;
		}
		System.out.print("번호 선택 : ");
		int index = scan.nextInt();
		boolean res = checkWord(list, word, index - 1);
		if(!res) {
			System.out.println("잘못된 번호입니다.");
			return -1;
		}
		return index - 1;
	}
	
	public static boolean checkWord(Word[] list, String word, int index) {
		if(list.length <= index || index < 0) {
			return false;
		}
		if(list[index] == null) {
			return false;
		}
		return list[index].getWord().equals(word);
	}

	public static Word[] modifyWord(Word[] list, int wordCount, int index) {
		Word tmp = inputWord();
		list[index] = tmp;
		sortWord(list, wordCount);
		System.out.println("단어 수정을 완료했습니다.");
		System.out.println("----------------------------------------------------");
		return list;
	}
	
	public static Word[] deleteWord(Word[] list, int wordCount, int index) {
		for(int i = index; i < wordCount; i++) {
			list[i] = list[i+1];
		}
		Word[] tmp = new Word[wordCount];
		for(int i = 0; i < wordCount - 1; i++) {
			tmp[i] = list[i];
		}
		System.out.println("단어 삭제를 완료했습니다.");
		System.out.println("----------------------------------------------------");
		return tmp;
	}

}

/**영어 단어를 관리하기 위한 Word 클래스를 만들고,
 * 필요한 멤버변수들을 선언 */
class Word{
	private String word, wordClass, meaning;
	
	public Word() {}
	
	public Word(String word, String wordClass, String meaning) {
		this.word = word;
		this.wordClass = wordClass;
		this.meaning = meaning;
	}
	
	public void print() {
		System.out.println(word+"("+wordClass+") : "+meaning);
	}
	
	public String getWord() {return word;}
	public void setWord(String word) {this.word = word;}
	public String getMeaning() {return meaning;}
	public void setMeaning(String meaning) {this.meaning = meaning;}
	public String getWordClass() {return wordClass;}
	public void setWordClass(String wordClass) {this.wordClass = wordClass;}
	
}
