package com.example.demo.app.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.header.HeaderController;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;

/**
 * ホームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_HOME)
public class HomeController {
	
	/** Homeページのタイトル */
	private static final String TITLE_HOME = "Welcome to Practice Home";
	
	/** Homeページの紹介文 */
	private static final String CONT_HOME = "Springフレームワークを使用したWebアプリをテーマに作成したページです。";
	
	/** コントローラー */
	/** --------------------------------------------------------------- */
	
	/** 
	 * ヘッダーコントローラー 
	 * {@link HeaderController} 
	 */
	private final HeaderController	headerController;
	
	/** --------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param userService		{@link UserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	public HomeController(
			UserServiceUse 		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			HttpSession			httpSession,
			LogMessage			logMessage) {
		this.headerController	= new HeaderController(
										userService,
										loginService,
										sessionModel,
										httpSession,
										logMessage);
	}

	/**
	 * index
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  request			{@link HttpServletRequest}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_HOME_INDEX}
	 */
	@RequestMapping
	public String index(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)		String cookieUserName,
			HttpServletRequest	request,
			HeaderForm 			headerForm,
			Model				model) {
		/** Cookieの設定 */
		this.headerController.setCookie(cookieLoginId, cookieUserId, cookieUserName);
		
		/** attributeの設定 */
		this.setAttribute(model);
		
		/** ヘッダーの設定 */
		this.headerController.setHeader(request, headerForm, model);
		
		return AppConsts.URL_HOME_INDEX;
	}
	
	/**
	 * Attribute設定
	 * @param model {@link Model}
	 */
	private void setAttribute(Model model) {
		model.addAttribute(
				WebConsts.ATTRIBUTE_TITLE, 
				TITLE_HOME);
		
		model.addAttribute(
				WebConsts.ATTRIBUTE_CONT, 
				CONT_HOME);
	}
}
