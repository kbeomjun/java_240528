package day13;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateEx01 {

	public static void main(String[] args) throws ParseException {
		/* Date 클래스
		 * 기본 생성자만 주로 사용, 기본 생성자 이외의 생성자는 대부분 Deprecated(비권장) 됨
		 * */
		
		// 현재 날짜를 객체로 가져옴
		Date date = new Date();
		System.out.println(date);
		
		// 날짜 => 문자열
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr1 = format1.format(date);
		System.out.println(dateStr1);
		
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr2 = format2.format(date);
		System.out.println(dateStr2);
		
		SimpleDateFormat format3 = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String dateStr3 = format3.format(date);
		System.out.println(dateStr3);
		
		// 문자열 => 날짜
		String dateStr4 = "2024-06-14 09:22";
		SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date4 = format4.parse(dateStr4);
		System.out.println(date4);
		
		// Date => Calendar
		Date date5 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date5);
		System.out.println(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH));
	}
}