package com.triple.homework.review.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triple.homework.point.service.PointService;
import com.triple.homework.point.vo.PointHistoryVO;
import com.triple.homework.point.vo.PointVO;
import com.triple.homework.review.mapper.ReviewMapper;
import com.triple.homework.review.vo.PlaceVO;
import com.triple.homework.review.vo.ReviewVO;

@Service
public class ReviewService {
	
	@Autowired
	ReviewMapper reviewMapper;
	
	@Autowired
	PointService pointService;
	
	public List<PlaceVO> getAllPlaces(){
		return reviewMapper.getAllPlaces();
	}

	
	public List<ReviewVO> getAllReviewByPlaceId(PlaceVO placeVO){
		return reviewMapper.getAllReviewByPlaceId(placeVO);
	}
	
	public List<ReviewVO> getReviewByReviewId(ReviewVO reviewVO){
		return reviewMapper.getReviewByReviewId(reviewVO);
	}
	
	public String handleEvent(ReviewVO reviewVO) {
		try {
			//리뷰 이벤트
			if("REVIEW".equals(reviewVO.getType())) {
				//리뷰 생성
				if("ADD".equals(reviewVO.getAction())) {
					
					/* 리뷰 row 추가 */
					reviewVO.setAttachedPhotoIdsString(String.join(", ", reviewVO.getAttachedPhotoIds()));
					reviewMapper.insertReview(reviewVO);
					
					/* 마일리지 적립 */
					int totalPoint = 0;
					List<PointHistoryVO> pointHistory = new ArrayList<>();
					
					//1자 이상 텍스트 작성. "CONTENT_TEXT"
					if(reviewVO.getContent().length() >= 1) {
						PointHistoryVO hisVO = pointService.getPointHistory("CONTENT_TEXT", "PLUS", reviewVO);
						totalPoint += hisVO.getValue();
						pointHistory.add(hisVO);
					}
					
					//1자 이상 사진 첨부. "CONTENT_PHOTO"
					if(reviewVO.getAttachedPhotoIds().length >= 1) {
						PointHistoryVO hisVO = pointService.getPointHistory("CONTENT_PHOTO","PLUS", reviewVO);
						totalPoint += hisVO.getValue();
						pointHistory.add(hisVO);
					}
					
					//특정 장소에 첫 리뷰 작성. "BONUS_FIRST"
					if(reviewMapper.getCountReviewByPlaceId(reviewVO) == 1) {
						PointHistoryVO hisVO = pointService.getPointHistory("BONUS_FIRST","PLUS", reviewVO);
						totalPoint += hisVO.getValue();
						pointHistory.add(hisVO);
					}
					
					
					//히스토리 추가 & 현재 포인트 변경
					if(pointHistory.size() > 0) {
						for(PointHistoryVO vo: pointHistory) pointService.insertPointHistory(vo);
						
						PointVO pVO = new PointVO();
						pVO.setUserId(reviewVO.getUserId());
						pVO.setValue(pointService.getPoint(pVO).getValue()+totalPoint);
						pVO.setChgId(reviewVO.getUserId());
						pointService.updatePoint(pVO);
					}
				}
				
				
				//리뷰 삭제
				if("DELETE".equals(reviewVO.getAction())) {
					
					/* 리뷰row 삭제처리 */
					reviewMapper.deleteReview(reviewVO);
					/* 마일리지 회수 */
					int totalPoint = 0;
					List<PointHistoryVO> newHistory = new ArrayList<>();
					List<PointHistoryVO> currentHistory = pointService.getPointHistoryByUserId(reviewVO);
					//리뷰로 받은 포인트 이력 조회
					//하나씩검사하면서 타입 검사 
					for(PointHistoryVO vo : currentHistory) {
						vo.setAction("MINUS");
						newHistory.add(vo);
						totalPoint-= vo.getValue();
					}
					//히스토리 추가 & 현재 포인트 변경 
					if(newHistory.size() > 0) {
						for(PointHistoryVO vo:newHistory) pointService.insertPointHistory(vo);
						PointVO pVO = new PointVO();
						pVO.setUserId(reviewVO.getUserId());
						pVO.setValue(pointService.getPoint(pVO).getValue()+totalPoint);
						pVO.setChgId(reviewVO.getUserId());
						pointService.updatePoint(pVO);
					}
					
				}
				
				//리뷰 수
				if("MOD".equals(reviewVO.getAction())) {
					/* 리뷰 row 수정 */
					reviewVO.setAttachedPhotoIdsString(String.join(", ", reviewVO.getAttachedPhotoIds()));
					reviewMapper.updateReview(reviewVO);
					
					/* 마일리지 회수 */
					int totalPoint = 0;
					List<PointHistoryVO> newHistory = new ArrayList<>();
					List<PointHistoryVO> currentHistory = pointService.getPointHistoryByUserId(reviewVO);
					//리뷰로 받은 포인트 이력 조회
					//하나씩검사하면서 타입별로 조건 검사
					List<String> contextType=new ArrayList<>();
					contextType.add("CONTENT_TEXT"); contextType.add("CONTENT_PHOTO");
					for(PointHistoryVO vo : currentHistory) {
						if("CONTENT_TEXT".equals(vo.getType())){
							contextType.remove(contextType.indexOf(vo.getType()));
							if(reviewVO.getContent().length() < 1) {	//포인트 회수
							PointHistoryVO hisVO = pointService.getPointHistory("CONTENT_TEXT","MINUS", reviewVO);
							hisVO.setValue(vo.getValue());
							totalPoint -= vo.getValue();
							newHistory.add(hisVO);
							}
						}
						if("CONTENT_PHOTO".equals(vo.getType())) {
							contextType.remove(contextType.indexOf(vo.getType()));
							if(reviewVO.getAttachedPhotoIds().length < 1) {
								PointHistoryVO hisVO = pointService.getPointHistory("CONTENT_PHOTO","MINUS", reviewVO);
								hisVO.setValue(vo.getValue());
								totalPoint -= vo.getValue();
								newHistory.add(hisVO);
							}
						}
						
					}
					
					/* 빠졌던 조건이 수정 시 추가됐는지 확인 */
					for(String type: contextType) {
						if("CONTENT_TEXT".equals(type) && reviewVO.getContent().length() >= 1) {
							PointHistoryVO hisVO = pointService.getPointHistory(type,"PLUS", reviewVO);
							totalPoint += hisVO.getValue();
							newHistory.add(hisVO);
						}
						if("CONTENT_PHOTO".equals(type) && reviewVO.getAttachedPhotoIds().length >= 1) {
							PointHistoryVO hisVO = pointService.getPointHistory(type,"PLUS", reviewVO);
							totalPoint += hisVO.getValue();
							newHistory.add(hisVO);
						}
					}
					
					//히스토리 추가 & 현재 포인트 변경 
					if(newHistory.size() > 0) {
						for(PointHistoryVO vo:newHistory) pointService.insertPointHistory(vo);
						PointVO pVO = new PointVO();
						pVO.setUserId(reviewVO.getUserId());
						pVO.setValue(pointService.getPoint(pVO).getValue()+totalPoint);
						pVO.setChgId(reviewVO.getUserId());
						pointService.updatePoint(pVO);
					}
					
					
				}
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "";
		}
		
		return "SUCCESS";
	}
}
