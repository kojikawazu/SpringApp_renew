package com.example.demo.app.exception;

public class WebMvcConfig {
	
	public static String EXCEPTION_NOTFOUND = "Can't find the same ID";

	public static InquiryNotFoundException NOT_FOUND() {
		return new InquiryNotFoundException(WebMvcConfig.EXCEPTION_NOTFOUND);
	}
}
