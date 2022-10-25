package com.example.demo.app.session.user;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * セッションログインユーザークラス
 * @author nanai
 *
 */
@Component
@SessionScope
public class SessionModel implements Serializable  {

	private static final long serialVersionUID	= 1L;
	
	/** ----------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public SessionModel() {
		super();
	}
	
}
