package com.example.demo.app.header.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.common.WebFunctions;
import com.example.demo.common.id.user.LoginId;
import com.example.demo.common.log.LogMessage;

/**
 * セッションユーザーインターバルコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_USER)
public class UserSessionLogoutController extends SuperUserController {

	/** 結果[削除可能] */
	private static final int CHECK_DELETE		= 0;
	
	/** 結果[何もしない] */
	private static final int CHECK_NONE			= 1;
	
	/** 結果[Cookieが既に破棄されている] */
	private static final int CHECK_COOKIE_NONE	= 2;
	
	/** --------------------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param userService		{@link UserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param logMessage		{@link LogMessage}
	 */
	public UserSessionLogoutController(
			UserServiceUse		userService, 
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			LogMessage			logMessage) {
		super(userService, 
				loginService, 
				sessionModel,
				logMessage);
	}
	
	/**
	 * タイムアウトチェック受信
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  model 			{@link Model}
	 * @return none or delete
	 */
	@RequestMapping("/userinterval")
	@ResponseBody
	public String userIntervalAction(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)		String cookieUserName,
			HttpServletRequest		request,
			HttpServletResponse 	response,
			Model					model) {
		HttpSession 	session = request.getSession();
		this.getLog().info("[debug]: sessionID..."  + session.getId());
		
		// Cookieモデルの設定
		this.setCookieModel(cookieLoginId, cookieUserId, cookieUserName);
		
		// チェック処理
		int checkAnswer = this.isCheck(request, response);
		if (checkAnswer == CHECK_NONE) {
			return WebConsts.RESPONSE_NONE;
		} else if(checkAnswer == CHECK_COOKIE_NONE) {
			return WebConsts.RESPONSE_RELOAD;
		}
		
		// ログイン情報の削除
		this.deleteLoginUser(this.getCookieLoginUser().getLoginId());
				
		// セッションログインユーザーの初期化
		this.initCookieLoginUser(request, response);
		return WebConsts.RESPONSE_DELETE;
	}
	
	
	
	/**
	 * チェック処理
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @return
	 */
	private int isCheck(
			HttpServletRequest		request,
			HttpServletResponse 	response) {
		
		// Cookieが保存されている?
		if (!isCookie()) {
			// Cookieは初期化状態なので何もしない
			return CHECK_NONE;
		}
		
		try {
			// ログイン情報の取得
			int cookieLoginId		= this.getCookieLoginUser().getLoginId();
			LoginModel loginModel	= this.getLoginService().select(new LoginId(cookieLoginId));
			
			// 現在の時間日付がログイン情報の更新時間日付を過ぎてる？
			if (WebFunctions.checkDiffHour(loginModel.getUpdated(), WebConsts.LOGIN_TIMEOUT_HOUR)) {
				// 過ぎてない
				return CHECK_NONE;
			}
		} catch(Exception ex) {
			// ログイン情報存在しない
			this.getLog().error(ex.getMessage());
			
			// セッションログインユーザーの初期化
			this.initCookieLoginUser(request, response);
			return CHECK_COOKIE_NONE;
		}
		
		// ログイン情報削除可能
		return CHECK_DELETE;
	}

}
