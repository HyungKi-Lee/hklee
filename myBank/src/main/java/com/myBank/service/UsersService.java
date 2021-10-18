package com.myBank.service;

import java.util.Map;

import com.myBank.dto.Users;
import com.myBank.exception.JoinFailException;

public interface UsersService {
	void joinUser(Users users) throws JoinFailException;
	String selectEmail(String email);
	Users checkLoginInfo(String email);
	void getAccessToken(String authorizationCode);
}
