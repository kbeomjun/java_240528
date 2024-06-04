package day06;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ArrayEx02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		// 배열 여러 개 선언
		int [] arr1 = new int[3], arr2 = new int[5]; 
		int arr3 []; // 이 형태로는 여러 개 배열 선언이 불가
		
		int [] arr = new int[9];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = 2 * (i + 1);
		}
		for(int i = 0; i < arr.length; i++) {
			System.out.println(2+" X "+(i+1)+" = " +arr[i]);
		}
		
		// 4자리 정수를 입력받고 역수로 출력하는 예제
		int [] arr = new int[4];
		System.out.print("4자리 정수 입력 : ");
		int num = scan.nextInt();
		if(num > 9999 || num < 1000) {
			System.out.println("잘못 입력");
		}
		else {
			int tmp = num;
			int i = 0;
			while(tmp != 0) {
				arr[i++] = tmp % 10;
				tmp = tmp / 10;
			}
		}
		System.out.print("정수 역순 : ");
		for(int tmp : arr) {
			System.out.print(tmp);
		}
		
		// 100이하의 소수를 찾는 예제 - 에라토스테네스의 체
		// 소수이면 0, 아니면 1
		int [] arr = new int[101];
		arr[1] = 1;
		for(int i = 2; i < arr.length; i++) {
			if(arr[i] == 1) {
				continue;
			}
			for(int j = 2 * i; j < 101; j += i) {
				arr[j] = 1;
			}
		}
		for(int i = 1; i < 101; i++) {
			if(arr[i] == 0) {
				System.out.print(i+" ");
			}
		}
		
		// 버블 정렬 - 앞 뒤로 비교하면서 정렬
		int [] arr = new int[] {1,3,5,7,9,2,4,6,8,10};
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if(arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
		for (int tmp : arr) {
			System.out.print(tmp + " "); 
		}
		
		// Dual-Pivot Quick Sort 알고리즘을 이용하여 정렬
		// Arrays.sort는 int[]에 대해 내림차순을 제공하지 않음
		int [] arr = new int[] {1,3,5,7,9,2,4,6,8,10};
		Arrays.sort(arr);
		for (int tmp : arr) {
			System.out.print(tmp + " "); 
		}
		System.out.println();
		// Integer[]에 대해 내림차순을 제공함
		Integer [] arr2 = {1,3,5,7,9,2,4,6,8,10};
		Arrays.sort(arr2, Collections.reverseOrder());
		System.out.println(Arrays.toString(arr2));
		*/
		// 과목 4개 점수를 입력받고 과락과 평균에 따라서 합격여부 따지는 예제
		int [] scores = new int[4];
		int total = 0;
		boolean isPass = true;
		double avg = 0.0;
		for(int i = 1; i < scores.length + 1; i++) {
			System.out.print("과목"+i+" 성적 입력 : ");
			scores[i - 1] = scan.nextInt();
			total += scores[i - 1];
			if(scores[i - 1] < 40) {
				isPass = false;
			}
		}
		avg = (double)total / scores.length;
		System.out.println("과락 : "+isPass+", 평균 : "+avg);
		if(isPass && avg >= 60) {
			System.out.println("Pass");
		}
		else {
			System.out.println("Fail");
		}
		
		
		
	}

}
