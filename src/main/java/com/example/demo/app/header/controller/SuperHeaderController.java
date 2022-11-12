package com.example.demo.app.header.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.CookieModel;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.ThymeleafConsts;
import com.example.demo.common.log.LogMessage;

public class SuperHeaderController {

	/** サービス */
	/** --------------------------------------------------------------- */
	
	/** 
	 * ユーザーサービス 
	 * {@link SecurityUserServiceUse} 
	 */
	private final SecurityUserServiceUse	secUserService;
	
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
	 * @param secUserService	{@link SecurityUserServiceUse}
	 * @param userService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	public SuperHeaderController(
			SecurityUserServiceUse	secUserService,
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
	
	public SecurityUserServiceUse getSecUserService() {
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
	
	public void setSecLoginUserDetails(SecLoginUserDetails secLoginUserDetails) {
		if (secLoginUserDetails == null) {
			return ;
		}
		this.secLoginUserDetails = secLoginUserDetails;
	}
	
	public void initSecLoginUserDetails() {
		this.secLoginUserDetails = null;
	}
	
	/** --------------------------------------------------------------- */
	
	protected void setSameModel(Model model) {
		model.addAttribute("ThyConsts", new ThymeleafConsts());
	}
	
	
}
