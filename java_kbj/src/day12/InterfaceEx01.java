package day12;

public class InterfaceEx01 {

	public static void main(String[] args) {
		Dog2 dog = new Dog2();
		dog.bark();
		dog.sleep();
		// 인터페이스는 직접 객체를 만들 수 없고, 구현 클래스를 이용하여 객체를 만들 수 있음 (포함 다형성)
		AnimalAction aa = new Dog2();
		aa.bark();
		System.out.println("num : "+AnimalAction.num);
		System.out.println("num2 : "+AnimalAction.num2);
		AnimalAction.print();
	}
}
// 인터페이스는 상수와 추상 메서드로만 구성, default 메서드와 static 메서드도 추가 가능
interface AnimalAction{
	public static final int num = 10;
	int num2 = 20; // public static final 이 자동으로 붙음
	
	public abstract void bark();
	void eat(String food); // public abstract 가 자동으로 붙음
	default void sleep() { // default 메서드는 구현부를 작성해야 함
		System.out.println("잠을 잡니다.");
	}
	public static void print() { // static 메서드는 구현부를 작성해야 함
		System.out.println("동물의 동작입니다.");
	}
}
//클래스는 상속과 구현을 동시에 받을 수 있음
abstract class Animal2 implements AnimalAction{
	String name;
}

class Dog2 extends Animal2{ 
	@Override
	public void bark() {
		System.out.println("멍멍");
	}
	@Override
	public void eat(String food) {
		System.out.println(food+"을 먹습니다.");
	}
}



/* 다중 상속 */
//인터페이스는 추상 메서드로 구성되어 있으므로 다중 상속이 가능
interface InterfacePA{
	void print();
	void test1();
}
interface InterfacePB{
	void print();
	void test2();
}
interface InterfaceC extends InterfacePA, InterfacePB{
	
}

//클래스는 다중 상속이 불가능
class ClassA{
	void print() {}
	void test2() {}
}
class ClassB{
	void print() {}
	void test1() {}
}
//class ClassC extends ClassA, ClassB{}