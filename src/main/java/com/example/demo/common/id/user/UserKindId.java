package com.example.demo.common.id.user;

import com.example.demo.common.id.intro.IntroId;

/**
 * ユーザー種別クラス
 * @author nanai
 *
 */
public class UserKindId implements IntroId {

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
