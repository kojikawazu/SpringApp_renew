package com.example.demo.common.exception;

/**
 * SQL追加エラーexception
 * <br>
 * extends {@link RuntimeException}
 * @author nanai
 *
 */
public class SQLNoInsertException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 * @param message {@link String}
	 */
	public SQLNoInsertException(String message) {
		super(message);
	}
}
