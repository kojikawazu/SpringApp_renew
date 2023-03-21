package com.example.demo.app.common.id.user;

import com.example.demo.common.id.SuperIdString;

/**
 * セッションIDクラス
 * <br>
 * implements {@link SuperIdString}
 * @author nanai
 *
 */
public class SessionId implements SuperIdString {

	/** ID */
	private String id;

	/**
	 * コンストラクタ
	 */
	public SessionId() {
		this.id = "";
	}

	/**
	 * コンストラクタ
	 * @param id {@link String}
	 */
	public SessionId(String id) {
		this.id = id;
	}

	/**
	 * getter
	 * @return {@link String}
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * setter
	 * @param id {@link String}
	 */
	public void setId(String id) {
		if (id == null)	return;
		this.id = id;
	}

	/**
	 * setter
	 * @param sessionId {@link SessionId}
	 */
	public void setId(SessionId sessionId) {
		if (sessionId == null)	return;
		this.id = sessionId.getId();
	}
}
