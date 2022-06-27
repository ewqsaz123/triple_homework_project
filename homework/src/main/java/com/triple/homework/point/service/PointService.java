package com.triple.homework.point.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triple.homework.login.vo.UserVO;
import com.triple.homework.point.mapper.PointMapper;
import com.triple.homework.point.vo.PointHistoryVO;
import com.triple.homework.point.vo.PointVO;
import com.triple.homework.point.vo.RewardVO;
import com.triple.homework.review.vo.ReviewVO;

@Service
public class PointService {
	
	@Autowired
	PointMapper pointMapper;
	
	public PointHistoryVO getPointHistory(String type, String action, ReviewVO reviewVO) {
		RewardVO rVO = new RewardVO();
		rVO.setType(type);
		rVO.setValue(pointMapper.getRewardValue(rVO).getValue());
		rVO.setAction(action);
		
		PointHistoryVO hisVO = new PointHistoryVO();
		hisVO.setReviewId(reviewVO.getReviewId());
		hisVO.setUserId(reviewVO.getUserId());
		hisVO.setValue(rVO.getValue());
		hisVO.setType(rVO.getType());
		hisVO.setAction(rVO.getAction());
		hisVO.setRgstId(reviewVO.getUserId());
		
		return hisVO;
	}
	
	public void insertPointHistory(PointHistoryVO pointHistoryVO) {
		pointMapper.insertPointHistory(pointHistoryVO);
		return;
	}
	
	public PointVO getPoint(PointVO pointVO) {
		return pointMapper.getPoint(pointVO);
	}
	
	public void updatePoint(PointVO pointVO) {
		pointMapper.updatePoint(pointVO);
		return;
	}
	
	public List<PointHistoryVO> getPointHistoryByUserId(ReviewVO reviewVO) {
		return pointMapper.getPointHistoryByUserId(reviewVO);
	}
	public List<PointHistoryVO> getMileageHistory(UserVO userVO){
		return pointMapper.getMileageHistory(userVO);
	}
	

}
