package com.example.demo.app.survey.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.survey.SurveyModel;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.app.survey.form.SurveyForm;
import com.example.demo.common.common.AppConsts;
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
	 * @param surveyService			{@link SurveyService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public SurveyCompleteController(
			SurveyService 		surveyService,
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		super(surveyService,
				userServiceUse,
				sessionLoginUser);
	}
	
	/**
	 * 調査投稿実施受信
	 * @param  headerForm			{@link HeaderForm}
	 * @param surveyForm			{@link SurveyForm}
	 * @param result				{@link BindingResult}
	 * @param model					{@link Model}
	 * @param redirectAttributes	{@link RedirectAttributes}
	 * @return {@value AppConsts#URL_SURVEY_FORM}
	 * 			{@value AppConsts#REDIRECT_URL_SURVEY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_COMPLETE)
	public String complete(
			HeaderForm				headerForm,
			@Validated SurveyForm	surveyForm,
			BindingResult			result,
			Model					model,
			RedirectAttributes		redirectAttributes) {
		if(result.hasErrors()) {
			// エラー
			// attribute設定
			this.setCommonAttribute(model);
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
