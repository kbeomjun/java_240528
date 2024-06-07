package day08;

import java.util.Arrays;

public class ArraySortEx01 {

	public static void main(String[] args) {
		/*
		// 정수형 배열을 버블 정렬을 이용하여 정렬하는 예제
		// 버블정렬 시간 복잡도는 O(n) = n의 2제곱
		int [] arr = new int[] {1,6,3,2,7,8,4,5};
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = 0; j < arr.length - 1; j++) {
				// 내림차순, 올림차순은 부등호가 반대
				if(arr[j] > arr[j+1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
		*/
		// 문자열 배열을 버블 정렬을 이용하여 정렬하는 예제
		String [] arr = new String[] {"apple","a","zoo","banana"};
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = 0; j < arr.length - 1; j++) {
				// 문자열1.compareTo(문자열2) : 문자열1과 문자열2를 알파벳순으로 배치했을 때 위치를 정수로 알려줌
				// 같으면 0, 문자열1이 앞이면 음수, 뒤이면 양수
				if(arr[j].compareTo(arr[j+1]) > 0) {
					String tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
		
	}

}
