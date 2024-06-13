package day12;

public class WrapperEx01 {

	public static void main(String[] args) {
		/* Wrapper 클래스
		 * 기본자료형을 가지고 만든 클래스
		 * int와 char을 제외한 다른 자료형은 첫글자만 대문자로 바꾸면 래퍼 클래스가 됨
		 * int => Integer, char => Character
		 * 기본자료형을 래퍼 클래스의 객체로 만드는 걸 박싱, 래퍼 클래스의 객체를 기본자료형으로 만드는 걸 언박싱
		 * 래퍼클래스명.parse기본형(문자열) => 문자열을 기본자료형으로
		 *  */
		String str1 = "123";
		int num = Integer.parseInt(str1);
		System.out.println(num);
		
		String str2 = "3.14";
		double num2 = Double.parseDouble(str2);
		System.out.println(num2);
	}
}