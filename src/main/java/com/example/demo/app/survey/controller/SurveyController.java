package com.example.demo.app.survey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.entity.survey.SurveyModel;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;
import com.example.demo.common.common.AppConsts;

/**
 * 調査コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SURVEY)
public class SurveyController extends SuperSurveyController {
	
	/** 調査一覧ページの最大数 */
	private static final int SURVEY_PAGE_MAX = 5;
	
	/**
	 * コンストラクタ
	 * @param surveyService		{@link SurveyService}
	 * @param userService		{@link UserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	@Autowired
	public SurveyController(
			SurveyService 		surveyService,
			UserServiceUse 		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			HttpSession			httpSession,
			LogMessage			logMessage) {
		super(surveyService,
				userService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/**
	 * 調査一覧受信
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  pageidx
	 * @param request			{@link HttpServletRequest}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_SURVEY_INDEX}
	 */
	@GetMapping
	public String index(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)					String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)					String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)					String cookieUserName,
			@RequestParam(value = WebConsts.ATTRIBUTE_PAGE_IDX, 
							required = false, defaultValue = "1") 	int pageidx,
			HttpServletRequest	request,
			HeaderForm			headerForm,
			Model				model) {
		/** Cookieの設定 */
		this.headerController.setCookie(cookieLoginId, cookieUserId, cookieUserName);
		
		// ページ設定
		this.setPaging(pageidx, model);
		
		/** ヘッダーの設定 */
		this.headerController.setHeader(request, headerForm, model);
		
		// attribute設定
		this.setCommonAttribute(request, headerForm, model);
		this.setIndexAttribute(model);
		return AppConsts.URL_SURVEY_INDEX;
	}
	
	/**
	 * ページング設定
	 * @param pageidx
	 * @param model		{@link Model}
	 */
	private void setPaging(int pageidx, Model model) {
		List<SurveyModel> list       = this.surveyService.getAll();
		PageController page          = new PageController();
		List<SurveyModel> surveyList = page.setPaging(list, pageidx, SURVEY_PAGE_MAX);
		
		// ページクラスに設定
		page.setPageName(AppConsts.REQUEST_MAPPING_SATIS);
		
		model.addAttribute(AppConsts.ATTRIBUTE_SURVEY_LIST, surveyList);
		model.addAttribute(WebConsts.ATTRIBUTE_PAGING,      page);
		list.clear();
	}
	
}
