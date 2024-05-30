package day01;

public class VariableEx01 {

	public static void main(String[] args) {
		/* 자료형 char
		 * 정수형 byte, short, int, long
		 * 실수형 float, double
		 * 논리형 boolean
		 */
		char ch1 = '\\', ch2 = '\n', ch3 = '+';
		System.out.println(ch1);
		System.out.println(ch2);
		System.out.println(ch3);
		
		// overflow
		byte num1 = 127;
		System.out.println(num1++);
		System.out.println(num1--);
		System.out.println(num1);
		
		// 접미사 long, float
		long num2 = 1234567890123L;
		System.out.println(num2);
		float num3 = 3.14F;
		double num4 = 3.14;
		System.out.println(num3+" "+num4 );
		
		boolean isEven = false;
		System.out.println(isEven);
	}

}
