package com.example.demo.app.header.form;

/**
 * ユーザーログアウトフォーム
 * @author nanai
 *
 */
public class UserLogoutForm {
	
	/** ログインID */
	private int nowLogoutId;
	
	/** ------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 */
	public UserLogoutForm() {
		this.nowLogoutId = 0;
	}
	
	/** ------------------------------------------------------- */
	
	public int getNowLogoutId() {
		return this.nowLogoutId;
	}
	
	public void setNowLogoutId(int nowLogoutId) {
		this.nowLogoutId = nowLogoutId;
	}
	
}
