package com.myBank.exception;

//이메일 중복예외
public class ExistUserEmailException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ExistUserEmailException(String message, String email) {
		super(email+"is"+message);
		
	}

	
}
