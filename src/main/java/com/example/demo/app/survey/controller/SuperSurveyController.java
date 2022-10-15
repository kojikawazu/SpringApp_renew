package com.example.demo.app.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.header.HeaderController;
import com.example.demo.app.service.survey.SurveyService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
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
	
	/** サービス　*/
	/** --------------------------------------------------------------- */
	
	/** 
	 * 調査サービス 
	 * {@link SurveyService} 
	 */
	protected final SurveyService surveyService;
	
	/** コントローラー */
	/** --------------------------------------------------------------- */
	
	/** 
	 * ヘッダーサービス 
	 * {@link HeaderController} 
	 */
	protected final HeaderController	headerController;
	
	/** --------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param surveyService			{@link SurveyService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public SuperSurveyController(
			SurveyService 		surveyService,
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		this.surveyService 		= surveyService;
		this.headerController	= new HeaderController(userServiceUse, 
											sessionLoginUser);
	}
	
	/**
	 * 共通attribute設定
	 * @param model {@link Model}
	 */
	protected void setCommonAttribute(Model model) {
		/** ヘッダーの設定 */
		this.headerController.setHeader(model);
	}
	
	/**
	 * アンケート一覧attribute設定
	 * @param model {@link Model}
	 */
	protected void setIndexAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_SURVEY_INDEX);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_SURVEY_INDEX);
	}
	
	/**
	 * フォームattribute設定
	 * @param model {@link Model}
	 */
	protected void setFormAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_SURVEY_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_SURVEY_FORM);
	}
	
	/**
	 * 投稿確認attribute設定
	 * @param model {@link Model}
	 */
	protected void setConfirmAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_SURVEY_CONFIRM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_SURVEY_CONFIRM);
	}
	
	/**
	 * 調査投稿 - リダイレクトattribute設定
	 * @param redirectAttributes {@link RedirectAttributes}
	 */
	protected void setCompleteAttribute(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(WebConsts.ATTRIBUTE_COMPLETE, 
				MESSAGE_SURVEY_ADD);
	}
	
	/**
	 * 調査統計attribute設定
	 * @param model {@link Model}
	 * @param list  調査評価フォーム型リスト
	 */
	protected void setSatisAttribute(Model model, List<SurveySatisForm> list) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE,      TITLE_SURVEY_SATIS);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,       CONT_SURVEY_SATIS);
		model.addAttribute(AppConsts.ATTRIBUTE_SATIS_LIST, list);
	}

}
