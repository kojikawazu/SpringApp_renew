package com.example.demo.app.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.app.survey.form.SurveyForm;
import com.example.demo.common.common.AppConsts;

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
	 * @param surveyService			{@link SurveyService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public SurveyConfirmController(
			SurveyService 		surveyService,
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		super(surveyService,
				userServiceUse,
				sessionLoginUser);
	}
	
	/**
	 * 調査投稿確認受信(Post)
	 * @param  headerForm	{@link HeaderForm}
	 * @param  surveyForm	{@link SurveyForm}
	 * @param  result		{@link BindingResult}
	 * @param  model		{@link Model}
	 * @return {@value AppConsts#URL_SURVEY_CONFIRM}
	 * 			{@value AppConsts#URL_SURVEY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_CONFIRM)
	public String confirm(
			HeaderForm				headerForm,
			@Validated SurveyForm	surveyForm,
			BindingResult			result,
			Model 					model) {
		if(result.hasErrors()) {
			// エラー
			// attribute設定
			this.setCommonAttribute(model);
			this.setFormAttribute(model);
			return AppConsts.URL_SURVEY_FORM;
		}
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setConfirmAttribute(model);
		return AppConsts.URL_SURVEY_CONFIRM;
	}
	
}
