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
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.common.WebFunctions;

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
	
	/** ---------------------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param loginService	{@link LoginServiceUse}
	 */
	public SecurityUserInterval(
			LoginServiceUse			loginService) {
		this.loginService 	= loginService;
	}
	
	/** ---------------------------------------------------------------------------------------- */
	
	/**
	 * ユーザーインターバル受信
	 * @param detailUser	{@link SecLoginUserDetails}
	 * @param request		{@link HttpServletRequest}
	 * @param response		{@link HttpServletResponse}
	 * @return レスポンス結果
	 * @throws IOException
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_USER_INTERVAL)
	@ResponseBody
	public String userinterval(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			HttpServletRequest								request,
			HttpServletResponse 							response) throws IOException {
		
		if (this.isCheck(detailUser)) {
			return WebConsts.RESPONSE_DELETE;
		}
		
		return WebConsts.RESPONSE_OK;
	}
	
	/**
	 * セキュリティユーザークラスチェック
	 * @param detailUser セキュリティユーザークラス
	 * @return true OK false NG
	 */
	private boolean isCheck(
			SecLoginUserDetails	detailUser) {
		SecUserModel model = detailUser.getSecUserModel();
		
		try {
			LoginModel loginModel =  this.loginService.select(new UserId(model.getId()));
			
			if (WebFunctions.checkDiffHour(loginModel.getUpdated(), WebConsts.LOGIN_TIMEOUT_HOUR)) {
				return false;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return true;
	}
	
}
