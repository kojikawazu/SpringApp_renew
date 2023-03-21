package com.example.demo.app.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.header.controller.HeaderController;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.log.LogMessage;

/**
 * ホームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_HOME)
public class HomeController extends SuperHomeController {

	/** SuccessedのURL */
	private static final String RETUEN_SUCCESSED_URL 		= AppConsts.URL_HOME_INDEX;

	/** Homeページのタイトル */
	private static final String TITLE_HOME 					= "Welcome to Practice Home";

	/** Homeページの紹介文 */
	private static final String CONT_HOME 					= "Springフレームワークを使用したWebアプリをテーマに作成したページです。";

	/** 自己紹介タイトルキー */
	private static final String INTRO_ICON_TITLE_KEY		= "introTitleKey";
	/** ブログタイトルキー */
	private static final String BLOG_ICON_TITLE_KEY			= "blogTitleKey";
	/** アンケートタイトルキー */
	private static final String SURVEY_ICON_TITLE_KEY		= "surveyTitleKey";
	/** お問い合わせタイトルキー */
	private static final String INQUIRY_ICON_TITLE_KEY		= "inquiryTitleKey";

	/** 自己紹介タイトルbody */
	private static final String INTRO_ICON_TITLE_BODY		= "自己紹介";
	/** ブログタイトルbody */
	private static final String BLOG_ICON_TITLE_BODY		= "ブログ";
	/** アンケートタイトルbody */
	private static final String SURVEY_ICON_TITLE_BODY		= "アンケート";
	/** お問い合わせタイトルbody */
	private static final String INQUIRY_ICON_TITLE_BODY		= "お問い合わせ";

	/**
	 * コンストラクタ
	 * @param secUserService	{@link SecUserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	public HomeController(
			SecUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		super(secUserService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}

	/**
	 * index
	 * @param  detailUser	{@link SecLoginUserDetails}
	 * @param  request		{@link HttpServletRequest}
	 * @param  response		{@link HttpServletResponse}
	 * @param  headerForm	{@link HeaderForm}
	 * @param  model		{@link Model}
	 * @return {@value AppConsts#URL_HOME_INDEX}
	 */
	@RequestMapping
	public String index(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			HttpServletRequest								request,
			HttpServletResponse 							response,
			HeaderForm 										headerForm,
			Model											model) {
		IntroAppLogWriter logWriter = IntroAppLogWriter.getInstance();
		logWriter.start("");

		/** Cookieの設定 */
		this.getHeaderController().setCookie(detailUser, request, response);

		/** attributeの設定 */
		this.setAttribute(model);

		/** ヘッダーの設定 */
		this.getHeaderController().setHeader(detailUser, request, headerForm, model);

		logWriter.successed("URL: " + RETUEN_SUCCESSED_URL);
		return RETUEN_SUCCESSED_URL;
	}

	/**
	 * Attribute設定
	 * @param model {@link Model}
	 */
	private void setAttribute(Model model) {
		IntroAppLogWriter logWriter = IntroAppLogWriter.getInstance();
		logWriter.start("");

		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_HOME);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_HOME);

		model.addAttribute(INTRO_ICON_TITLE_KEY,   INTRO_ICON_TITLE_BODY);
		model.addAttribute(BLOG_ICON_TITLE_KEY,    BLOG_ICON_TITLE_BODY);
		model.addAttribute(SURVEY_ICON_TITLE_KEY,  SURVEY_ICON_TITLE_BODY);
		model.addAttribute(INQUIRY_ICON_TITLE_KEY, INQUIRY_ICON_TITLE_BODY);

		logWriter.successed("");
	}
}
