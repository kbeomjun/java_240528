package kr.kh.app.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Criteria {
	protected int page = 1;
	protected int perPageNum = 5;
	protected String search = "";
	
	public Criteria(int page, int perPageNum, String search) {
		this.page = page;
		this.perPageNum = perPageNum;
		this.search = search;
	}
	
	public int getPageStart() {
		return (page - 1) * perPageNum;
	}
}