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
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.service.user.LoginServiceUse;

/**
 * ログアウト成功ハンドラー(カスタム版)
 * implements {@link LogoutSuccessHandler}
 * @author nanai
 *
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	/**
	 * ログインサービス
	 * {@link LoginServiceUse}
	 */
	@Autowired
	LoginServiceUse loginService;
	
	/** ------------------------------------------------------------------------------------- */
	
	/**
	 * ログアウト成功
	 * <br>
	 */
	@Override
	public void onLogoutSuccess(
			HttpServletRequest 		request, 
			HttpServletResponse 	response, 
			Authentication 			authentication)
			throws IOException, ServletException {
		
		if (this.deleteLoginUser(authentication)) {
			// レスポンス設定
			this.setResponse(request, response);
			return ;
		}
		
		// レスポンス設定
		this.setResponse(request, response);
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
		SecUserModel userModel 			= detailUser.getSecUserModel();
		
		if (userModel == null) {
			// Security側にユーザー情報存在しない...
			return false;
		}
		
		try {
			UserId userId = new UserId(userModel.getId());
			if(loginService.isSelect_byUserId(userId)) {
				// ログイン情報存在する
				// ログイン情報削除
				this.loginService.delete(userId);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		return true;
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
