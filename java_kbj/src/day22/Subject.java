package day22;


import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subject {
	private String name;
	private int grade;
	private int semester;
	private int midterm;
	private int endterm;
	private int performance;
	@Override
	public String toString() {
		return grade+"학년 "+semester+"학기 "+name+" 중간 : "+midterm+", 기말 : "+endterm+", 수행평가 : "+performance;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return grade == other.grade && Objects.equals(name, other.name) && semester == other.semester;
	}
	@Override
	public int hashCode() {
		return Objects.hash(grade, name, semester);
	}
}