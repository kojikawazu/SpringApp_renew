package com.example.demo.app.security.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.user.SessionId;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * 認証成功ハンドラー(カスタム版)
 * implements {@link AuthenticationSuccessHandler}
 * @author nanai
 *
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	/**
	 * 成功時の行き先URL
	 * {@link String}
	 */
	private final String SUCCESSED_URL = AppConsts.REQUEST_MAPPING_HOME;

	/**
	 * ログインサービス
	 * {@link LoginServiceUse}
	 */
	@Autowired
	LoginServiceUse loginService;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter = IntroAppLogWriter.getInstance();

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 認証成功
	 * <br>
	 */
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest 	request, 
			HttpServletResponse response,
			Authentication 		authentication) throws IOException, ServletException {
		this.logWriter.start("");

		if (response.isCommitted()) {
			this.logWriter.error("レスポンス失敗: isCommited()");
			return;
		}

		// ログイン情報の保存
		this.saveLoginData(authentication);
		// レスポンス設定
		this.setResponse(request, response);

		this.logWriter.successed("");
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * ログイン情報の保存
	 * @param authentication {@link Authentication}
	 */
	private void saveLoginData(
			Authentication  authentication) {
		this.logWriter.start("");
		SecLoginUserDetails	detailUser 	= (SecLoginUserDetails)authentication.getPrincipal();
		SecUserModel userModel 			= detailUser.getSecUserModel();

		// ログイン情報作成
		LoginModel loginModel = new LoginModel(
				userModel.getId(),
				new SessionId("0"),
				LocalDateTime.now(),
				LocalDateTime.now());
		// ログイン情報の保存
		this.logWriter.info("ログインモデルの保存前 ユーザID: " + loginModel.getUserId());
		this.loginService.save(loginModel);

		this.logWriter.successed("ログインモデルの保存後 ユーザID: " + loginModel.getUserId());
	}

	/**
	 * レスポンス設定
	 * @param request 	{@link HttpServletRequest}
	 * @param response	{@link HttpServletResponse}
	 * @throws IOException
	 * @throws ServletException
	 */
	private void setResponse(
			HttpServletRequest 	request,
			HttpServletResponse response) throws IOException, ServletException {
		this.logWriter.info("リダイレクト先: " + SUCCESSED_URL);
		response.setStatus(HttpStatus.OK.value());
		response.sendRedirect(request.getContextPath() + SUCCESSED_URL);
	}
}
