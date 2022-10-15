package com.example.demo.app.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.app.survey.form.SurveySatisForm;
import com.example.demo.common.common.AppConsts;

/**
 * 調査統計コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SURVEY)
public class SurveySatisController extends SuperSurveyController  {

	/**
	 * コンストラクタ
	 * @param surveyService			{@link SurveyService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public SurveySatisController(
			SurveyService 		surveyService,
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		super(surveyService,
				userServiceUse,
				sessionLoginUser);
	}
	
	/**
	 * アンケート統計受信
	 * @param  headerForm	{@link HeaderForm}
	 * @param  model		{@link Model}
	 * @return {@value AppConsts#URL_SURVEY_SATISTICS}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_SATIS)
	public String satisfaction(
			HeaderForm	headerForm,
			Model		model) {
		List<SurveySatisForm> list = this.surveyService.countSatisfactionAll();
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setSatisAttribute(model, list);
		return AppConsts.URL_SURVEY_SATISTICS;
	}
	
}
