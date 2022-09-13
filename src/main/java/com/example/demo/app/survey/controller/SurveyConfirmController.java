package com.example.demo.app.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.service.SurveyService;
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
	 * @param service
	 */
	@Autowired
	public SurveyConfirmController(
			SurveyService service) {
		super(service);
	}
	
	/**
	 * 調査投稿確認受信(Post)
	 * @param surveyForm
	 * @param result
	 * @param model
	 * @return 調査フォームURL or 調査投稿確認URL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_CONFIRM)
	public String confirm(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			// エラー
			this.setFormAttribute(model);
			return AppConsts.URL_SURVEY_FORM;
		}
		
		this.setConfirmAttribute(model);
		return AppConsts.URL_SURVEY_CONFIRM;
	}
	
}
