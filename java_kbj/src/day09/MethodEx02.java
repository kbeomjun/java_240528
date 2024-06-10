package day09;

public class MethodEx02 {

	public static void main(String[] args) {
		
		// 가변 매개변수를 이용하여 정수들의 합을 구하는 코드
		System.out.println(sum(1,2,3));
		System.out.println(sum(1,2,3,4));
		System.out.println(sum(1,2,3,4,5));
		
		// 메서드 오버로딩
		System.out.println(sum(1,2));
		System.out.println(sum(1.0,2.0));
		
	}
	
	// 가변 매개변수를 이용하여 정수들의 합을 구하는 메서드
	public static int sum(int ... nums) { // 가변 매개변수는 가장 마지막에 넣어야 함
		int sum = 0;
		for(int tmp : nums) {
			sum += tmp;
		}
		return sum;
	}
	
	/* 메서드 오버로딩의 조건
	 * 1. 매개변수의 개수가 다름
	 * 2. 매개변수의 타입이 다름 */
	// 메서드 오버로딩
	public static int sum(int num1, int num2) {
		return num1 + num2;
	}
	
	public static double sum(double num1, double num2) {
		return num1 + num2;
	}
	
}
