package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6671141496653939468L;

	public UserNotFoundException() {
		super();
	}
	public UserNotFoundException(String message) {
		super(message);
	}

}
