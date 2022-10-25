package com.example.demo.app.header.response;

/**
 * ユーザーログインのレスポンスクラス
 * @author nanai
 *
 */
public class UserLoginResponse {

	/** メッセージ */
	private String	message;
	
	/** ユーザー名 */
	private String	userName;
	
	/** ----------------------------------------------------------------------------*/
	
	/**
	 * コンストラクタ
	 */
	public UserLoginResponse() {
		this.message	= "";
		this.userName	= "";
	}
	
	/**
	 * コンストラクタ
	 * @param message		結果
	 * @param userName		ユーザー名
	 */
	public UserLoginResponse(
			String message,
			String userName) {
		this.message	= message;
		this.userName	= userName;
	}
	
	/** ----------------------------------------------------------------------------*/
	
	public String getMessage() {
		return this.message;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
}
