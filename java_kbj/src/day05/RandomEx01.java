package day05;

public class RandomEx01 {

	public static void main(String[] args) {
		/* min ~ max 사이의 정수를 랜덤으로 만드는 코드
		 * Math.random()은 0 이상 1 미만의 랜덤한 실수를 생성
		 * min <= random * (max-min+1) + min < max + 1
		 */
		int min = 1, max = 7;
		int random = (int)(Math.random() * (max - min + 1) + min);
		System.out.println("랜덤 수 : "+random);
		
	}

}
