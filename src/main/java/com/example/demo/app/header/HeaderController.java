package com.example.demo.app.header;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.CookieLoginUser;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.encrypt.CommonEncrypt;
import com.example.demo.common.id.user.LoginId;
import com.example.demo.common.log.LogMessage;

/**
 * ヘッダーコントローラー
 * @author nanai
 *
 */
public class HeaderController extends SuperHeaderController {

	/** ログインなしキーワード */
	public static final String NO_LOGIN_ID_NAME		= "noLoginId";
	
	/** ログイン中キーワード */
	public static final String YES_LOGIN_ID_NAME	= "yesLoginId";
	
	/** ログイン表示側 */
	public static final String LOGIN_NOW_WORD		= "yes";
	
	/** ログイン非表示側 */
	public static final String LOGIN_NO_WORD		= "";
	
	/** -------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param userService		{@link UserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	public HeaderController(
			UserServiceUse		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			HttpSession			httpSession,
			LogMessage			logMessage) {
		super(userService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/** ----------------------------------------------------------------------------------------- */
	
	/**
	 * Cookieの変化があったかチェック処理
	 * @param cookieLoginId
	 * @param cookieUserId
	 * @param cookieUserName
	 * @return true 変化なし false 変化あり
	 */
	private boolean isChangeCookie(
			String cookieLoginId,
			String cookieUserId,
			String cookieUserName) {
		int cookieLoginIdValue	= Integer.valueOf(cookieLoginId);
		int cookieUserIdValue 	= Integer.valueOf(cookieUserId);
		CookieLoginUser cookieLoginUser = this.getCookieModel().getCookieLoginUser(); 
		
		// 過去と現在が一致してる？
		if (cookieLoginUser.getLoginId() != cookieLoginIdValue ||
				cookieLoginUser.getUserId() != cookieUserIdValue ||
				!cookieLoginUser.getUserName().equals(cookieUserName)) {
			// 変化あり
			
			// Cookie破棄された?
			if (cookieLoginUser.getLoginId() > CookieLoginUser.getLoginIdInit() &&
				cookieLoginIdValue == CookieLoginUser.getLoginIdInit()) {
				// Cookie破棄
				
				// データはまだ保存されてる?
				if (this.getLoginService().isSelect_byId(
						cookieLoginUser.getLoginId())) {
					// 残っているログイン情報削除
					this.getLoginService().delete(
							new LoginId(cookieLoginUser.getLoginId()));
					// 変化あり
					return false;
				}
			}
		}
		
		// 変化なし
		return true;
	}
	
	/**
	 * Cookieの設定
	 * @param loginId
	 * @param userId
	 * @param userName
	 */
	public void setCookie(
			String loginId, String userId, String userName) {
		// 復号化
		String decryptedLoginId		= CommonEncrypt.decrypt(loginId);
		String decryptedUserId 		= CommonEncrypt.decrypt(userId);
		String decryptedUserName 	= CommonEncrypt.decrypt(userName);
		
		// Cookie変化チェック
		isChangeCookie(decryptedLoginId, decryptedUserId, decryptedUserName);
		
		CookieLoginUser cookieLoginUser = this.getCookieModel().getCookieLoginUser(); 
		cookieLoginUser.setLoginId(decryptedLoginId);
		cookieLoginUser.setUserId(decryptedUserId);
		cookieLoginUser.setUserName(decryptedUserName);
	}
	
	/**
	 * ヘッダーの設定
	 * @param request		{@link HttpServletRequest}
	 * @param headerForm	{@link HeaderForm}
	 * @param model			{@link Model}
	 */
	public void setHeader(
			HttpServletRequest	request,
			HeaderForm 			headerForm,
			Model 				model) {
		CookieLoginUser cookieLoginUser = this.getCookieModel().getCookieLoginUser();
		int loginId						= cookieLoginUser.getLoginId();
		String loginUserName			= cookieLoginUser.getUserName();
		
		if (loginId > 0) {
			// ログイン中
			model.addAttribute(NO_LOGIN_ID_NAME,	LOGIN_NO_WORD);
			model.addAttribute(YES_LOGIN_ID_NAME,	LOGIN_NOW_WORD);
			model.addAttribute("loginUserName",	loginUserName);
			
			headerForm.getUserLoginForm().setName(loginUserName);
			headerForm.getUserLogoutForm().setNowLogoutId(loginId);
		} else {
			// ログインなし
			model.addAttribute(NO_LOGIN_ID_NAME,	LOGIN_NOW_WORD);
			model.addAttribute(YES_LOGIN_ID_NAME,	LOGIN_NO_WORD);
			
			headerForm.getUserLoginForm().setName(CookieLoginUser.getUserNameInit());
			headerForm.getUserLogoutForm().setNowLogoutId(CookieLoginUser.getLoginIdInit());
		}
	}
}
