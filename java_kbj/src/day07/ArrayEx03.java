package day07;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayEx03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		// 배열 선언, 출력
		int [] arr1 = new int[] {1,2,3,4,5};
		System.out.println(Arrays.toString(arr1));
		// 배열 선언 이후에 초기화 가능
		arr1 = new int[] {5,4,3,2,1};
		System.out.println(Arrays.toString(arr1));
		// arr1 = {7,6,5,4,3}; 이런식으로는 초기화 불가능
		
		// 3명의 학생의 국영수 성적 입력받고 각 학생의 평균을 구하는 예제
		int studentCount = 3, sum = 0;
		int [] kor, eng, math;
		kor = new int[studentCount];
		eng = new int[studentCount];
		math = new int[studentCount];
		double avg = 0.0;
		
		for(int i = 0; i < studentCount; i++) {
			System.out.print("학생"+(i+1)+"의 성적 입력(국어, 영어, 수학 순) : ");
			kor[i] = scan.nextInt();
			eng[i] = scan.nextInt();
			math[i] = scan.nextInt();
		}
		for(int i = 0; i < studentCount; i++) {
			sum = kor[i] + eng[i] + math[i];
			avg = (double)sum / studentCount;
			System.out.println("학생"+(i+1)+"의 평균 : "+avg);
		}
		sum = 0;
		for(int i = 0; i < studentCount; i++) {
			sum += kor[i];
		}
		avg = (double)sum / studentCount;
		System.out.println("국어 평균 : "+avg);
		sum = 0;
		for(int i = 0; i < studentCount; i++) {
			sum += eng[i];
		}
		avg = (double)sum / studentCount;
		System.out.println("영어 평균 : "+avg);
		sum = 0;
		for(int i = 0; i < studentCount; i++) {
			sum += math[i];
		}
		avg = (double)sum / studentCount;
		System.out.println("수학 평균 : "+avg);
		*/
		// 1~9 사이의 중복되지 않는 랜덤한 수 3개 저장하는 예제
		int [] list = new int[3];
		int min = 1, max = 9, count = 0, i = 0;
		while (count < list.length){
			int random = (int)(Math.random() * (max - min + 1) + min);
			for(i = 0; i < count; i++) {
				if(list[i] == random) {
					break;
				}
			}
			if (i == count) {
				list[count] = random;
				count++;
			}
			System.out.println(random);
		}
		System.out.println(Arrays.toString(list));
		
	}

}
