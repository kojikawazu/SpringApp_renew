package com.example.demo.app.common.id.user;

import com.example.demo.common.id.SuperId;

/**
 * ログインIDクラス
 * @author nanai
 *
 */
public class LoginId implements SuperId {

	/** デフォルトのIDの値 */
	private static final int DEFAULT_ID_NUMBER = 0;

	/** ID */
	private int id;

	/**
	 * コンストラクタ
	 * @param id
	 */
	public LoginId() {
		this.setId(DEFAULT_ID_NUMBER);
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public LoginId(int id) {
		this.setId(id);
	}

	/**
	 * getter
	 * @return id
	 */
	@Override
	public int getId() {
		return this.id;
	}

	/**
	 * setter
	 * @param id
	 */
	public void setId(int id) {
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
