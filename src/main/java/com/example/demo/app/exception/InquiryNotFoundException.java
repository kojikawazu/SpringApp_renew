package com.example.demo.app.exception;

public class InquiryNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InquiryNotFoundException(String message) {
		super(message);
	}
}
