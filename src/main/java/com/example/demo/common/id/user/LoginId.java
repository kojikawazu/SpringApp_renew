package com.example.demo.common.id.user;

import com.example.demo.common.id.common.SuperId;

/**
 * ログインIDクラス
 * @author nanai
 *
 */
public class LoginId implements SuperId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public LoginId(int id) {
		this.id = id;
	}

	/**
	 * getter
	 * @return id
	 */
	@Override
	public int getId() {
		return this.id;
	}
}
