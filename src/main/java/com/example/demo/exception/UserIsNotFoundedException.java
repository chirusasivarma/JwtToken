package com.example.demo.exception;

public class UserIsNotFoundedException extends RuntimeException {
	
	private String message;

	public UserIsNotFoundedException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
