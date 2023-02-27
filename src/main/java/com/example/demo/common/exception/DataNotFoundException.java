package com.example.demo.common.exception;

/**
 * Dataないエラークラス
 * @author nanai
 *
 */
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message) {
		super(message);
	}
}
