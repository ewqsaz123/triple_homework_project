package com.triple.homework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BaseVO {
	
	private String rgstDtm;
	private String rgstId;
	private String chgDtm;
	private String chgId;

}
