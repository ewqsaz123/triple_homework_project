package com.triple.homework.point.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triple.homework.login.vo.UserVO;
import com.triple.homework.point.vo.PointHistoryVO;
import com.triple.homework.point.vo.PointVO;
import com.triple.homework.point.vo.RewardVO;
import com.triple.homework.review.vo.ReviewVO;

@Mapper
public interface PointMapper {
	RewardVO getRewardValue(RewardVO rewardVO);
	void insertPointHistory(PointHistoryVO pointHistoryVO);
	PointVO getPoint(PointVO pointVO);
	void updatePoint(PointVO pointVO);
	List<PointHistoryVO> getPointHistoryByUserId(ReviewVO reviewVO);
	List<PointHistoryVO>getMileageHistory (UserVO userVO);
}
