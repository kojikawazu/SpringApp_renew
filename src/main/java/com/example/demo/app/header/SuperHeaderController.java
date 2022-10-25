package com.example.demo.app.header;

import javax.servlet.http.HttpSession;

import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.CookieModel;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.log.LogMessage;

public class SuperHeaderController {

	/** サービス */
	/** --------------------------------------------------------------- */
	
	/** 
	 * ユーザーサービス 
	 * {@link UserServiceUse} 
	 */
	private final UserServiceUse	userService;
	
	/** 
	 * ログインサービス 
	 * {@link LoginServiceUse} 
	 */
	private final LoginServiceUse	loginService;
	
	/** セッション */
	/** --------------------------------------------------------------- */
	
	/** 
	 * ログインセッション 
	 * {@link SessionModel} 
	 */
	private SessionModel			sessionModel;
	
	/** 
	 * Cookieモデル 
	 * {@link CookieModel} 
	 */
	private CookieModel				cookieModel;
	
	/** 
	 * HTTPセッション 
	 * {@link HttpSession} 
	 */
	private HttpSession				httpSession;
	
	/** ログクラス */
	/** --------------------------------------------------------------- */
	
	/**
	 * ロガー
	 * {@link LogMessage}
	 */
	private final LogMessage		logMessage;
	
	/** --------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param userService		{@link UserServiceUse}
	 * @param userService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	public SuperHeaderController(
			UserServiceUse		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			HttpSession			httpSession,
			LogMessage			logMessage) {
		this.userService 		= userService;
		this.loginService		= loginService;
		this.sessionModel 		= sessionModel;
		this.httpSession		= httpSession;
		this.cookieModel		= new CookieModel();
		this.logMessage 		= logMessage;
	}
	
	/** --------------------------------------------------------------- */
	
	public UserServiceUse getUserService() {
		return this.userService;
	}
	
	public LoginServiceUse getLoginService() {
		return this.loginService;
	}
	
	public SessionModel getSessionModel() {
		return this.sessionModel;
	}
	
	public CookieModel getCookieModel() {
		return this.cookieModel;
	}
	
	public HttpSession getHttpSession() {
		return this.httpSession;
	}
	
	public LogMessage getLog() {
		return this.logMessage;
	}
	
}
