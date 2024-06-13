package day11;

public class PolymorphismEx01 {

	public static void main(String[] args) {
		TV tv = new TV();
		Airconditioner aircon = new Airconditioner();
		turnOn(tv);
		turnOn(aircon);
	}
	
	// 매개변수의 다형성 : 메서드에 부모 객체를 사용함으로써 자식 객체를 호출
	public static void turnOn(Appliances app) {
		app.power = true;
		System.out.println(app.name+"이 켜졌습니다.");
	}
}

class Appliances{
	boolean power;
	String name = "가전제품";
}

class TV extends Appliances{
	public TV() {
		name = "TV";
	}
}

class Airconditioner extends Appliances{
	public Airconditioner() {
		name = "에어컨";
	}
}
