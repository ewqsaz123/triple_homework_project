package com.triple.homework.point.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triple.homework.login.vo.UserVO;
import com.triple.homework.point.service.PointService;
import com.triple.homework.point.vo.PointHistoryVO;
import com.triple.homework.point.vo.PointVO;

@RestController
public class PointController {
	
	@Autowired
	PointService pointService;
	
	@RequestMapping(value="/mileage")
	public ResponseEntity<List<PointHistoryVO>> getMileageHistory(@RequestBody UserVO userVO){
		return new ResponseEntity<>(pointService.getMileageHistory(userVO), HttpStatus.OK);
	}
	
	@RequestMapping(value="/point")
	public ResponseEntity<PointVO> getPoint(@RequestBody PointVO pointVO){
		return new ResponseEntity<PointVO>(pointService.getPoint(pointVO), HttpStatus.OK);
	}

}
