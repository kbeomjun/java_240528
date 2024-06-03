package day05;

import java.util.Scanner;

public class ArrayEx01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/* 배열은 자동으로 자료형에 맞는 초기화가 된다
		 * 배열은 참조형
		 */
		/*
		int [] scores1 = new int[5];
		int scores2 [] = new int[5];
		int scores3 [] = new int[] {1,2,3,4,5};
		
		scores1[0] = 1;
		scores1[1] = 2;
		scores1[2] = 3;
		scores1[3] = 4;
		scores1[4] = 5;
		System.out.println(scores1[0]);
		System.out.println(scores1[1]);
		System.out.println(scores1[2]);
		System.out.println(scores1[3]);
		System.out.println(scores1[4]);
		
		for (int i = 0; i < 5; i++) {
			scores1[i] = i + 1;
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(scores1[i]);
		}
		
		int score [] = new int[3];
		int total = 0;
		for(int i = 0; i < score.length; i++) {
			System.out.print("학생"+(i+1)+"의 성적 입력 : ");
			score[i] = scan.nextInt();
			total += score[i];
		}
		for(int i = 0; i < score.length; i++) {
			System.out.println("학생"+(i+1)+"의 성적 : "+score[i]);
		}
		double avg = (double)total / score.length;
		System.out.println("학생 "+score.length+"명의 평균 : "+avg);
		
		// 배열 복사 - 반복문 이용
		int [] arr1 = new int[] {5,4,3,2,1};
		int [] arr2 = new int[arr1.length];
		for(int i = 0; i < arr1.length; i++) {
			arr2[i] = arr1[i];
			System.out.print(arr2[i]+" ");
		}
		System.out.println();
		// 배열 복사 - System.arraycopy 이용 
		int [] arr3 = new int[arr1.length];
		System.arraycopy(arr1, 0, arr3, 0, arr1.length);
		for(int i = 0; i < arr3.length; i++) {
			System.out.print(arr3[i]+" ");
		}
		System.out.println();
		
		// 향상된 for문
		int [] arr4 = new int[] {1,2,3,4,5};
		for(int tmp : arr4) {
			System.out.print(tmp+" ");
		}
		System.out.println();
		
		int [] arr5 = new int[3];
		for(int i = 0; i < arr5.length; i++) {
			System.out.print("정수 입력 : ");
			arr5[i] = scan.nextInt();
		}
		for(int i = arr5.length - 1; i >= 0; i--) {
			System.out.print(arr5[i]+" ");
		}
		System.out.println();
		*/
		
		// 랜덤한 수 3개를 생성하고 정수를 입력하여 그 수가 있는지 없는지 판별하는 예제
		int min = 1, max = 10;
		int randoms[] = new int [3];
		for(int i = 0; i < randoms.length; i++) {
			int random = (int)(Math.random() * (max - min + 1) + min);
			randoms[i] = random;
			System.out.println("랜덤 수 : "+randoms[i]);
		}
		
		System.out.print("정수 입력 ("+min+"~"+max+") : ");
		int num = scan.nextInt();
		
		int cnt = 0;
		for(cnt = 0; cnt < randoms.length ; cnt++) {
			if (num == randoms[cnt]) {
				break;
			}
		}
		if(cnt < randoms.length) {
			System.out.println(num+"은 있습니다.");
		}
		else {
			System.out.println(num+"은 없습니다.");
		}
		
		boolean result = false;
		for (int tmp : randoms) {
			if(tmp == num) {
				result = true;
				break;
			}
		}
		if(result == true) {
			System.out.println(num+"은 있습니다.");
		}
		else {
			System.out.println(num+"은 없습니다.");
		}
		
	}

}
