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
	}

}
