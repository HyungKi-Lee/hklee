package com.myBank.exception;

//�̸��� �ߺ�����
public class ExistUserEmailException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ExistUserEmailException(String message, String email) {
		super(email+"is"+message);
		
	}

	
}
