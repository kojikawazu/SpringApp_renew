package com.example.demo.app.home;

import javax.servlet.http.HttpSession;

import com.example.demo.app.header.controller.HeaderController;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.log.LogMessage;

/**
 * スーパーhomeコントローラー
 * @author nanai
 *
 */
public class SuperHomeController {

	/** コントローラー */
	/** --------------------------------------------------------------- */

	/** 
	 * ヘッダーコントローラー 
	 * {@link HeaderController} 
	 */
	private final HeaderController	headerController;

	/** --------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param secUserService	{@link SecurityUserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	public SuperHomeController(
			SecurityUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage){
		this.headerController	= new HeaderController(
				secUserService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}

	/**
	 * ヘッダーコントローラーgetter
	 * @return {@link HeaderController}
	 */
	public HeaderController getHeaderController() {
		return this.headerController;
	}
}
