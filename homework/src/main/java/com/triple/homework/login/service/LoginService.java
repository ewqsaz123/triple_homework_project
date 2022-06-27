package com.triple.homework.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triple.homework.login.mapper.LoginMapper;
import com.triple.homework.login.vo.UserVO;

@Service
public class LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	
	public List<UserVO> getAllUsers(){
		return loginMapper.getAllUsers();
	}
	
	public int getUser(UserVO userVO) {
		return loginMapper.getUser(userVO);
	}
	
	public UserVO getUserById(UserVO userVO) {
		return loginMapper.getUserById(userVO);
	}
}
