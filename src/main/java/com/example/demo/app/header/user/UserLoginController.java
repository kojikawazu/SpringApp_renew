package com.example.demo.app.header.user;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.entity.user.UserModel;
import com.example.demo.app.header.form.UserLoginForm;
import com.example.demo.app.header.response.UserLoginResponse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.CookieLoginUser;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.common.WebFunctions;
import com.example.demo.common.encrypt.CommonEncrypt;
import com.example.demo.common.id.user.LoginId;
import com.example.demo.common.id.user.SessionId;
import com.example.demo.common.id.user.UserId;
import com.example.demo.common.log.LogMessage;

/**
 * ユーザーログインコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_USER)
public class UserLoginController extends SuperUserController {
	
	/**
	 * コンストラクタ
	 * @param userService		{@link UserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param logMessage		{@link LogMessage}
	 */
	@Autowired
	public UserLoginController(
			UserServiceUse		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			LogMessage			logMessage) {
		super(userService, 
				loginService, 
				sessionModel,
				logMessage);
	}
	
	/** ------------------------------------------------------------------------------- */
	
	/**
	 * ログイン受信
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  userLoginForm	{@link UserLoginForm}
	 * @param  result			{@link BindingResult}
	 * @param  model			{@link Model}
	 * @return {@link UserLoginResponse}
	 */
	@RequestMapping(AppConsts.REQUEST_MAPPING_LOGIN)
	@ResponseBody
	public UserLoginResponse loginAction(
			HttpServletRequest			request,
			HttpServletResponse 		response,
			@Validated UserLoginForm 	userLoginForm,
			BindingResult          		result,
			Model						model) {
		UserLoginResponse userResponse = new UserLoginResponse();
		
		// エラーチェック
		UserModel userModel = this.getUserModel(userLoginForm);
		if (!this.isCheck(userLoginForm, userModel, result)) {
			// -------------------------------------------------------------------------------------------
			// エラー
			// -------------------------------------------------------------------------------------------
			userResponse = this.getResponse(WebConsts.RESPONSE_NG, null);
			return userResponse;
		}
		
		// -----------------------------------------------------------------------------------------------
		// 正常
		// -----------------------------------------------------------------------------------------------
		
		// ログイン情報の保存
		LoginId loginId = this.saveLoginUser(request, userLoginForm, userModel);
		if (loginId.getId() > CookieLoginUser.getLoginIdInit()) {
			// セッションとCookieの保存
			this.saveSession(response, loginId, userModel);
		}
		
		userResponse = this.getResponse(WebConsts.RESPONSE_OK, userModel);
		return userResponse;
	}
	
	/**
	 * チェック処理
	 * @param userLoginForm	{@link UserLoginForm}
	 * @param userModel		{@link UserModel}
	 * @param result		{@link BindingResult}
	 * @return true チェックOK false チェックNG
	 */
	private boolean isCheck(
			UserLoginForm 	userLoginForm,
			UserModel		userModel,
			BindingResult	result) {
		this.getLog().start();
		
		// バリデーションチェック
		if (result.hasErrors()) {
			this.getLog().error("バリデーションチェックNG");
			// ログインNG
			return false;
		}
		
		// ユーザー存在チェック
		if (userModel == null) {
			this.getLog().info("(ユーザーorEメール)、パスワードが存在しない");
			// ログインNG
			return false;
		}
		
		// ログインチェック
		LoginModel loginModel = this.getLoginModel(userModel);
		if (loginModel != null) {
			this.getLog().info("ログイン情報存在する");
			// ログインNG
			return false;
		}
		
		// ログイン可能
		this.getLog().info("OK.");
		return true;
	}
	
	/**
	 * ログイン情報追加
	 * @param request 			{@link HttpServletRequest}
	 * @param userLoginForm		{@link UserLoginForm}
	 * @param userModel			{@link UserModel}
	 * @return {@link LoginId}
	 */
	private LoginId saveLoginUser(
			HttpServletRequest	request,
			UserLoginForm 		userLoginForm,
			UserModel			userModel) {
		HttpSession 	session = request.getSession();
		LocalDateTime 	now = LocalDateTime.now();
		LoginModel 		loginModel = new LoginModel(
				new UserId(userModel.getId()),
				new SessionId(session.getId()),
				now,
				now
				);
		this.getLog().start();
		
		// ログイン情報保存
		LoginId loginId = this.getLoginService().save_returnId(loginModel);
		if (loginId == null) {
			// エラー
			this.getLog().error("ログイン情報保存失敗");
			return new LoginId(0);
		}
		
		// ログイン成功
		this.getLog().end();
		return loginId;
	}
	
	/**
	 * セッション、Cookieの保存
	 * @param response		{@link HttpServletResponse}
	 * @param loginId		{@link LoginId}
	 * @param userModel		{@link UserModel}
	 */
	private void saveSession(
			HttpServletResponse 	response,
			LoginId					loginId, 
			UserModel 				userModel) {
		// 暗号化
		String encryptedLoginId		= CommonEncrypt.encrypt(loginId.getId());
		String encryptedUserId		= CommonEncrypt.encrypt(userModel.getId());
		String encryptedUserName 	= CommonEncrypt.encrypt(userModel.getName());
		
		// Cookieの保存
		WebFunctions.saveCookie(
				response, 
				WebConsts.COOKIE_LOGIN_ID, 
				encryptedLoginId,
				WebConsts.COOKIE_TIMEOUT);
		
		WebFunctions.saveCookie(
				response, 
				WebConsts.COOKIE_USER_ID, 
				encryptedUserId,
				WebConsts.COOKIE_TIMEOUT);
		
		WebFunctions.saveCookie(
				response, 
				WebConsts.COOKIE_USER_NAME, 
				encryptedUserName, 
				WebConsts.COOKIE_TIMEOUT);
	}
	
	/**
	 * レスポンス取得
	 * @param  result 結果文字列
	 * @return {@link UserLoginResponse}
	 */
	private UserLoginResponse getResponse(
			String result, 
			UserModel userModel) {
		UserLoginResponse response = null;
		if (result.equals(WebConsts.RESPONSE_OK)) {
			response = new UserLoginResponse(
					WebConsts.RESPONSE_OK, 
					userModel.getName());
		} else {
			// NG
			response = new UserLoginResponse(
					WebConsts.RESPONSE_NG, 
					CookieLoginUser.getUserNameInit());
		}
		return response;
	}

}
