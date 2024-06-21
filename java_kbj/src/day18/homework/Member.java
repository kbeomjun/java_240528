package day18.homework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member implements Serializable {
	private static final long serialVersionUID = -1365636226625753722L;
	private String id;
	private String name;
	private List<Schedule> slist = new ArrayList<Schedule>();
}