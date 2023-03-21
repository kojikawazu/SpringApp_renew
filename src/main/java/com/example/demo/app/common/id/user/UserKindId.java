package com.example.demo.app.common.id.user;

import com.example.demo.common.id.NormalId;

/**
 * ユーザー種別クラス
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class UserKindId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public UserKindId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public UserKindId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link UserKindId}
	 */
	public void setId(UserKindId id) {
		if (id == null)	return;
		this.id = id.getId();
	}
}
