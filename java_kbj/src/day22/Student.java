package day22;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* 학생의 성적을 관리하기 위한 클래스 */
@Data
@RequiredArgsConstructor
public class Student{
	@NonNull
	private int grade, classNum, num;
	@NonNull
	private String name;
	private List<Subject> subjectList = new ArrayList<Subject>();
	public void print() {
		System.out.println(grade+"학년 "+classNum+"반 "+num+"번 "+name);
		System.out.println("과목 성적");
		if(subjectList.size() == 0) {
			System.out.println("등록된 과목이 없습니다.");
			return;
		}
		for(Subject tmp : subjectList) {
			System.out.println(tmp);
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return classNum == other.classNum && grade == other.grade && num == other.num;
	}
	@Override
	public int hashCode() {
		return Objects.hash(classNum, grade, num);
	}
}