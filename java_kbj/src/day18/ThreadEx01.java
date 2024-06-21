package day18;

public class ThreadEx01 {

	public static void main(String[] args) {
		/* Thread 클래스 */
		MyThread1 thread1 = new MyThread1();
		thread1.start();
		for(int i = 0; i < 10000; i++) {
			System.out.print("|");
		}
		
		/* Runnable 인터페이스를 이용한 쓰레드 생성 예제 */
		MyThread2 thread2 = new MyThread2();
		Thread th = new Thread(thread2);
		th.start();
		for(int i = 0; i < 10000; i++) {
			System.out.print("|");
		}
	}
}

class MyThread1 extends Thread{
	@Override
	public void run() {
		for(int i = 0; i < 10000; i++) {
			System.out.print("-");
		}
	}
}

class MyThread2 implements Runnable{
	@Override
	public void run() {
		for(int i = 0; i < 10000; i++) {
			System.out.print("-");
		}
	}
}