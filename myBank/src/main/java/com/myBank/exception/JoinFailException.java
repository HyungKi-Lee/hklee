package com.myBank.exception;

//회원가입 실패시 발생할 예외 설정
public class JoinFailException extends Exception {
	private static final long serialVersionUID = 1L;

	public JoinFailException(String message) {
		super(message);
	}
	
	
}
