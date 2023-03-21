package com.example.demo.app.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.example.demo.app.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * 認証失敗ハンドラー(カスタム版)
 * 	<br>extends			{@link SimpleUrlAuthenticationFailureHandler}
 *  <br>implements		{@link AuthenticationFailureHandler}
 * @author nanai
 *
 */
public class CustomAuthenticationFailureHandler 
									extends 	SimpleUrlAuthenticationFailureHandler 
									implements 	AuthenticationFailureHandler {

	/** 失敗URL */
	private final String FAILURE_URL =  AppConsts.REQUEST_MAPPING_SECURITY_FORM + "?" + WebConsts.ATTRIBUTE_ERROR;

	/** 
	 * リダイレクトストラテジ
	 * {@link RedirectStrategy} 
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter = IntroAppLogWriter.getInstance();

	/** -------------------------------------------------------------------------------------------------------- */

	/**
	 * 認証失敗
	 * <br>
	 */
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest 		request, 
			HttpServletResponse 	response,
			AuthenticationException exception) throws IOException, ServletException {
		this.logWriter.start("");

		// レスポンス設定
		this.setResponse(request, response, exception);

		this.logWriter.successed("");
	}

	/**
	 * レスポンス設定
	 * @param request 	{@link HttpServletRequest}
	 * @param response	{@link HttpServletResponse}
	 * @throws IOException
	 * @throws ServletException
	 */
	private void setResponse(
			HttpServletRequest 		request,
			HttpServletResponse 	response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.OK.value());
		super.saveException(request, exception);
		this.redirectStrategy.sendRedirect(request, response, FAILURE_URL);
	}
}
