package day13;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class FormatEx01 {

	public static void main(String[] args) {
		/* DecimalFormat 클래스
		 * 숫자 데이터를 원하는 형식으로 표현하기 위해 사용
		 * 0 : 10진수, 빈자리는 0으로 채움
		 * # : 10진수, 빈자리는 채우지 않음
		 * . : 소수점
		 * - : 음수 기호
		 * , : 단위 구분
		 * ; : 양수, 음수 패턴을 모두 기술할 경우
		 * % : 100을 곱한 후 %문자를 붙임, 주어진 숫자를 %단위로 환산
		 * \u00A4 : 통화기호
		 * E : 지수문자 x.x * 10 E제곱으로 표현할 때 사용
		 * */
		int num1 = 123456789;
		DecimalFormat df1 = new DecimalFormat("0000000000");
		System.out.println(df1.format(num1));
		
		DecimalFormat df2 = new DecimalFormat("###,###");
		System.out.println(df2.format(num1));
		
		DecimalFormat df3 = new DecimalFormat("#.##%");
		System.out.println(df3.format(num1));
		
		DecimalFormat df4 = new DecimalFormat("\u00A4#");
		System.out.println(df4.format(num1));
		
		double num2 = 12.3456;
		DecimalFormat df5 = new DecimalFormat("#.##");
		System.out.println(df5.format(num2));
		
		/* SimpleDateFormat */
		
		/* MessageFormat */
		int x = 1, y = 10;
		String format1 = "({0}, {1})";
		String result1 = MessageFormat.format(format1, x, y);
		System.out.println(result1);
		
		int grade = 1, classNum = 1, num = 1;
		String name = "홍길동";
		String format2 = "{0}학년 {1}반 {2}번 {3}";
		String result2 = MessageFormat.format(format2, grade, classNum, num, name);
		System.out.println(result2);
	}
}