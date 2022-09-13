package com.example.demo.app.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.service.SurveyService;
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
	 * @param service
	 */
	@Autowired
	public SurveyFormController(
			SurveyService service) {
		super(service);
	}
	
	/**
	 * 調査フォーム受信(Get)
	 * @param  surveyForm
	 * @param  model
	 * @param  complete
	 * @return 調査フォームURL
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String form(
			SurveyForm surveyForm,
			Model      model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		this.setFormAttribute(model);
		return AppConsts.URL_SURVEY_FORM;
	}
	
	/**
	 * 調査フォーム受信(Post)
	 * @param  surveyForm
	 * @param  model
	 * @return 調査フォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String formGoBack(
			SurveyForm surveyForm,
			Model      model) {
		this.setFormAttribute(model);
		return AppConsts.URL_SURVEY_FORM;
	}
	
}
