package com.triple.homework.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.triple.homework.login.vo.UserVO;

@Mapper
public interface LoginMapper {
	List<UserVO> getAllUsers();
	int getUser(UserVO userVO);
	UserVO getUserById(UserVO userVO);
	
	
}
