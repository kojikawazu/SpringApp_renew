package com.example.demo.app.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.service.SurveyService;
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
	 * @param service
	 */
	@Autowired
	public SurveySatisController(
			SurveyService service) {
		super(service);
	}
	
	/**
	 * アンケート統計受信
	 * @param model
	 * @return アンケート統計URL
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_SATIS)
	public String satisfaction(
			Model model) {
		List<SurveySatisForm> list = this.surveyService.countSatisfactionAll();
		this.setSatisAttribute(model, list);
		return AppConsts.URL_SURVEY_SATISTICS;
	}
	
}
