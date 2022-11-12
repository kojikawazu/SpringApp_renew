package com.example.demo.app.survey.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.app.survey.form.SurveyForm;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.log.LogMessage;

/**
 * 調査投稿確認コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SURVEY)
public class SurveyConfirmController extends SuperSurveyController {

	/**
	 * コンストラクタ
	 * @param surveyService		{@link SurveyService}
	 * @param secUserService	{@link SecurityUserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	@Autowired
	public SurveyConfirmController(
			SurveyService 			surveyService,
			SecurityUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		super(surveyService,
				secUserService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/**
	 * 調査投稿確認受信(Post)
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  surveyForm		{@link SurveyForm}
	 * @param  result			{@link BindingResult}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_SURVEY_CONFIRM}
	 * 			{@value AppConsts#URL_SURVEY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_CONFIRM)
	public String confirm(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			HttpServletRequest								request,
			HttpServletResponse 							response,
			HeaderForm										headerForm,
			@Validated SurveyForm							surveyForm,
			BindingResult									result,
			Model 											model) {
		/** Cookieの設定 */
		this.setInclude(detailUser, request, response, headerForm, model);
		
		if(result.hasErrors()) {
			// エラー
			// attribute設定
			this.setCommonAttribute(detailUser, request, response, headerForm, model);
			this.setFormAttribute(model);
			return AppConsts.URL_SURVEY_FORM;
		}
		
		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
		this.setConfirmAttribute(model);
		return AppConsts.URL_SURVEY_CONFIRM;
	}
	
}
