package com.example.demo.app.header.form;

import javax.validation.constraints.NotNull;

/**
 * ユーザーログインフォーム
 * @author nanai
 *
 */
public class UserLoginForm {

	@NotNull
	private String name;
	
	@NotNull
	private String password;
	
	/**
	 * コンストラクタ
	 */
	public UserLoginForm() {
		
	}
	
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
