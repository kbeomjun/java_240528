package day05;

public class ForEx02 {

	public static void main(String[] args) {
		/*
		int num1 = 6, num2 = 8, lcm = 0;
		// num1이 num2보다 작을 때 두 수를 바꾸는 코드
		if(num1 < num2) {
			int tmp = num1;
			num1 = num2;
			num2 = tmp;
		}
		// num1과 num2의 최소공배수를 찾는 코드
		for(int i = num1;  ; i+=num1) {
			if(i % num2 == 0) {
				lcm = i;
				System.out.println(num1+"와 "+num2+"의 최소공배수 : "+lcm);
				break;
			}
		}
		
		// 별 예제
		for(int i = 1; i < 6; i++) {
			for(int j = 0; j < 5-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		
		for(int i = 1; i < 6; i++) {
			for(int j = 0; j < 5-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i - 1; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		
		// 알파벳 예제
		for (int i = 1; i < 27; i++) {
			for(int j = 0; j < i; j++) {
				char ch = 'a';
				ch += j;
				System.out.print(ch);
			}
			System.out.println();
		}
		
		for (char ch1 = 'a'; ch1 <= 'z'; ch1++) {
			for(char ch2 = 'a'; ch2 <= ch1; ch2++) {
				System.out.print(ch2);
			}
			System.out.println();
		}
		*/
		/* 향상된 for문
		 * for(타입 변수명 : 배열 또는 컬렉션){
		 * 		변수명을 이용하여 활용;
		 * }
		 */
		 int [] arr = new int[] {1,2,3,4,5};
		 for(int tmp : arr) {
			 System.out.println(tmp);
		 }
		 
		 // 라벨 이름 A를 지정한 후 break A를 이용하면 A인 반복문을 break
		 A : for(int i = 1; i <= 5; i++) {
			 for(int j = 1; j <= 5; j++) {
				 System.out.print(i*j+" ");
				 if(j == 3) {
					 break A;
				 }
			 }
			 System.out.println();
		 }
		 
	}

}
