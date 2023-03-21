package com.example.demo.app.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 認証Exceptionクラス<br>
 * (引数エラー)
 * extends {@link AuthenticationException}
 * @author nanai
 *
 */
public class AutenticationArgumentsException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 * @param msg {@link String}
	 */
	public AutenticationArgumentsException(String msg) {
		super(msg);
	}

	/**
	 * コンストラクタ
	 * @param msg 		{@link String}
	 * @param cause 	{@link Throwable}
	 */
	public AutenticationArgumentsException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
