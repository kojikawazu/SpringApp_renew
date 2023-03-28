package com.example.demo.app.header.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.CookieModel;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.ThymeleafConsts;
import com.example.demo.common.log.LogMessage;

/**
 * スーパーヘッダーコントローラー
 * @author nanai
 *
 */
public class SuperHeaderController {

	/** サービス */
	/** --------------------------------------------------------------- */

	/** 
	 * ユーザーサービス 
	 * {@link SecUserServiceUse} 
	 */
	private final SecUserServiceUse			secUserService;

	/** 
	 * ログインサービス 
	 * {@link LoginServiceUse} 
	 */
	private final LoginServiceUse			loginService;

	/** セッション */
	/** --------------------------------------------------------------- */

	/** 
	 * ログインセッション 
	 * {@link SessionModel} 
	 */
	private SessionModel					sessionModel;

	/** 
	 * Cookieモデル 
	 * {@link CookieModel} 
	 */
	private CookieModel						cookieModel;

	/** 
	 * HTTPセッション 
	 * {@link HttpSession} 
	 */
	private HttpSession						httpSession;

	/**
	 * ログインユーザー
	 * {@link SecLoginUserDetails}
	 */
	private SecLoginUserDetails				secLoginUserDetails;

	/** ログクラス */
	/** --------------------------------------------------------------- */

	/**
	 * ロガー
	 * {@link LogMessage}
	 */
	private final LogMessage				logMessage;

	/** --------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param secUserService	{@link SecUserServiceUse}
	 * @param userService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	public SuperHeaderController(
			SecUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		this.secUserService 		= secUserService;
		this.loginService			= loginService;
		this.sessionModel 			= sessionModel;
		this.httpSession			= httpSession;
		this.cookieModel			= new CookieModel();
		this.logMessage 			= logMessage;
		this.secLoginUserDetails	= null;
	}

	/** --------------------------------------------------------------- */

	public SecUserServiceUse getSecUserService() {
		return this.secUserService;
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

	public SecLoginUserDetails getSecLoginUserDetails() {
		return this.secLoginUserDetails;
	}

	public LogMessage getLog() {
		return this.logMessage;
	}

	/** --------------------------------------------------------------- */

	/**
	 * setter
	 * @param secLoginUserDetails {@link SecLoginUserDetails}
	 */
	public void setSecLoginUserDetails(SecLoginUserDetails secLoginUserDetails) {
		if (secLoginUserDetails == null) {
			return ;
		}
		this.secLoginUserDetails = secLoginUserDetails;
	}

	/**
	 * 初期化(SecLoginUserDetails)
	 */
	public void initSecLoginUserDetails() {
		this.secLoginUserDetails = null;
	}

	/** --------------------------------------------------------------- */

	/**
	 * Thymeleaf定数クラスの設定
	 * @param model [@link Model]
	 */
	protected void setSameModel(Model model) {
		model.addAttribute("ThyConsts", new ThymeleafConsts());
	}
}
