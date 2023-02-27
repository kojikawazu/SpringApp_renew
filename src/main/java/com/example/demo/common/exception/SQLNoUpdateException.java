package com.example.demo.common.exception;

/**
 * SQL更新エラーexception
 * @author nanai
 *
 */
public class SQLNoUpdateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SQLNoUpdateException(String message) {
		super(message);
	}

}
