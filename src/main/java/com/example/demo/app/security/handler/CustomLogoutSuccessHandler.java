package com.example.demo.app.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * ログアウト成功ハンドラー(カスタム版)
 * implements {@link LogoutSuccessHandler}
 * @author nanai
 *
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

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
	private LoginServiceUse loginService;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter = IntroAppLogWriter.getInstance();

	/** ------------------------------------------------------------------------------------- */

	/**
	 * ログアウト成功
	 */
	@Override
	public void onLogoutSuccess(
			HttpServletRequest 		request, 
			HttpServletResponse 	response, 
			Authentication 			authentication)
			throws IOException, ServletException {
		this.logWriter.start("");

		if (!this.deleteLoginUser(authentication)) {
			// レスポンス設定
			this.setResponse(request, response);
			this.logWriter.error("ログイン情報の削除に失敗");
			return ;
		}

		// レスポンス設定
		this.setResponse(request, response);
		this.logWriter.successed("");
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * ログイン情報の削除
	 * @param  authentication {@link Authentication}
	 * @return true 成功 false 失敗
	 */
	private boolean deleteLoginUser(
			Authentication 	authentication) {
		SecLoginUserDetails	detailUser 	= (SecLoginUserDetails)authentication.getPrincipal();
		SecUserModel 		userModel 	= detailUser.getSecUserModel();
		boolean				result		= false;

		this.logWriter.start("");
		if (userModel == null) {
			// Security側にユーザー情報存在しない...
			this.logWriter.warning("ユーザー情報が存在しない");
			return result;
		}

		try {
			UserId userId = userModel.getId();
			if (this.loginService.isSelect_byUserId(userId)) {
				// ログイン情報存在する
				// ログイン情報削除
				this.logWriter.info("ログイン情報の削除 開始: " + userId.getId());
				this.loginService.delete(userId);
				this.logWriter.info("ログイン情報の削除 終了: " + userId.getId());
			}
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			return result;
		}

		result = true;
		this.logWriter.successed("result: " + result);
		return result;
	}

	/**
	 * レスポンス設定
	 * @param request	{@link HttpServletRequest}
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
