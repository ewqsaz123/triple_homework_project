package com.triple.homework.login.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triple.homework.SessionConstants;
import com.triple.homework.login.service.LoginService;
import com.triple.homework.login.vo.UserVO;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	

	@RequestMapping(value="/getUsers")
	public List<UserVO> getUsers() {
		return loginService.getAllUsers();
	}
	
	
	
	@RequestMapping(value="/login")
	public ResponseEntity<String> login(@RequestBody UserVO userVO, BindingResult bindingresult, HttpSession session) {
		System.out.println(userVO.getUserId());
		int cnt = loginService.getUser(userVO);
		if(cnt != 1) {
			bindingresult.reject("존재하지 않는 사용자입니다.");
			System.out.println("*********로그인 실패***********"+cnt+","+userVO.getUserId());
			return new ResponseEntity<>("FAIL", HttpStatus.OK);
		}
		
		//로그인 성공 처리
		session.setAttribute(SessionConstants.LOGIN_USER, userVO.getUserId());
		System.out.println(userVO.getUserId()+"*********로그인 성공***********");
		
		return new ResponseEntity<>(userVO.getUserId(),HttpStatus.OK);
	}
	
	@RequestMapping(value="logout")
	public ResponseEntity<String> logout(HttpSession session){
		if(session != null) {
			session.invalidate();
		}
		System.out.println("*********로그아웃 성공***********");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="IsLogIn")
	public ResponseEntity<String> IsLogIn(HttpSession session){
		if(session.getAttribute(SessionConstants.LOGIN_USER) != null) {
			return new ResponseEntity<>("Y", HttpStatus.OK);
		}else return new ResponseEntity<>("N", HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/loginUser")
	public ResponseEntity<UserVO> getLoginUser(HttpSession session){
		if(session.getAttribute(SessionConstants.LOGIN_USER) == null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		UserVO vo = new UserVO();
		vo.setUserId((String)session.getAttribute(SessionConstants.LOGIN_USER));
		return new ResponseEntity<>(loginService.getUserById(vo),HttpStatus.OK);
	}

}
