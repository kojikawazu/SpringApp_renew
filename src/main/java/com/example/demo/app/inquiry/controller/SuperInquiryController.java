package com.example.demo.app.inquiry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.inquiry.InquiryModel;
import com.example.demo.app.header.HeaderController;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.inquiry.InquiryId;
import com.example.demo.common.log.LogMessage;

/**
 * スーパー問い合わせコントローラークラス
 * @author nanai
 *
 */
public class SuperInquiryController {
	
	/** 問い合わせページのタイトル */
	private static final String TITLE_INQUIRY_INDEX = "お問い合わせ一覧";
	
	/** 問い合わせページの一言 */
	private static final String CONT_INQUIRY_INDEX = "サンプルお問い合わせ情報一覧画面です。お問い合わせ一覧はこちらになります。";
	
	/** 問い合わせフォームのタイトル */
	private static final String TITLE_INQUIRY_FORM = "お問い合わせフォーム";
	
	/** 問い合わせフォームの一言 */
	private static final String CONT_INQUIRY_FORM  = "以下の項目を入力してください。";

	/** 問い合わせ確認のタイトル */
	private static final String TITTLE_INQUIRY_CONFIRM = "確認画面";
	
	/** 問い合わせ確認の一言 */
	private static final String CONT_INQUIRY_CONFIRM = "これでよろしいでしょうか？";
	
	/** メッセージ[投稿成功] */
	private static final String MESSAGE_INQUIRY_COMPLETE = "投稿しました。";
	
	/** 問い合わせ返信フォームのタイトル */
	private static final String TITTLE_INQUIRY_REPLY_FORM = "コメント投稿";
	
	/** 問い合わせ返信フォームの一言 */
	private static final String CONT_INQUIRY_REPLY_FORM = "お問い合わせに対してのコメントを入力してください。";
	
	/** 問い合わせ返信確認のタイトル */
	private static final String TITTLE_INQUIRY_REPLY_CONFIRM = "確認画面";
	
	/** 問い合わせ返信確認の一言 */
	private static final String CONT_INQUIRY_REPLY_CONFIRM = "これでよろしいでしょうか？";
	
	private static final String MESSAGE_INQUIRY_REPLY_COMPLETE = "投稿しました。";
	
	/** サービス */
	/** --------------------------------------------------------------- */
	
	/** 
	 * 問い合わせサービス 
	 * {@link InquiryService} 
	 */
	protected final InquiryService		inquiryService;
	
	/** 
	 * 問い合わせ返信サービス 
	 * {@link InquiryReplyService} 
	 */
	protected final InquiryReplyService	inquiryReplyService;
	
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
	 * @param inquiryService		{@link InquiryService}
	 * @param inquiryReplyService	{@link InquiryReplyService}
	 * @param userService			{@link UserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public SuperInquiryController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService,
			UserServiceUse 		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			HttpSession			httpSession,
			LogMessage			logMessage) {
		this.inquiryService			= inquiryService;
		this.inquiryReplyService	= inquiryReplyService;
		this.headerController		= new HeaderController(userService,
														loginService,
														sessionModel,
														httpSession,
														logMessage);
	}
	
	/**
	 * 共通attribute設定
	 * @param request		{@link HttpServletRequest}
	 * @param headerForm	{@link HeaderForm}
	 * @param model 		{@link Model}
	 */
	protected void setCommonAttribute(
			HttpServletRequest	request,
			HeaderForm			headerForm,
			Model 				model) {
		/** ヘッダーの設定 */
		this.headerController.setHeader(request, headerForm, model);
	}
	
	/**
	 * 問い合わせページattribute設定
	 * @param model {@link Model}
	 */
	protected void setIndexAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_INQUIRY_INDEX);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_INDEX);
	}
	
	/**
	 * 問い合わせフォームattribute設定
	 * @param model {@link Model}
	 */
	protected void setFormAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_INQUIRY_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_FORM);
	}
	
	/**
	 * 問い合わせ確認attribute設定
	 * @param model {@link Model}
	 */
	protected void setConfirmAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITTLE_INQUIRY_CONFIRM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_CONFIRM);
	}
	
	/**
	 * 問い合わせ成功 - リダイレクトattribute設定
	 * @param redirectAttributes {@link RedirectAttributes}
	 */
	protected void setRedirectCompleteAttribute(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(WebConsts.ATTRIBUTE_COMPLETE, 
				MESSAGE_INQUIRY_COMPLETE);
	}
	
	/**
	 * 問い合わせ返信フォームattribute設定
	 * @param model {@link Model}
	 */
	protected void setReplyFormAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITTLE_INQUIRY_REPLY_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_REPLY_FORM);
	}
	
	/**
	 * 問い合わせ返信確認attribute設定
	 * @param model {@link Model}
	 */
	protected void setReplyConfirmAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITTLE_INQUIRY_REPLY_CONFIRM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_REPLY_CONFIRM);
	}
	
	/**
	 * 問い合わせ返信投稿 - リダイレクトattribute設定
	 * @param redirectAttributes {@link RedirectAttributes}
	 */
	protected void setReplyCompleteAttribute(
			InquiryId          inquiryId, 
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(WebConsts.ATTRIBUTE_ID, inquiryId.getId());
		redirectAttributes.addFlashAttribute(WebConsts.ATTRIBUTE_COMPLETE, 
				MESSAGE_INQUIRY_REPLY_COMPLETE);
	}
	
	// -------------------------------------------------------------------------------------------------
	
	/**
	 * 問い合わせ返信設定
	 * @param id	{@link InquiryId}
	 * @param model	{@link Model}
	 */
	protected void setReply(InquiryId inquiryId, Model model) {
		InquiryModel inquiryModel = this.inquiryService.select(inquiryId);
		
		model.addAttribute(AppConsts.ATTRIBUTE_INQUIRY, inquiryModel);
		model.addAttribute(WebConsts.ATTRIBUTE_ID,      inquiryId.getId());
	}
}
