package com.example.demo.app.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.entity.SurveyModel;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.SurveyService;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.common.AppConsts;

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
	 * @param service
	 */
	@Autowired
	public SurveyController(
			SurveyService service) {
		super(service);
	}
	
	/**
	 * 調査一覧受信
	 * @param pageidx
	 * @param model
	 * @return 調査一覧URL
	 */
	@GetMapping
	public String index(
			@RequestParam(value = WebConsts.ATTRIBUTE_PAGE_IDX, 
							required = false, defaultValue = "1") int pageidx,
			Model model) {
		
		this.setPaging(pageidx, model);
		this.setIndexAttribute(model);
		return AppConsts.URL_SURVEY_INDEX;
	}
	
	/**
	 * ページング設定
	 * @param pageidx
	 * @param model
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
