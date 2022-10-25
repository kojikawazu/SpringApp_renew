package com.example.demo.app.header.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.header.form.UserLogoutForm;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.CookieLoginUser;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;

/**
 * ユーザーログアウトコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_USER)
public class UserLogoutController extends SuperUserController {

	/**
	 * コンストラクタ
	 * @param userService		{@link UserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionLoginUser	{@link SessionModel}
	 * @param logMessage		{@link LogMessage}
	 */
	public UserLogoutController(
			UserServiceUse 		userService,
			LoginServiceUse		loginService,
			SessionModel 	sessionLoginUser,
			LogMessage			logMessage) {
		super(userService, 
				loginService, 
				sessionLoginUser,
				logMessage);
	}
	
	/** -------------------------------------------------------------------------------------------- */
	
	/**
	 * ログイン受信
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  userLogoutForm	{@link UserLogoutForm}
	 * @param  result			{@link BindingResult}
	 * @param  model 			{@link Model}
	 * @return OK or NG
	 */
	@RequestMapping(AppConsts.REQUEST_MAPPING_LOGOUT)
	@ResponseBody
	public String logoutAction(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)		String cookieUserName,
			HttpServletRequest			request,
			HttpServletResponse 		response,
			@Validated UserLogoutForm	userLogoutForm,
			BindingResult          		result,
			Model						model) {
		
		// Cookieモデルの設定
		this.setCookieModel(cookieLoginId, cookieUserId, cookieUserName);
		
		// Cookie保存状態?
		if (!this.isCookie()) {
			// Cookie初期状態
			
			// フォームに保存しているログインIDで削除可能かチェック
			int formLoginId = userLogoutForm.getNowLogoutId();
			if (this.isDelete(formLoginId)) {
				// 削除可能
				// ログイン情報削除
				this.deleteLoginUser(formLoginId);
			}
			return WebConsts.RESPONSE_INIT;
		}
		
		// CookieログインIDで削除可能かチェック
		int loginId = this.getCookieLoginUser().getLoginId();
		if (this.isDelete(loginId)) {
			// 削除可能
			// ログイン情報削除
			this.deleteLoginUser(loginId);
		}
		
		// Cookieログインユーザーの初期化
		this.initCookieLoginUser(request, response);
		return WebConsts.RESPONSE_OK;
	}
	
	/**
	 * セッションログインIDで削除可能か？
	 * @return true 削除可能 false 削除不可
	 */
	private boolean isDelete(int loginId) {
		if (loginId <= CookieLoginUser.getLoginIdInit()) {
			// ログイン不可
			return false;
		}
		boolean isLoginId = this.getLoginService().isSelect_byId(
								loginId);
		
		if (!isLoginId) {
			// ログインしてない
			return false;
		}
		
		// 削除OK
		return true;
	}
}
