package com.example.demo.app.common.id.user;

import com.example.demo.common.id.SuperId;

/**
 * ユーザー種別クラス
 * @author nanai
 *
 */
public class UserKindId implements SuperId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public UserKindId(int id) {
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
