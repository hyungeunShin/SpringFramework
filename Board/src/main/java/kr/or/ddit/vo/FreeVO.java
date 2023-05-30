package kr.or.ddit.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FreeVO {
	private int boNo;
	private String boTitle;
	private String boWriter;
	private String boContent;
	private String boDate;
	private int boHit;
}
