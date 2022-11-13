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

import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.id.user.SessionId;
import com.example.demo.common.id.user.UserId;

/**
 * 認証成功ハンドラー(カスタム版)
 * implements {@link AuthenticationSuccessHandler}
 * @author nanai
 *
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	/**
	 * ログインサービス
	 * {@link LoginServiceUse}
	 */
	@Autowired
	LoginServiceUse loginService;
	
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
		
		if (response.isCommitted()) {
			return;
		}
		
		// ログイン情報の保存
		this.saveLoginData(authentication);
		
		// レスポンス設定
		this.setResponse(request, response);
	}
	
	/** ------------------------------------------------------------------------------------- */
	
	/**
	 * ログイン情報の保存
	 * @param authentication {@link Authentication}
	 */
	private void saveLoginData(
			Authentication  authentication) {
		SecLoginUserDetails	detailUser 	= (SecLoginUserDetails)authentication.getPrincipal();
		SecUserModel userModel 			= detailUser.getSecUserModel();
		
		// ログイン情報作成
		LoginModel loginModel = new LoginModel(
				new UserId(userModel.getId()),
				new SessionId("0"),
				LocalDateTime.now(),
				LocalDateTime.now());
		
		// ログイン情報の保存
		this.loginService.save(loginModel);
	}
	
	/**
	 * レスポンス設定
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void setResponse(
			HttpServletRequest 	request,
			HttpServletResponse response) throws IOException, ServletException {
		response.setStatus(HttpStatus.OK.value());
		response.sendRedirect(request.getContextPath() + AppConsts.REQUEST_MAPPING_HOME);
	}

}
