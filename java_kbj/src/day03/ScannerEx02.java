package day03;

import java.util.Scanner;

public class ScannerEx02 {

	public static void main(String[] args) {
		// 한 번에 여러 값을 받는 예제
		int score1, score2, score3;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("세 학생의 성적을 입력하세요 : ");
		score1 = scan.nextInt();
		score2 = scan.nextInt();
		score3 = scan.nextInt();
		
		System.out.println("세 학생의 성적은 "+score1+", "+score2+", "+score3);
		
	}

}
