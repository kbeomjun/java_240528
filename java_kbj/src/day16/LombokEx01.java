package day16;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class LombokEx01 {

	public static void main(String[] args) {
		Test t = new Test();
		t.setName("홍길동");
		t.setNumber("1");
		System.out.println(t);
		
		Test t2 = new Test("홍길동", "1");
		System.out.println(t.equals(t2));
		
		Test t3 = new Test("1");
		System.out.println(t3);
	}
}
/* @Data : @EqualsAndHashCode + @Getter + @Setter + @ToString */
@Data
@AllArgsConstructor // 모든 멤버변수를 필요로 하는 생성자
@NoArgsConstructor // 기본생성자
@RequiredArgsConstructor // @NonNull의 변수만 필요로 하는 생성자
class Test{
	private String name;
	@NonNull
	private String number;
	@Override
	public String toString() {
		return name+" : "+number;
	}
}