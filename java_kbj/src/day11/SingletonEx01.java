package day11;

public class SingletonEx01 {

	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		s1.message();
		
		Singleton s2 = Singleton.getInstance();
		s2.message();
		
		System.out.println(s1);
		System.out.println(s2);
	}

}

// 싱글톤 - 하나의 객체만을 사용하는 클래스
class Singleton{
	private static Singleton instance = new Singleton();
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		return instance;
	}
	
	public void message() {
		System.out.println("싱글톤입니다.");
	}
	
}
