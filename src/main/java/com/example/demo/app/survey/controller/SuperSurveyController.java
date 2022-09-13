package com.example.demo.app.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.service.SurveyService;
import com.example.demo.app.survey.form.SurveySatisForm;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;

/**
 * スーパー調査クラス
 * @author nanai
 *
 */
public class SuperSurveyController {
	
	/** 調査一覧のタイトル */
	private static final String TITLE_SURVEY_INDEX = "アンケート一覧";
	
	/** 調査一覧の一言 */
	private static final String CONT_SURVEY_INDEX  = "アンケート一覧を表示してます。";
	
	/** 調査ページのタイトル */
	private static final String TITLE_SURVEY_FORM = "アンケート投稿フォーム";
	
	/** 調査ページの一言 */
	private static final String CONT_SURVEY_FORM  = "アンケートを入力してください。";
	
	/** 調査ページのタイトル */
	private static final String TITLE_SURVEY_CONFIRM = "アンケート投稿確認";
	
	/** 調査ページの一言 */
	private static final String CONT_SURVEY_CONFIRM  = "これでよろしいでしょうか？";
	
	/** 調査投稿成功メッセージ */
	private static final String MESSAGE_SURVEY_ADD = "投稿ありがとうございました！";
	
	/** 調査統計のタイトル */
	private static final String TITLE_SURVEY_SATIS = "アンケート統計";
	
	/** 調査統計の一言 */
	private static final String CONT_SURVEY_SATIS  = "アンケートを集計した一覧です。";
	
	/**
	 * サービス
	 */
	protected final SurveyService surveyService;
	
	/**
	 * コンストラクタ
	 * @param service
	 */
	@Autowired
	public SuperSurveyController(
			SurveyService service) {
		this.surveyService = service;
	}
	
	/**
	 * アンケート一覧attribute設定
	 * @param model
	 */
	protected void setIndexAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_SURVEY_INDEX);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_SURVEY_INDEX);
	}
	
	/**
	 * フォームattribute設定
	 * @param model
	 */
	protected void setFormAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_SURVEY_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_SURVEY_FORM);
	}
	
	/**
	 * 投稿確認attribute設定
	 * @param model
	 */
	protected void setConfirmAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_SURVEY_CONFIRM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_SURVEY_CONFIRM);
	}
	
	/**
	 * 調査投稿 - リダイレクトattribute設定
	 * @param redirectAttributes
	 */
	protected void setCompleteAttribute(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(WebConsts.ATTRIBUTE_COMPLETE, 
				MESSAGE_SURVEY_ADD);
	}
	
	/**
	 * 調査統計attribute設定
	 * @param model
	 * @param list
	 */
	protected void setSatisAttribute(Model model, List<SurveySatisForm> list) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE,      TITLE_SURVEY_SATIS);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,       CONT_SURVEY_SATIS);
		model.addAttribute(AppConsts.ATTRIBUTE_SATIS_LIST, list);
	}

}
