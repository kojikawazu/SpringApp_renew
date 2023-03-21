package com.example.demo.app.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 認証Exceptionクラス<br>
 * (既にユーザーがいる)
 * extends {@link AuthenticationException}
 * @author nanai
 *
 */
public class AuthenticationLoginedUserException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 * @param msg {@link String}
	 */
	public AuthenticationLoginedUserException(String msg) {
		super(msg);
	}

	/**
	 * コンストラクタ
	 * @param msg 		{@link String}
	 * @param cause 	{@link Throwable}
	 */
	public AuthenticationLoginedUserException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
