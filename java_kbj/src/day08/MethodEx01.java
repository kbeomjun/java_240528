package day08;

import java.util.Arrays;
import java.util.Scanner;

public class MethodEx01 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
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
		System.out.println();
		
		// 두 수의 최대 공약수와 최소 공배수를 구하는 코드
		int num1 = 18, num2 = 12;
		int gcd = gcd(num1, num2);
		System.out.println(num1+"과 "+num2+"의 최대공약수 : "+gcd);
		long lcm = lcm(num1, num2);
		System.out.println(num1+"과 "+num2+"의 최소공배수 : "+lcm);
		
		// 재귀 메서드로 인해 팩토리얼 구하는 코드
		System.out.println(factorial(5));
		
		// 주어진 정수 배열에 원하는 정수가 있는지 확인하는 코드
		int [] arr = new int[] {1,2,3,4,5};
		int num3 = 6;
		System.out.println(contains(arr, num3));
		*/
		// 크기를 입력받고 최소값과 최대값이 주어졌을 때, 중복되지 않은 랜덤한 배열을 생성하는 코드
		System.out.print("배열 크기 입력 : ");
		int size = scan.nextInt();
		int min = 1, max = 9;
		int [] randomArr = randomArray(size, min, max);
		System.out.println(Arrays.toString(randomArr));
		
	}
	
	// 크기를 입력받고 최소값과 최대값이 주어졌을 때, 중복되지 않은 랜덤한 배열을 생성하는 메서드
	public static int[] randomArray(int size, int min, int max) {
		if(size < 0) {
			return null;
		}
		if (min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		if(size > max - min + 1) {
			return null;
		}
		int[] arr = new int[size];
		int count = 0, i = 0;
		while (count < size){
			int random = (int)(Math.random() * (max - min + 1) + min);
			for(i = 0; i < count; i++) {
				if(arr[i] == random) {
					break;
				}
			}
			if (i == count) {
				arr[count] = random;
				count++;
			}
		}
		return arr;
	}
	
	// 주어진 배열에 원하는 정수가 있는지 확인하는 메서드
	public static boolean contains(int[] arr, int num) {
		if(arr == null) {
			return false;
		}
		for(int tmp : arr) {
			if(tmp == num) {
				return true;
			}
		}
		return false;
	}
	
	// 재귀 메서드를 이용한 팩토리얼 구하는 메서드
	public static long factorial(int num) {
		if(num < 0) {
			return 0;
		}
		if(num == 0 || num == 1) {
			return 1;
		}
		return num * factorial(num - 1);
	}
	
	// 두 수의 최대공약수를 구하는 메서드
	public static int gcd(int num1, int num2) {
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
	public static long lcm(int num1, int num2) {
		if(num1 > num2) {
			int tmp = num1;
			num1 = num2;
			num2 = tmp;
		}
		for (long i = num2; i <= num1 * (long)num2; i+=num2) {
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
