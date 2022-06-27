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
public class PointHistoryVO extends BaseVO {
	private String historyId;
	private String userId;
	private String reviewId;
	private String action;
	private String type;
	private int value;
	private String userName;
	private String placeName;
	private String typeDesc;
	private String actionDesc;

}
