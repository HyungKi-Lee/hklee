package com.myBank.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myBank.dto.Users;
import com.myBank.exception.ExistUserEmailException;
import com.myBank.exception.JoinFailException;
import com.myBank.mapper.UsersMapper;

@Repository
public class UsersDAOImpl implements UsersDAO {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public void joinUser(Users users)throws JoinFailException {
		sqlSession.getMapper(UsersMapper.class).joinUser(users);
	}

	@Override
	public String selectEmail(String email) {
		return sqlSession.getMapper(UsersMapper.class).selectEmail(email);
	}

	@Override
	public Users checkLoginInfo(String email) {
		return sqlSession.getMapper(UsersMapper.class).checkLoginInfo(email);
	}

}
