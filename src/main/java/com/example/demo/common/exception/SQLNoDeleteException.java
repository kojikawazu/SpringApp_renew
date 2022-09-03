package com.example.demo.common.exception;

/**
 * SQL削除エラーexception
 * @author nanai
 *
 */
public class SQLNoDeleteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SQLNoDeleteException(String message) {
		super(message);
	}
}
