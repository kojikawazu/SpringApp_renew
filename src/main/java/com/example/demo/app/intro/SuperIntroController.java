package com.example.demo.app.intro;

import javax.servlet.http.HttpSession;

import com.example.demo.app.header.controller.HeaderController;
import com.example.demo.app.service.intro.IntroService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.log.LogMessage;

/**
 * スーパー自己紹介コントローラー
 * @author nanai
 *
 */
public class SuperIntroController {

	/** 
	 * サービス 
	 * {@link IntroService}
	 */
	private final IntroService		introService;

	/** 
	 * コントローラー 
	 * {@link HeaderController}
	 */
	private final HeaderController	headerController;

	/**
	 * ログ
	 * {@link LogMessage}
	 */
	private final LogMessage		logMessage;

	/** --------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param introService		{@link IntroService}
	 * @param secUserService	{@link SecUserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	public SuperIntroController(
			IntroService 			introService,
			SecUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		this.introService		= introService;
		this.logMessage			= logMessage;

		this.headerController	= new HeaderController(
										secUserService,
										loginService,
										sessionModel,
										httpSession,
										logMessage);
	}

	/** --------------------------------------------------------------------------- */

	/**
	 * getter
	 * @return {@link IntroService}
	 */
	public IntroService getIntroService() {
		return this.introService;
	}

	/**
	 * getter
	 * @return {@link HeaderController}
	 */
	public HeaderController getHeaderController() {
		return this.headerController;
	}

	/**
	 * getter
	 * @return {@link LogMessage}
	 */
	public LogMessage getLogMessage() {
		return this.logMessage;
	}
}
