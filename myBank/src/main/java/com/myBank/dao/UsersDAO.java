package com.myBank.dao;

import java.util.Map;

import com.myBank.dto.Users;
import com.myBank.exception.ExistUserEmailException;
import com.myBank.exception.JoinFailException;

public interface UsersDAO {
	void joinUser(Users users) throws JoinFailException;
	String selectEmail(String email);
	Users checkLoginInfo(String email);
}
