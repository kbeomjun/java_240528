package day12;

public class MathEx01 {

	public static void main(String[] args) {
		/* Math 클래스
		 * 수학 관련 기능을 제공하는 클래스
		 * int abs(int a), double abs(double a) : 절대값
		 * double ceil(double a) : 소수점 첫번째 자리에서 올림
		 * double floor(double a) : 소수점 첫번째 자리에서 버림
		 * double random() : 0 이상 1 미만의 랜덤 수
		 * long round(double a) : 소수점 첫번째 자리에서 반올림, 정수 리턴
		 * */
		
		// abs() : 절댓값
		System.out.println("-1의 절댓값 : "+Math.abs(-1));
		
		// ceil() : 소수점 첫번째 자리에서 올림
		System.out.println("1.23을 소수점 첫번째 자리에서 올림 : "+Math.ceil(1.23));
		
		// floor() : 소수점 첫번째 자리에서 버림
		System.out.println("1.23을 소수점 첫번째 자리에서 버림 : "+Math.floor(1.23));
		
		// round() : 소수점 첫번째 자리에서 반올림 후 정수를 리턴
		System.out.println("1.23을 소수점 첫번째 자리에서 반올림 : "+Math.round(1.23));
		
		// sqrt() : 루트
		System.out.println("루트 16 : "+Math.sqrt(16));
		
		// pow() : n제곱
		System.out.println("2의 3제곱 : "+Math.pow(2, 3));
		
		/* 3.14를 소수점 2번째 자리에서 올림하는 코드를 작성 */
		double num = 3.14;
		num *= 10;
		num = Math.ceil(num);
		num /= 10;
		System.out.println(num);
	}
}