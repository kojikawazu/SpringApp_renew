package com.example.demo.app.header.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.entity.user.UserModel;
import com.example.demo.app.header.form.UserLoginForm;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.CookieLoginUser;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.common.WebFunctions;
import com.example.demo.common.id.user.LoginId;
import com.example.demo.common.id.user.UserId;
import com.example.demo.common.log.LogMessage;

/**
 * スーパーユーザーコントローラー
 * @author nanai
 *
 */
public class SuperUserController {
	
	/** 
	 * サービス
	 * {@link UserServiceUse}
	 */
	private final UserServiceUse		userService;
	
	/** 
	 * サービス
	 * {@link LoginServiceUse}
	 */
	private final LoginServiceUse		loginService;
	
	/** 
	 * セッション
	 * {@link SessionModel}
	 */
	private final SessionModel			sessionModel;
	
	/** 
	 * Cookie
	 * {@link CookieLoginUser}
	 */
	
	private final CookieLoginUser 		cookieLoginUser;
	
	/**
	 * ロガー
	 * {@link LogMessage}
	 */
	private final LogMessage			logMessage;
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param userService		 	{@link UserServiceUse}
	 * @param loginService		 	{@link LoginServiceUse}
	 * @param sessionModel	 		{@link SessionModel}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public SuperUserController(
			UserServiceUse		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			LogMessage			logMessage) {
		this.userService 		= userService;
		this.loginService		= loginService;
		this.sessionModel		= sessionModel;
		this.logMessage			= logMessage;
		this.cookieLoginUser	= new CookieLoginUser();
	}
	
	/** --------------------------------------------------------------------------- */

	public UserServiceUse getUserService() {
		return this.userService;
	}
	
	public LoginServiceUse getLoginService() {
		return this.loginService;
	}
	
	public SessionModel getSessionModel() {
		return this.sessionModel;
	}
	
	public LogMessage getLog() {
		return this.logMessage;
	}
	
	public CookieLoginUser getCookieLoginUser() {
		return this.cookieLoginUser;
	}
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * Cookie保存状態の確認
	 * @return true Cookie保存中 false Cookie初期状態
	 */
	protected boolean isCookie() {
		// Cookieが保存されている?
		if (!this.getCookieLoginUser().isLoginId() || 
				!this.getCookieLoginUser().isUserId() ||
				!this.getCookieLoginUser().isUserName()) {
			// Cookieは初期化状態
			return false;
		}
		// Cookie保存状態
		return true;
	}
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * セッション + Cookieの初期化
	 * @param request		{@link HttpServletRequest}
	 * @param response		{@link HttpServletResponse}
	 */
	protected void initCookieLoginUser(
			HttpServletRequest		request,
			HttpServletResponse 	response) {
		WebFunctions.deleteCookie(request, response, WebConsts.COOKIE_KEY_LIST);
	}
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * Cookieモデルの設定
	 * @param cookieLoginId
	 * @param cookieUserId
	 * @param cookieUserName
	 */
	protected void setCookieModel(
			String cookieLoginId,
			String cookieUserId,
			String cookieUserName) {
		this.getCookieLoginUser().setLoginId(Integer.valueOf(cookieLoginId));
		this.getCookieLoginUser().setUserId(Integer.valueOf(cookieUserId));
		this.getCookieLoginUser().setUserName(cookieUserName);
	}
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * ログインユーザーの削除
	 * @return true 削除成功 false 削除失敗
	 */
	protected boolean deleteLoginUser(int loginId) {
		try {
			if (loginId <= CookieLoginUser.getLoginIdInit()) {
				// ログイン削除失敗
				return false;
			}
			
			this.getLoginService().delete(
					new LoginId(loginId));
		} catch(Exception ex) {
			this.getLog().error(ex.getMessage());
		}
		
		// ログイン削除成功
		return true;
	}
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * ユーザーモデル取得
	 * @param  userLoginForm {@link UserLoginForm}
	 * @return {@link UserModel}
	 */
	protected UserModel getUserModel(
			UserLoginForm userLoginForm) {
		UserModel model = null;
		this.getLog().start();
		
		try {
			model = this.getUserService().select_byNameOrEmailAndPassword(
					userLoginForm.getName(), 
					userLoginForm.getPassword());
			
			this.getLog().end();
		} catch(Exception ex) {
			this.getLog().error(ex.getMessage());
			model = null;
		}
		
		return model;
	}
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * ログインモデル取得
	 * @param userModel {@link UserModel}
	 * @return {@link LoginModel}
	 */
	protected LoginModel getLoginModel(
			UserModel userModel) {
		LoginModel model = null;
		this.getLog().start();
		
		try {
			model = this.getLoginService().select(
					new UserId(userModel.getId()));
			
			this.getLog().end();
		} catch(Exception ex) {
			this.getLog().error(ex.getMessage());
			model = null;
		}
		
		return model;
	}
}
