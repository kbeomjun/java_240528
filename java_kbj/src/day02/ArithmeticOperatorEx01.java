package day02;

public class ArithmeticOperatorEx01 {

	public static void main(String[] args) {
		//산술연산자
		int num1 = 5, num2 = 2;
		System.out.println(num1+" + "+num2+" = "+(num1 + num2));
		System.out.println(num1+" - "+num2+" = "+(num1 - num2));
		System.out.println(num1+" * "+num2+" = "+(num1 * num2));
		System.out.println(num1+" / "+num2+" = "+(num1 / num2));
		System.out.println(num1+" / "+num2+" = "+(num1 / (double)num2));
		System.out.println(num1+" % "+num2+" = "+(num1 % num2));
		
		//증감연산자
		System.out.println("num1 : " + ++num1);
		System.out.println("num2 : " + num2++);
		
		//비교(관계)연산자
		System.out.println(num1+" < "+num2+" : "+(num1 < num2));
		System.out.println(num1+" > "+num2+" : "+(num1 > num2));		
		System.out.println(num1+" <= "+num2+" : "+(num1 <= num2));
		System.out.println(num1+" >= "+num2+" : "+(num1 >= num2));
		System.out.println(num1+" == "+num2+" : "+(num1 == num2));
		System.out.println(num1+" != "+num2+" : "+(num1 != num2));
		
		String str1 = "abc", str2 = "abc", str3 = new String("abc");
		System.out.println("str1 == str2 : "+(str1 == str2));
		System.out.println("str1 == str3 : "+(str1 == str3));
		System.out.println("str2 == str3 : "+(str2 == str3));
		// 문자열은 equals를 이용하여 비교해야 함
		System.out.println("str1 == str2 : "+(str1.equals(str2)));
		System.out.println("str1 == str3 : "+(str1.equals(str3)));
		System.out.println("str2 == str3 : "+(str1.equals(str3)));
		
		//논리연산자
		int score = 85;
		boolean isB = score >= 80 && score < 90;
		System.out.println(isB);
		
		int sub1 = 60, sub2 = 100, sub3 = 30, sub4 = 80;
		boolean isFail = sub1 < 40 || sub2<40 || sub3<40 || sub4<40;
		System.out.println(isFail);
		
		int age = 10;
		boolean isMinor = !(age >= 19);
		System.out.println(isMinor);
		
		//복합 대입 연산자
		int num3 = 4, num4 = 2; 
		num3 += num4;
		System.out.println(num3);
		
		//조건 연산자 : (조건식) ? 참일 때 값 : 거짓일 때 값
		int num5 = 3;
		String result = (num5%2 == 0) ? "짝수" : "홀수";
		System.out.println(num5+"은(는) "+result+"입니다.");
		
		//비트연산자 : 변수 또는 값을 비트로 나열한 후 각 비트별로 논리 연산을 함. &(AND), |(OR), ~(NOT), ^(XOR)
		
		//비트 이동 연산자 <<, >>, >>>
		
	}

}
