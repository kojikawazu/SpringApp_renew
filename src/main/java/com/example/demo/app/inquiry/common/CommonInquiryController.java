package com.example.demo.app.inquiry.common;

import javax.servlet.http.HttpSession;

import com.example.demo.app.header.controller.HeaderController;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.log.LogMessage;

/**
 * (共通)問い合わせコントローラークラス
 * @author nanai
 *
 */
public class CommonInquiryController {

	/** サービス */
	/** --------------------------------------------------------------- */
	
	/** 
	 * 問い合わせサービス 
	 * {@link SecUserServiceUse} 
	 */
	private final InquiryService			inquiryService;
	
	/** 
	 * 問い合わせ返信サービス 
	 * {@link InquiryReplyService} 
	 */
	private final InquiryReplyService		inquiryReplyService;
	
	/** 
	 * ユーザーサービス 
	 * {@link SecUserServiceUse} 
	 */
	private final SecUserServiceUse	secUserService;
	
	/** コントローラー */
	/** --------------------------------------------------------------- */
	
	/** 
	 * ヘッダコントローラー 
	 * {@link HeaderController} 
	 */
	private final HeaderController			headerController;
	
	/** ログクラス*/
	/** --------------------------------------------------------------- */
	
	/** 
	 * ログクラス
	 * {@link LogMessage} 
	 */
	private final LogMessage				logMessage;
	
	/** --------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param inquiryService		{@link InquiryService}
	 * @param inquiryReplyService	{@link InquiryReplyService}
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	public CommonInquiryController(
			InquiryService      	inquiryService, 
			InquiryReplyService 	inquiryReplyService,
			SecUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		this.inquiryService			= inquiryService;
		this.inquiryReplyService	= inquiryReplyService;
		this.secUserService			= secUserService;
		this.logMessage				= logMessage;
		this.headerController		= new HeaderController(secUserService,
														loginService,
														sessionModel,
														httpSession,
														logMessage);
	}
	
	/** --------------------------------------------------------------- */
	
	/** 
	 * 問い合わせサービスの取得
	 * {@link InquiryService} 
	 */
	public InquiryService getInquiryService() {
		return this.inquiryService;
	}
	
	/** 
	 * 問い合わせ返信サービスの取得
	 * {@link InquiryReplyService} 
	 */
	public InquiryReplyService getInquiryReplyService() {
		return this.inquiryReplyService;
	}
	
	/** 
	 * ユーザーサービスの取得
	 * {@link SecUserServiceUse} 
	 */
	public SecUserServiceUse getSecurityUserService() {
		return this.secUserService;
	}
	
	/** 
	 * ヘッダコントローラーの取得
	 * {@link HeaderController} 
	 */
	public HeaderController getHeaderController() {
		return this.headerController;
	}
	
	/** 
	 * ログクラスの取得
	 * {@link LogMessage} 
	 */
	public LogMessage getLog() {
		return this.logMessage;
	}
	
	/** --------------------------------------------------------------- */
}
