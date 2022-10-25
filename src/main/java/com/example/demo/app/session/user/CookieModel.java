package com.example.demo.app.session.user;

/**
 * クッキーモデル
 * @author nanai
 *
 */
public class CookieModel {

	/**
	 * クッキーログインユーザー
	 * {@link CookieLoginUser} 
	 */
	private CookieLoginUser cookieLoginUser;
	
	/** ----------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 */
	public CookieModel() {
		this.cookieLoginUser = new CookieLoginUser();
	}
	
	/** ----------------------------------------------------------------- */
	
	public CookieLoginUser getCookieLoginUser() {
		return this.cookieLoginUser;
	}
}
