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
public class RewardVO extends BaseVO {
	private String action;
	private String type;
	private String useYn;
	private int value;
}
