package com.example.demo.app.header.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.log.LogMessage;

/**
 * ヘッダーコントローラー<br>
 * extends {@link SuperHeaderController}
 * @author nanai
 *
 */
public class HeaderController extends SuperHeaderController {

	/** ログインなしキーワード */
	public static final String NO_LOGIN_ID_NAME		= "noLoginId";
	
	/** ログイン中キーワード */
	public static final String YES_LOGIN_ID_NAME	= "yesLoginId";
	
	/** ログイン中のユーザー名 */
	public static final String LOGOUT_USER_NAME		= "logoutUserName";
	
	/** ログインユーザーモデル */
	public static final String LOGIN_USER_MODEL		= "loginUserModel";
	
	/** ログイン表示側 */
	public static final String LOGIN_NOW_WORD		= "yes";
	
	/** ログイン非表示側 */
	public static final String LOGIN_NO_WORD		= "";
	
	/** -------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param secUserService	{@link SecurityUserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	public HeaderController(
			SecurityUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		super(secUserService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/** ----------------------------------------------------------------------------------------- */
	
	/**
	 * Cookieの設定
	 * @param 	secLoginUserDetails	{@link SecLoginUserDetails}
	 * @param  	request				{@link HttpServletRequest}
	 * @param 	response			{@link HttpServletResponse}
	 */
	public void setCookie(
			SecLoginUserDetails		secLoginUserDetails,
			HttpServletRequest		request,
			HttpServletResponse 	response) {
		this.setSecLoginUserDetails(secLoginUserDetails);
	}
	
	/**
	 * ヘッダーの設定
	 * @param secLoginUserDetails	{@link SecLoginUserDetails}
	 * @param request				{@link HttpServletRequest}
	 * @param  headerForm			{@link HeaderForm}
	 * @param model					{@link Model}
	 */
	public void setHeader(
			SecLoginUserDetails	secLoginUserDetails,
			HttpServletRequest	request,
			HeaderForm 			headerForm,
			Model 				model) {
		if (secLoginUserDetails != null) {
			// ログイン中
			
			model.addAttribute(NO_LOGIN_ID_NAME,	LOGIN_NO_WORD);
			model.addAttribute(YES_LOGIN_ID_NAME,	LOGIN_NOW_WORD);
			model.addAttribute(LOGOUT_USER_NAME,	secLoginUserDetails.getUsername());
			//model.addAttribute(LOGIN_USER_MODEL,    userModel);
			
		} else {
			// ログインなし
			model.addAttribute(NO_LOGIN_ID_NAME,	LOGIN_NOW_WORD);
			model.addAttribute(YES_LOGIN_ID_NAME,	LOGIN_NO_WORD);
			model.addAttribute(LOGOUT_USER_NAME,	"");
		}
		
		/** model共通反映 */
		this.setSameModel(model);
	}
	
	/**
	 * ログイン情報の更新日付の更新
	 * @param loginId ログインID
	 */
	private void updateTime_LoginModel(int loginId) {
		try {
			this.getLoginService().updateTime(new LoginId(loginId));
		} catch(Exception ex) {
			this.getLog().warning(ex.getMessage());
		}
	}
}
