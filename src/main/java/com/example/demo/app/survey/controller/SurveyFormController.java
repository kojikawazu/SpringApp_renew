package com.example.demo.app.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.app.survey.form.SurveyForm;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;

/**
 * 調査フォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SURVEY)
public class SurveyFormController extends SuperSurveyController {

	/**
	 * コンストラクタ
	 * @param surveyService			{@link SurveyService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public SurveyFormController(
			SurveyService 		surveyService,
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		super(surveyService,
				userServiceUse,
				sessionLoginUser);
	}
	
	/**
	 * 調査フォーム受信(Get)
	 * @param  headerForm	{@link HeaderForm}
	 * @param  surveyForm	{@link SurveyForm}
	 * @param  model		{@link Model}
	 * @param  complete
	 * @return {@value AppConsts#URL_SURVEY_FORM}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String form(
			HeaderForm	headerForm,
			SurveyForm	surveyForm,
			Model		model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		// attribute設定
		this.setCommonAttribute(model);
		this.setFormAttribute(model);
		return AppConsts.URL_SURVEY_FORM;
	}
	
	/**
	 * 調査フォーム受信(Post)
	 * @param  headerForm	{@link HeaderForm}
	 * @param  surveyForm	{@link SurveyForm}
	 * @param  model		{@link Model}
	 * @return {@value AppConsts#URL_SURVEY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String formGoBack(
			HeaderForm	headerForm,
			SurveyForm	surveyForm,
			Model		model) {
		// attribute設定
		this.setCommonAttribute(model);
		this.setFormAttribute(model);
		return AppConsts.URL_SURVEY_FORM;
	}
	
}
