package com.myBank.mapper;

import java.util.Map;

import com.myBank.dto.Users;
import com.myBank.exception.ExistUserEmailException;
import com.myBank.exception.JoinFailException;

public interface UsersMapper {
	//회원가입
	void joinUser(Users users) throws JoinFailException;
	String selectEmail(String email);
	Users checkLoginInfo(String email);
}
