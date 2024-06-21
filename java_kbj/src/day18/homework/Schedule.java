package day18.homework;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule implements Serializable {
	private static final long serialVersionUID = -5064040074128226529L;
	private String date;
	private String hour;
	private String schedule;
	private String detail;
	@Override
	public String toString() {
		return date+" "+hour+" "+schedule+" "+detail;
	}
}