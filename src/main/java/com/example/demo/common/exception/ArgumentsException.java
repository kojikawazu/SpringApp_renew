package com.example.demo.common.exception;

/**
 * 引数エラーexception
 * @author nanai
 *
 */
public class ArgumentsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ArgumentsException(String message) {
		super(message);
	}
}
