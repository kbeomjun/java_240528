package day12;

public class InheritanceEx02 {

	public static void main(String[] args) {
		Child c = new Child();
		c.run(); // 매개변수 다형성에 의해 실행
	}
	// 매개변수 다형성
	public static void run(Parent parent) {
		if(parent != null) {
			parent.run();
		}
	}
}
// 라이브러리에 있는 클래스라고 가정 => 다른 패키지에 존재하여 코드 수정이 불가함
class Parent{
	public void run() {
		System.out.println("실행합니다.");
	}
}
// 상속을 받아서 내가 원하는대로 구현 기능을 수정
class Child extends Parent{
	@Override
	public void run() {
		System.out.println("오버라이드하여 실행합니다.");
	}
}



/* final */
class Parent1{
	public final void print() {
		System.out.println("파이널 메서드입니다.");
	}
}
class Child1 extends Parent1{
	// final 메서드는 override 할 수 없음
	/*@Override 
	public final void print() {
		System.out.println("파이널 메서드를 수정하려고 합니다.");
	}*/
}
final class Parent2{
	
}
// final 클래스는 부모 클래스가 될 수 없음
//class Child2 extends Parent2{}
