package day12;

import java.util.Random;

public class RandomEx01 {

	public static void main(String[] args) {
		/* Random 클래스
		 * 난수를 생성하기 위한 기능을 제공하는 클래스
		 * Random() 생성자 : 현재 시간을 종자값으로 자동 설정
		 * Random(long seed) 생성자 : seed 값을 종자값으로 설정, seed 값을 설정하면 난수도 고정됨
		 * boolean nextBoolean() : boolean 타입의 난수 리턴
		 * double nextDouble() : double 타입의 난수 리턴, 0 이상 1 미만
		 * */
		
		Random r1 = new Random(10); // seed 값을 10으로 하면 프로그램을 재실행해도 랜덤한 수 패턴이 동일
		for(int i = 0; i < 100; i++) {
			int num  = r1.nextInt(10); // 0~9 사이 랜덤수
			System.out.print(num+" "+(i%30 == 29? "\n" : ""));
		}
		System.out.println();
		
		Random r2 = new Random(); // 현재 시간을 seed 값으로 활용
		for(int i = 0; i < 100; i++) {
			int num  = r2.nextInt(10); // 0~9 사이 랜덤수
			System.out.print(num+" "+(i%30 == 29? "\n" : ""));
		}
		System.out.println();
		
		/* min~max 사이의 랜덤한 수를 만드는 코드를 작성 */
		Random r3 = new Random();
		int min = 1, max = 5;
		for(int i = 0; i < 10; i++) {
			int num  = r3.nextInt(max - min +1) + min;
			System.out.print(num+" ");
		}
	}
}