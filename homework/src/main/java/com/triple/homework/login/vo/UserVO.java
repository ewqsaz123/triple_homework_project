package com.triple.homework.login.vo;

import com.triple.homework.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO extends BaseVO {
		private String userId;
		private String name;
}
