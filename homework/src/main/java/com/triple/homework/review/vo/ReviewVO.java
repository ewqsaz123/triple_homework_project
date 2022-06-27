package com.triple.homework.review.vo;

import java.util.List;

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
public class ReviewVO extends BaseVO{
	private String type;
	private String action;
	private String reviewId;
	private String name;
	private String userId;
	private String content;
	private String[] attachedPhotoIds;
	private String attachedPhotoIdsString;
	private String placeId;
	private String placeName;
	private String userName;
	private String deleteYn;
}
