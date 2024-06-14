package day12;

import java.util.Arrays;
import java.util.StringTokenizer;

public class StringEx01 {

	public static void main(String[] args) {
		/* String 클래스 */
		// char charAt(index)
		String str = "Hello world";
		System.out.println(str+"의 3번째 문자 : "+str.charAt(2));
		
		// boolean equals(Object obj)
		String str2 = "Hello world";
		System.out.println(str+"과 "+str2+"는 같나? "+str.equals(str2));
		
		// int indexOf(문자열)
		System.out.println(str+"에서 world의 시작 번지 : "+str.indexOf("world"));
		
		// boolean contains(문자열)
		System.out.println((str+"에서 world가 있나? "+str.contains("world")));
		
		// String replace(찾을 문자열, 교체할 문자열) : 문자열에서 찾을 문자열을 찾아서 교체할 문자열로 바꾼 후 반환
		System.out.println(str+"에서 o를 찾아서 O로 수정 : "+str.replace("o", "O"));
		System.out.println("수정한 후 str : "+str);
		
		// String substring(시작 번지) : 시작 번지부터 부분 문자열을 추출
		// String substring(시작 번지, 끝 번지) : 시작 번지부터 끝 번지 전까지 부분 문자열을 추출
		System.out.println(str+"에서 6번지부터 부분 문자열 : "+str.substring(6));
		System.out.println(str+"에서 6번지부터 10번지 전까지 부분 문자열 : "+str.substring(6, 10));
		
		// String toLowerCase(), String toUpperCase()
		System.out.println(str+"을 대문자로 : "+str.toUpperCase());
		System.out.println(str+"을 소문자로 : "+str.toLowerCase());
		
		// String trim() : 첫 문자 앞 공백과 마지막 문자 뒤 공백을 제거한 문자열을 반환
		String str3 = "          안녕하세요.          리뷰입니다.       \n";
		System.out.println(str3.trim());
		
		// String String.valueOf(기본자료형) : 기본 자료형을 문자열로 반환
		String str4 = String.valueOf(1);
		System.out.println(str4);
		String str5 = String.valueOf(true);
		System.out.println(str5);
		
		// String [] split(정규표현식) : 정규표현식을 기준으로 문자열을 추출하여 배열로 전환
		String fruit = "오렌지,사과,바나나,포도,수박";
		String[] fruits = fruit.split(",");
		System.out.println(Arrays.toString(fruits));
		
		/* StringBuffer 예제*/
		// String 은 수정이 아니라 교체, StringBuffer 는 수정
		// append(문자열) : 문자열을 제일 뒤에 추가
		StringBuffer sb = new StringBuffer("Hello world");
		sb.append("!");
		System.out.println(sb);
		
		// insert(시작 번지, 문자열) : 시작 번지에 문자열을 추가
		sb.insert(0, "\"");
		sb.append("\"");
		System.out.println(sb);
		
		// delete(시작 번지, 끝 번지) : 시작 번지부터 끝 번지 전까지를 삭제
		sb.delete(6, sb.length()-1);
		System.out.println(sb);
		
		/* StringBuilder */
		// String Buffer 클래스와 사용 방법이 동일, 차이점은 Buffer 클래스는 멀티쓰레드에서도 동기화가 적용되지만 Builder 클래스는 단일 쓰레드 환경에서만 사용
		
		/* StringTokenizer */
		// 문자열이 한 종류의 구분자로 되어 있는 경우 토큰을 쉽게 분리할 수 있게 하는 클래스
		String fruit2 = "사과,포도,배";
		StringTokenizer st = new StringTokenizer(fruit2, ",");
		// boolean hasMoerTokens() : 토큰이 더 있는지를 알려주는 메서드
		while(st.hasMoreTokens()) {
			String token = st.nextToken(); // 토큰을 가져옴
			System.out.println(token);
		}
		
		/* 다음과 같이 책 제목들이 있을 때 원하는 단어를 검색해서 해당 단어가 있는 책을 출력하는 코드 
		 * 일치하는 책이 없는 경우 일치하는 책이 없습니다. 라고 출력 */
		String [] books = {"자바의 정석", "혼자하는 자바", "혼자하는 C", "수학의 정석", "누구나 하는 C"};
		String word = "자바";
		int count = 0;
		for(int i = 0; i < books.length; i++) {
			if(books[i].contains(word)) {
				System.out.println(books[i]);
				count++;
			}
		}
		if(count == 0) {
			System.out.println("일치하는 책이 없습니다.");
		}
	}
}