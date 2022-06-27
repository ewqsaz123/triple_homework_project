package com.triple.homework.point.vo;

import com.triple.homework.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointVO extends BaseVO {
	
	private String pointId;
	private String userId;
	private int value;
	

}
