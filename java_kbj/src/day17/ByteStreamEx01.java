package day17;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamEx01 {

	public static void main(String[] args) {
		/* 바이트 기반 입력 스트림 예제 */
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("src/day17/byteStream.txt");
			int data;
			do {
				data = fis.read();
				System.out.print((char)data);
			}while(data != -1);
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("파일 읽기에 실패했습니다.");
		} finally {
			try {
				if(fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("파일을 닫을 수 없습니다.");
			}
		}
		
		/* 바이트 기반 출력 스트림 예제 */
		try(FileOutputStream fos = new FileOutputStream("src/day17/byteStream2.txt")) {
			for(char ch = 'A'; ch <= 'Z'; ch++) {
				fos.write(ch);
			}
			System.out.println();
			System.out.println("완료되었습니다.");
		} catch (FileNotFoundException e) {
			System.out.println("파일이 없거나 파일을 생성할 수 없습니다.");
		} catch (IOException e) {
			System.out.println("예외가 발생했습니다.");
		}
	}
}