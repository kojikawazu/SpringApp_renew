package com.example.demo.app.header.form;

import javax.validation.constraints.NotNull;

/**
 * ユーザーログインフォーム
 * @author nanai
 *
 */
public class UserLoginForm {

	/** ユーザー名 */
	@NotNull
	private String 	name;
	
	/** パスワード */
	@NotNull
	private String 	password;
	
	/** -------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 */
	public UserLoginForm() {
		this.name		= "";
		this.password	= "";
	}
	
	/** -------------------------------------------------------------- */
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
}
