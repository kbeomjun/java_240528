package day12;

public class AbstractClassEx01 {

	public static void main(String[] args) {
		Cat cat = new Cat();
		cat.bark();
		Dog dog = new Dog();
		dog.bark();
		Tiger tiger = new Tiger();
		tiger.bark();
		
		// 추상 클래스는 객체를 직접 생성할 수는 없지만, 포함 다형성으로 활용할 수 있다.
		Animal[] animals = new Animal[3];
		animals[0] = cat; // 업캐스팅
		animals[1] = dog;
		animals[2] = tiger;
		for(Animal tmp : animals) {
			tmp.bark();
		}
	}
}

abstract class Animal{ // 추상 메서드가 있는 클래스는 추상 클래스로 바꿔야 함, 하지만 추상 클래스에 추상 메서드가 반드시 들어가는 것은 아님
	public String name;
	public String species;
	public void sleep() {
		System.out.println("잠을 잡니다.");
	}
	public void eat(String food) {
		System.out.println(food+"를 먹습니다.");
	}
	public abstract void bark(); // 모든 자식 클래스가 override 하여 사용하는 메서드는 추상 메서드로 만듬
}
/* 추상 클래스를 상속받은 자식 클래스는 추상 클래스로 만들거나 추상 메서드를 override 한 일반 클래스로 만들어야 함 
 * 하지만 추상 클래스는 객체를 생성할 수 없으므로 보통 자식 클래스는 부모 클래스의 추상 메서드를 override 함 */
class Cat extends Animal{ 
	public Cat() {
		name = "고양이";
		species = "고양이과";
	}
	@Override
	public void bark() {
		System.out.println("야옹");
	}
}
class Dog extends Animal{
	public Dog() {
		name = "개";
		species = "개과";
	}
	@Override
	public void bark() {
		System.out.println("멍멍");
	}
}
class Tiger extends Animal{
	public Tiger() {
		name = "호랑이";
		species = "고양이과";
	}
	@Override
	public void bark() {
		System.out.println("어흥");
	}
}