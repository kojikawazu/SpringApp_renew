package com.example.demo.app.header.form;

/**
 * ヘッダーフォーム
 * @author nanai
 *
 */
public class HeaderForm {

	/** 
	 * ユーザーログインフォーム
	 * {@link UserLoginForm}
	 */
	private UserLoginForm	userLoginForm;
	
	/** 
	 * ユーザーログアウトフォーム
	 * {@link UserLogoutForm}
	 */
	private UserLogoutForm	userLogoutForm;
	
	/** ----------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * 
	 */
	public HeaderForm() {
		if (userLoginForm == null) {
			userLoginForm	= new UserLoginForm();
		}
		if (userLogoutForm == null) {
			userLogoutForm	= new UserLogoutForm();
		}
	}
	
	/** ----------------------------------------------------- */
	
	/** getter */
	
	public UserLoginForm getUserLoginForm() {
		return this.userLoginForm;
	}
	
	public UserLogoutForm getUserLogoutForm() {
		return this.userLogoutForm;
	}
	
}
