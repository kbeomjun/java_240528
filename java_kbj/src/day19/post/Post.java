package day19.post;

import java.io.Serializable;
import java.util.Objects;
import lombok.Data;

@Data
public class Post implements Serializable {
	private static final long serialVersionUID = 6855659071289853310L;
	private String title;
	private String content;
	private String id;
	private String password;
	private int view;
	private int num;
	public Post(String title, String content, String id, String password, int view) {
		this.title = title;
		this.content = content;
		this.id = id;
		this.password = password;
		this.view = view;
		this.num = 1;
	}
	@Override
	public String toString() {
		return num+". "+title+" / "+id+" / "+view;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return num == other.num;
	}
	@Override
	public int hashCode() {
		return Objects.hash(num);
	}
}