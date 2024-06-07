package day08;

public class MethodEx01 {
	
	public static void main(String[] args) {
		/*
		// 주어진 숫자가 소수인지 아닌지 판별하는 코드
		int num = 4;
		boolean isPrime = isPrime(num);
		if(isPrime) {
			System.out.println(num+"은 소수입니다.");
		}
		else {
			System.out.println(num+"은 소수가 아닙니다.");
		}
		for(int i = 1; i < 101; i++) {
			boolean result = isPrime2(i);
			if(result) {
				System.out.print(i+" ");
			}
		}
		*/
		// 두 수의 최대 공약수와 최소 공배수를 구하는 코드
		int num1 = 18, num2 = 12;
		int maxDeno = maxDeno(num1, num2);
		System.out.println(num1+"과 "+num2+"의 최대공약수 : "+maxDeno);
		
		int minMulti = minMulti(num1, num2);
		System.out.println(num1+"과 "+num2+"의 최소공배수 : "+minMulti);
		
	}
	
	// 두 수의 최대공약수를 구하는 메서드
	public static int maxDeno(int num1, int num2) {
		int result = 0;
		if(num1 > num2) {
			int tmp = num1;
			num1 = num2;
			num2 = tmp;
		}
		for(int i = 1; i <= num1; i++) {
			if(num1 % i == 0 && num2 % i == 0) {
				result = i;
			}
		}
		return result;
	}
	
	// 두 수의 최소공배수를 구하는 메서드
	public static int minMulti(int num1, int num2) {
		if(num1 > num2) {
			int tmp = num1;
			num1 = num2;
			num2 = tmp;
		}
		for (int i = num2; i < num1 * num2; i+=num2) {
			if(i % num1 == 0) {
				return i;
			}
		}
		return num1 * num2;
	}
	
	// 주어진 num가 소수인지 아닌지 판별하는 메서드
	public static boolean isPrime(int num) {
		int count = 0;
		for(int i = 1; i <= num; i++) {
			if(num % i == 0) {
				count++;
			}
		}
		if(count == 2) {
			return true;
		}
		return false;
	}
	
	// 100 이하의 소수를 판별하는 메서드
	public static boolean isPrime2(int num) {
		if(num == 1) {
			return false;
		}
		for(int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
