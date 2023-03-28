package com.example.demo.common.exception;

/**
 * Dataないエラークラス
 * <br>
 * extends {@link RuntimeException}
 * @author nanai
 *
 */
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 * @param message {@link String}
	 */
	public DataNotFoundException(String message) {
		super(message);
	}
}
