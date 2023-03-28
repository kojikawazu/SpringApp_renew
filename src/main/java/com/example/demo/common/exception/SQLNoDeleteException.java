package com.example.demo.common.exception;

/**
 * SQL削除エラーexception
 * <br>
 * extends {@link RuntimeException}
 * @author nanai
 *
 */
public class SQLNoDeleteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 * @param message {@link String}
	 */
	public SQLNoDeleteException(String message) {
		super(message);
	}
}
