package com.example.demo.app.survey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.app.survey.form.SurveySatisForm;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.log.LogMessage;

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
	 * @param surveyService		{@link SurveyService}
	 * @param secUserService	{@link SecurityUserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	@Autowired
	public SurveySatisController(
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
	 * アンケート統計受信
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_SURVEY_SATISTICS}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_SATIS)
	public String satisfaction(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			HttpServletRequest								request,
			HttpServletResponse 							response,
			HeaderForm										headerForm,
			Model											model) {
		List<SurveySatisForm> list = this.surveyService.countSatisfactionAll();
		
		/** Cookieの設定 */
		this.setInclude(detailUser, request, response, headerForm, model);
		
		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
		this.setSatisAttribute(model, list);
		return AppConsts.URL_SURVEY_SATISTICS;
	}
	
}
