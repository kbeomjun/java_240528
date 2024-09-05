package kr.kh.spring3.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommunityVO {
	int co_num;
	String co_name;
	int co_count;
	
	public CommunityVO(String name) {
		this.co_name = name;
	}
}