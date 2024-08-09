package kr.kh.app.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostVO {
	private int po_num;
	private String po_title;
	private String po_content;
	private String po_me_id;
	private int po_co_num;
	private String po_date;
	private int po_views; 
	private int po_report;
}