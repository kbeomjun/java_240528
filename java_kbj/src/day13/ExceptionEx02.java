package day13;

public class ExceptionEx02 {

	public static void main(String[] args) {
		/* throws
		 * 예외를 직접 처리하지 않고, 해당 메서드를 호출한 메서드에게 예외처리를 맡김 
		 * RuntimeException은 throws 를 표시하지 않아도 됨 */
		int [] arr = null;
		try {
			printArray(arr);
		} catch (Exception e) {
			// e.getMessage() : 예외 클래스에 있는 message 값을 가져오는 메서드, 보통 생성자에서 넣은 문자열을 가져옴
			System.out.println(e.getMessage());
			// e.printStackTrace() : 예외가 발생한 경로를 순차적으로 콘솔에 출력
			e.printStackTrace();
		}
		
		try {
			test();
		} catch (MyException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void printArray(int [] arr) throws Exception {
		if(arr == null) {
			throw new Exception("배열이 생성되지 않았습니다.");
		}
		for(int tmp : arr) {
			System.out.println(tmp);
		}
	}
	
	public static void test() throws MyException {
		try {
			System.out.println(1/0);
		}catch(Exception e) {
			throw new MyException("0으로 나눌 수 없습니다.", "test");
		}
	}
}

/* 사용자 정의 예외 클래스 
 * 무조건 Exception 클래스를 상속 받아야 함 */
class MyException extends Exception{
	String who;
	public MyException(String message, String who) {
		super(message);
		this.who = who;
	}
	@Override
	public String getMessage() {
		return who+" : "+super.getMessage();
	}
}