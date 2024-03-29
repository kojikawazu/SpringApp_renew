package com.example.demo.common.exception;

/**
 * 引数エラーexception
 * <br>
 * extends {@link RuntimeException}
 * @author nanai
 *
 */
public class ArgumentsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 * @param message {@link String}
	 */
	public ArgumentsException(String message) {
		super(message);
	}
}
