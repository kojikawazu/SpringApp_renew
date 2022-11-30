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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.entity.survey.SurveyModel;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;

/**
 * 調査コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SURVEY)
public class SurveyController extends SuperSurveyController {
	
	/** 調査一覧ページの最大数 */
	private static final int SURVEY_PAGE_MAX = 5;
	
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
	public SurveyController(
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
	 * 調査一覧受信
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  pageidx
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_SURVEY_INDEX}
	 */
	@GetMapping
	public String index(
			@AuthenticationPrincipal SecLoginUserDetails			detailUser,
			@RequestParam(value = WebConsts.ATTRIBUTE_PAGE_IDX, 
							required = false, defaultValue = "1") 	int pageidx,
			HttpServletRequest	request,
			HttpServletResponse response,
			HeaderForm			headerForm,
			Model				model) {
		/** Cookieの設定 */
		this.setInclude(detailUser, request, response, headerForm, model);
		
		// ページ設定
		this.setPaging(pageidx, model);
				
		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
		this.setIndexAttribute(model);
		return AppConsts.URL_SURVEY_INDEX;
	}
	
	/**
	 * ページング設定
	 * @param pageidx
	 * @param model		{@link Model}
	 */
	private void setPaging(int pageidx, Model model) {
		List<SurveyModel> list       = this.surveyService.getAll();
		PageController page          = new PageController();
		List<SurveyModel> surveyList = page.setPaging(list, pageidx, SURVEY_PAGE_MAX);
		
		// ページクラスに設定
		page.setPageName(AppConsts.REQUEST_MAPPING_SATIS);
		
		model.addAttribute(AppConsts.ATTRIBUTE_SURVEY_LIST, surveyList);
		model.addAttribute(WebConsts.ATTRIBUTE_PAGING,      page);
		list.clear();
	}
	
}
