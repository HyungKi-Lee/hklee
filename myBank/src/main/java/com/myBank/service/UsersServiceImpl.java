package com.myBank.service;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myBank.dao.UsersDAO;
import com.myBank.dto.Users;
import com.myBank.exception.ExistUserEmailException;
import com.myBank.exception.JoinFailException;
import com.myBank.util.KakaoLogin;
import com.myBank.util.OpenBank;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersDAO usersDAO;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	//PasswordEncoder passwordEncoder;
	
	@Override
	public void joinUser(Users users)throws JoinFailException {
		if(users.getPassword()==null || users.getPassword().equals("")) {
			new JoinFailException("Password is Null");
			return;
		}
		String encodedpw = passwordEncoder.encode(users.getPassword());
		users.setPassword(encodedpw);
		//System.out.println("encodedpw = "+encodedpw);
		usersDAO.joinUser(users);
	}

	@Override
	public String selectEmail(String email) {
		return usersDAO.selectEmail(email);
	}

	@Override
	public Users checkLoginInfo(String email) {
		//String encodedpw = passwordEncoder.encode((CharSequence) map.get("password"));
		//String getPw = (String) map.get("password");
		//String encodedpw = passwordEncoder.encode(getPw);
		
		//map.remove("password");
		//map.put("password", encodedpw);
		return usersDAO.checkLoginInfo(email);
	}
	
	// *** 카카오 로그인 토근발급(미완) ***
	@Override
	public void getAccessKakaoToken(String authorizationCode) {
		try {
			KakaoLogin.getAccessToken(authorizationCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// *** 오픈뱅킹 api 인증 *** //
	@Override
	public String getOauth(Properties prop) {
		try {
			return OpenBank.getOAuth(prop);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void getAccessBankToken(String authorizationCode) {
		
	}

}
