package kr.kh.app.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {
	private String me_id; 
	private String me_pw; 
	private String me_email; 
	private String me_authority; 
	private int me_fail; 
	private String me_cookie; 
	private Date me_limit; 
	private int me_report; 
	private String me_ms_name; 
	private Date me_stop;

	public MemberVO(String me_id, String me_pw, String me_email) {
		this.me_id = me_id;
		this.me_pw = me_pw;
		this.me_email = me_email;
	}

	public MemberVO(String me_id, String me_pw) {
		this.me_id = me_id;
		this.me_pw = me_pw;
	}
}