package com.example.demo.app.common.id.user;

import com.example.demo.common.id.SuperIdString;

/**
 * セッションIDクラス
 * @author nanai
 *
 */
public class SessionId implements SuperIdString {

	/** ID */
	private String id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public SessionId(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

}
