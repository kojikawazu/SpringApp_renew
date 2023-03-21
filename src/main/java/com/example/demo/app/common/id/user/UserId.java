package com.example.demo.app.common.id.user;

import com.example.demo.common.id.NormalId;

/**
 * ユーザーIDクラス
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class UserId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public UserId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public UserId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link UserId}
	 */
	public void setId(UserId id) {
		this.id = id.getId();
	}
}
