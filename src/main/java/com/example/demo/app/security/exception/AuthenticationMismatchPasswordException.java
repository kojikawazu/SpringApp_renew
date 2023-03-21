package com.example.demo.app.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 認証Exceptionクラス<br>
 * (パスワード不一致)
 * extends {@link AuthenticationException}
 * @author nanai
 *
 */
public class AuthenticationMismatchPasswordException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 * @param msg {@link String}
	 */
	public AuthenticationMismatchPasswordException(String msg) {
		super(msg);
	}

	/**
	 * コンストラクタ
	 * @param msg 		{@link String}
	 * @param cause 	{@link Throwable}
	 */
	public AuthenticationMismatchPasswordException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
