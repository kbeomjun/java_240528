package day21.socket2;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact implements Serializable, Comparable<Contact> {
	private static final long serialVersionUID = -2787196840194464462L;
	private String name;
	private String number;
	@Override
	public String toString() {
		return name+" : "+number;
	}
	@Override
	public int compareTo(Contact o) {
		return name.compareTo(o.name);
	}
}