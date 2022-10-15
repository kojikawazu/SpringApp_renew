package com.example.demo.app.header.form;

/**
 * ヘッダーフォーム
 * @author nanai
 *
 */
public class HeaderForm {

	/** ユーザーログインフォーム */
	private UserLoginForm userLoginForm;
	
	/**
	 * コンストラクタ
	 * 
	 */
	public HeaderForm() {
		userLoginForm = new UserLoginForm();
	}
	
	/** getter */
	
	public UserLoginForm getUserLoginForm() {
		return this.userLoginForm;
	}
	
}
