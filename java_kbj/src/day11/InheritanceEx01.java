package day11;

public class InheritanceEx01 {

	public static void main(String[] args) {
		// 상속 예제
		HighSchoolStudent std = new HighSchoolStudent();
		std.setSchoolName("KH정보고등학교");
		std.print();
		
		// super, Override 예제
		Child1 c1 = new Child1(1, 2);
		Child2 c2 = new Child2(3, 4);
		c1.print();
		c2.print();
		
		// 클래스 형변환 예제
		Circle cir1 = new Circle(10, 10, 10);
		Rect rec1 = new Rect(0, 10, 10, 10);
		Circle cir2 = new Circle(10, 10, 5);
		
		Shape[] shapes = new Shape[20]; // 포함 다형성 : 자식 클래스 객체를 부모 클래스에서 관리
		shapes[0] = cir1; // 업캐스팅에 의해 클래스 형변환이 실행
		shapes[1] = rec1;
		shapes[2] = cir2;
		for(Shape tmp : shapes) {
			if(tmp == null) {
				continue;
			}
			if(tmp instanceof Circle) {
				Circle circle = (Circle)tmp; // 다운캐스팅 : 업캐스팅 된 객체를 다운캐스팅 할때만 가능
				circle.drawCircle();
			}
			else if(tmp instanceof Rect) {
				((Rect)tmp).drawRect(); // 다운캐스팅
			}
		}
	}
}

// 상속 예제
class Student{
	private String schoolName;
	protected String name;
	public String birthday;
	int age;
	public void print() {
		System.out.println("학교 : "+schoolName);
		System.out.println("이름 : "+name);
		System.out.println("생일 : "+birthday);
		System.out.println("나이 : "+age);

	}
	public String getSchoolName() {return schoolName;}
	public void setSchoolName(String schoolName) {this.schoolName = schoolName;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getBirthday() {return birthday;}
	public void setBirthday(String birthday) {this.birthday = birthday;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
}
class HighSchoolStudent extends Student{ // Student를 상속받음
	public void test() {
		// System.out.println(schoolName); 접근제어자가 private이므로 자식 클래스에서 사용 불가능
		System.out.println(name); // 접근제어자가 protected이므로 같은 패키지 및 자식 클래스에서 사용 가능
		System.out.println(birthday); // 접근제어자가 public이므로 모든 클래스에서 사용 가능
		System.out.println(age); // 접근제어자가 default이므로 같은 패키지의 클래스에서 사용 가능
	}
}

// super, Override 예제
class Parent{
	int num;
	public Parent(int num) {
		this.num = num;
	}
	public void print() {
		System.out.println(num);
	}
}
class Child1 extends Parent{
	int num2;
	public Child1(int num1, int num2) {
		// 부모 클래스의 기본 생성자가 없어서 있는 생성자를 호출
		super(num1);
		this.num2 = num2;
	}
	@Override
	public void print() {
		super.print(); // 부모 클래스의 print()를 호출하기 위해 super 사용
		System.out.println("num2 : "+num2);
	}
}
class Child2 extends Parent{
	int num2;
	public Child2(int num1, int num2) {
		// 부모 클래스의 기본 생성자가 없어서 있는 생성자를 호출
		super(num1);
		this.num2 = num2;
	}
	@Override
	public void print() {
		System.out.println("num1 : "+num+", num2 : "+num2);
	}
}

// 클래스 형변환 예제
class Shape{
	String name = "도형";
	public Shape(String name) {
		this.name = name;
	}
	public void print() {
		System.out.println(name+"입니다.");
	}
}
class Rect extends Shape{
	int x, y;
	int width, height;
	public Rect(int x, int y, int width, int height) {
		super("사각형");
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	@Override
	public void print() {
		super.print();
		System.out.println("왼쪽 위의 점 : "+x+", "+y);
		System.out.println("너비 : "+width);
		System.out.println("높이 : "+height);
	}
	public void drawRect() {
		System.out.println("사각형을 그립니다.");
	}
}
class Circle extends Shape{
	int x, y;
	int radius;
	public Circle(int x, int y, int radius) {
		super("원");
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	@Override
	public void print() {
		super.print();
		System.out.println("중심점 : "+x+", "+y);
		System.out.println("반지름 : "+radius);
	}
	public void drawCircle() {
		System.out.println("원을 그립니다.");
	}
}
