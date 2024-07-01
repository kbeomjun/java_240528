package day17;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharStreamEx01 {

	public static void main(String[] args) {
		String fileName = "src/day17/byteStream.txt";
		try(FileReader fr = new FileReader(fileName)){
			while(fr.ready()) {
				char ch = (char)fr.read();
				System.out.print(ch);
			}
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("파일 입출력에서 예외가 발생했습니다.");
		}
		
		/* 파일을 이어 쓰려면 뒤에 true를 추가 */
		String fileName2 = "src/day17/charStream.txt";
		try(FileWriter fw = new FileWriter(fileName2, true)){
			String str = "안녕하세요. 제 이름은 홍길동입니다.\n만나서 반갑습니다.";
			fw.write(str);
		} catch (IOException e) {
			System.out.println("파일 입출력 예외가 발생했습니다.");
		}
	}
}