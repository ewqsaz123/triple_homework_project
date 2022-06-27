package com.triple.homework.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triple.homework.review.vo.PlaceVO;
import com.triple.homework.review.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	
	List<PlaceVO> getAllPlaces();
	List<ReviewVO> getAllReviewByPlaceId(PlaceVO placeVO);
	List<ReviewVO> getReviewByReviewId(ReviewVO reviewVO);
	void insertReview(ReviewVO reviewVO);
	int getCountReviewByPlaceId(ReviewVO reviewVO);
	void deleteReview(ReviewVO reviewVO);
	void updateReview(ReviewVO reviewVO);
}
