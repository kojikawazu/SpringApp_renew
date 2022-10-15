package com.example.demo.common.id.user;

import com.example.demo.common.id.intro.IntroId;

/**
 * ユーザーIDクラス
 * @author nanai
 *
 */
public class UserId implements IntroId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public UserId(int id) {
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
