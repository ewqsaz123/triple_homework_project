package com.triple.homework.review.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triple.homework.review.service.ReviewService;
import com.triple.homework.review.vo.PlaceVO;
import com.triple.homework.review.vo.ReviewVO;
import com.triple.homework.review.vo.ReviewWrapVO;

@RestController
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@RequestMapping(value="/places")
	public ResponseEntity<List<PlaceVO>> getAllPlaces(){
		return new ResponseEntity<>(reviewService.getAllPlaces(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/reviews")
	public ResponseEntity<List<ReviewVO>> getAllReviewByPlaceId(@RequestBody PlaceVO placeVO){
		return new ResponseEntity<>(reviewService.getAllReviewByPlaceId(placeVO), HttpStatus.OK);
	}
	
	@RequestMapping(value="/review")
	public ResponseEntity<List<ReviewVO>> getReviewByReviewId(ReviewVO reviewVO){
		
		return new ResponseEntity<>(reviewService.getReviewByReviewId(reviewVO), HttpStatus.OK);
	}
	
	@RequestMapping(value="/events")
	public ResponseEntity<String> handleEvent(@RequestBody ReviewVO reviewVO){
		return new ResponseEntity<>(reviewService.handleEvent(reviewVO), HttpStatus.OK);
	}


}
