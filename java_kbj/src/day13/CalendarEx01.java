package day13;

import java.util.Calendar;
import java.util.Date;

public class CalendarEx01 {

	public static void main(String[] args) {
		/* Calendar 클래스
		 * 달력을 표현한 클래스
		 * new 연산자로 객체 생성을 하지 않음, getInstance()를 이용하여 객체를 가져옴
		 * 년, 월, 일, 시, 분, 초, 요일 등의 다양한 정보를 쉽게 가져올 수 있음
		 * 그냥 날짜를 저장해서 관리할 땐 Date, 월이나 시, 분, 초 등 다양한 값을 가져와야 할 땐 Calendar
		 * */
		
		Calendar cal = Calendar.getInstance();
		
		// 연도를 추출
		int year = cal.get(Calendar.YEAR);
		// 월을 추출 (0~11)
		int month = cal.get(Calendar.MONTH);
		// 일을 추출
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		System.out.println(year+"-"+(month+1)+"-"+day);
		
		// Calendar => Date
		Calendar cal2 = Calendar.getInstance();
		Date date = new Date(cal2.getTimeInMillis()); // 1970년 1월 1일 0시 0분 0초를 기준으로 흐른 밀리초를 날짜로 계산
		System.out.println(date);
	}
}