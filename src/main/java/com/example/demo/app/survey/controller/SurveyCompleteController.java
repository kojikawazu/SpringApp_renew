package com.example.demo.app.survey.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.survey.SurveyModel;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.app.survey.form.SurveyForm;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * 調査投稿実施コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SURVEY)
public class SurveyCompleteController extends SuperSurveyController {

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
	public SurveyCompleteController(
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
	 * 調査投稿実施受信
	 * @param  cookieLoginId		ログインID(Cookie)
	 * @param  cookieUserId			ユーザーID(Cookie)
	 * @param  cookieUserName		ユーザー名(Cookie)
	 * @param  request				{@link HttpServletRequest}
	 * @param  response				{@link HttpServletResponse}
	 * @param  headerForm			{@link HeaderForm}
	 * @param  surveyForm			{@link SurveyForm}
	 * @param  result				{@link BindingResult}
	 * @param  model				{@link Model}
	 * @param  redirectAttributes	{@link RedirectAttributes}
	 * @return {@value AppConsts#URL_SURVEY_FORM}
	 * 			{@value AppConsts#REDIRECT_URL_SURVEY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_COMPLETE)
	public String complete(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)		String cookieUserName,
			HttpServletRequest		request,
			HttpServletResponse 	response,
			HeaderForm				headerForm,
			@Validated SurveyForm	surveyForm,
			BindingResult			result,
			Model					model,
			RedirectAttributes		redirectAttributes) {
		
		
		if(result.hasErrors()) {
			// エラー
			
			/** Cookieの設定 */
			this.headerController.setCookie(request, response, cookieLoginId, cookieUserId, cookieUserName);
			/** ヘッダーの設定 */
			this.headerController.setHeader(request, headerForm, model);
			
			// attribute設定
			this.setCommonAttribute(request, headerForm, model);
			this.setFormAttribute(model);
			return AppConsts.URL_SURVEY_FORM;
		}
		
		SurveyModel survey = new SurveyModel(
				new NameWord(surveyForm.getName()),
				new NormalNumber(surveyForm.getAge()),
				new NormalNumber(surveyForm.getProfession()),
				new NormalNumber(surveyForm.getMen_or_female()),
				new NormalNumber(surveyForm.getSatisfaction()),
				new CommentWord(surveyForm.getComment()),
				LocalDateTime.now()
				);
		this.surveyService.save(survey);
		
		this.setCompleteAttribute(redirectAttributes);
		return AppConsts.REDIRECT_URL_SURVEY_FORM;
	}
}
