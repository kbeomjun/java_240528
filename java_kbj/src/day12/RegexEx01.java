package day12;

import java.util.regex.Pattern;

public class RegexEx01 {

	public static void main(String[] args) {
		/* Regex (정규표현식) : 문자열이 특정 패턴에 맞는지 확인할 때 사용
		 * \d : 숫자
		 * \s : 공백 & 탭
		 * \w : 영문 + 숫자
		 * [문자들] : 해당 문자들만 허용
		 * + : 1 이상 무한대 이하
		 * ? : 0 또는 1개
		 * * : 0개 이상
		 * ^ : 문장 처음에 위치 - 문장의 시작 / 중간에 위치 - Not
		 * $ : 문장의 가장 끝
		 * {min,max} : 반복 횟수
		 * () : 하나의 패턴 구분자 안에 서브 패턴을 지정해서 사용할 경우
		 * */
		
		/* Regex 예제
		 * 영문자와 숫자 5~8자 => ^\w{5,8}$
		 * 영문자와 숫자, 특수문자(!@#?) 5~8자 => [a-zA-Z0-9!@#?]{5,8}
		 * http:// 또는 https:// => ^(http://|https://) , ^https?://
		 * */
		
		/* Pattern 클래스
		 * 정규식에 맞는지 확인할 때 사용하는 클래스
		 * boolean result = Pattern.matches(정규표현식, 문자열)
		 * 문자열이 정규식 규칙에 맞는지 확인해서 맞으면 true, 아니면 false 반환
		 *  */
		String regex1 = "^\\w{5,8}$";
		String str1 = "abc123";
		System.out.println(str1+"은 정규표현식 "+regex1+"에 맞나? "+Pattern.matches(regex1, str1));
		
		String str2 = "abc123!";
		System.out.println(str2+"은 정규표현식 "+regex1+"에 맞나? "+Pattern.matches(regex1, str2));
		
		String regex2 = "[a-zA-Z0-9!@#?]{5,8}";
		System.out.println(str2+"은 정규표현식 "+regex2+"에 맞나? "+Pattern.matches(regex2, str2));
		
		String regex3 = "^(http://|https://)";
		String str3 = "http://";
		System.out.println(str3+"은 http:// 또는 https://로 시작하나? "+Pattern.matches(regex3, str3));
		
		String regex4 = "^https?://";
		System.out.println(str3+"은 http:// 또는 https://로 시작하나? "+Pattern.matches(regex4, str3));
	}
}