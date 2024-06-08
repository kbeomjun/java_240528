package day08.homework;

import java.util.Arrays;

public class MethodHomework {
	
	public static void main(String[] args) {
		// 정수 num의 크기를 갖는 배열을 생성하는 코드
		int num = 5;
		int [] arr = array(num);
		System.out.println(Arrays.toString(arr));
		
		// 배열에 랜덤으로 1~9 사이의 중복되지 않은 배열을 생성하고 콘솔에 출력하는 코드
		int [] list = randomArray(5);
		System.out.println(Arrays.toString(list));
	}

	// 정수 num의 크기를 갖는 배열을 생성하는 코드
	public static int[] array(int num) {
		int [] arr = new int[num];
		return arr;
	}
	
	// 배열에 랜덤으로 1~9 사이의 중복되지 않은 배열을 생성하고 콘솔에 출력하는 메서드
	public static int[] randomArray(int num) {
		int[] arr = new int[num];
		int min = 1, max = 9, count = 0, i = 0;
		while (count < arr.length){
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

}
