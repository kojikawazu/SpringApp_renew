package com.example.demo.app.security.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.common.WebFunctions;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * セキュリティユーザーユーザー監視コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SECURITY)
public class SecurityUserInterval {

	/**
	 * ログインサービスクラス
	 * {@link LoginServiceUse}
	 */
	private final LoginServiceUse			loginService;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  		logWriter;

	/** ---------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param loginService	{@link LoginServiceUse}
	 */
	public SecurityUserInterval(
			LoginServiceUse			loginService) {
		this.loginService 	= loginService;
		this.logWriter 		= IntroAppLogWriter.getInstance();
	}

	/** ---------------------------------------------------------------------------------------- */

	/**
	 * ユーザーインターバル受信
	 * @param detailUser	{@link SecLoginUserDetails}
	 * @param request		{@link HttpServletRequest}
	 * @param response		{@link HttpServletResponse}
	 * @return レスポンス結果	{@link String}
	 *                      <br>{@value WebConsts#RESPONSE_DELETE}
	 *                      <br>{@value WebConsts#RESPONSE_OK}
	 * @throws IOException
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_USER_INTERVAL)
	@ResponseBody
	public String userinterval(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			HttpServletRequest								request,
			HttpServletResponse 							response) throws IOException {
		this.logWriter.start("");

		if (this.validate(detailUser)) {
			this.logWriter.warning("result: " + WebConsts.RESPONSE_DELETE);
			return WebConsts.RESPONSE_DELETE;
		}

		this.logWriter.successed("result: " + WebConsts.RESPONSE_OK);
		return WebConsts.RESPONSE_OK;
	}

	/**
	 * セキュリティユーザークラスチェック
	 * @param detailUser セキュリティユーザークラス {@link SecLoginUserDetails}
	 * @return true OK false NG
	 */
	private boolean validate(
			SecLoginUserDetails	detailUser) {
		if (detailUser == null)	return false;

		this.logWriter.start("");
		SecUserModel model = detailUser.getSecUserModel();
		boolean result = false;

		try {
			LoginModel loginModel =  this.loginService.select(model.getId());

			this.logWriter.info("loginId: " + loginModel.getId() + " " + "userId: " + loginModel.getUserId());
			if (WebFunctions.checkDiffHour(loginModel.getUpdated(), WebConsts.LOGIN_TIMEOUT_HOUR)) {
				this.logWriter.warning("result: " + result);
				return result;
			}
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
		}

		result = true;
		this.logWriter.successed("result: " + result);
		return result;
	}
}
