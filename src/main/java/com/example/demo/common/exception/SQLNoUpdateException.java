package com.example.demo.common.exception;

/**
 * SQL更新エラーexception
 * <br>
 * extends {@link RuntimeException}
 * @author nanai
 *
 */
public class SQLNoUpdateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 * @param message {@link String}
	 */
	public SQLNoUpdateException(String message) {
		super(message);
	}
}
