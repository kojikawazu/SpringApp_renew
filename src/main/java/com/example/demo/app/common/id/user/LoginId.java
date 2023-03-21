package com.example.demo.app.common.id.user;

import com.example.demo.common.id.NormalId;

/**
 * ログインIDクラス
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class LoginId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public LoginId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public LoginId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link LoginId}
	 */
	public void setId(LoginId id) {
		if (id == null)	return ;
		this.id = id.getId();
	}
}
