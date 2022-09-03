package com.example.demo.app.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.InquiryModel;
import com.example.demo.app.service.InquiryReplyService;
import com.example.demo.app.service.InquiryService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.InquiryId;

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
	
	/** サービス　*/
	protected final InquiryService      inquiryService;
	protected final InquiryReplyService inquiryReplyService;
	
	/**
	 * コンストラクタ
	 * @param service
	 * @param inquiryReplyService
	 */
	@Autowired
	public SuperInquiryController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService) {
		this.inquiryService      = inquiryService;
		this.inquiryReplyService = inquiryReplyService;
	}
	
	/**
	 * 問い合わせページattribute設定
	 * @param model
	 */
	protected void setIndexAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_INQUIRY_INDEX);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_INDEX);
	}
	
	/**
	 * 問い合わせフォームattribute設定
	 * @param model
	 */
	protected void setFormAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITLE_INQUIRY_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_FORM);
	}
	
	/**
	 * 問い合わせ確認attribute設定
	 * @param model
	 */
	protected void setConfirmAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITTLE_INQUIRY_CONFIRM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_CONFIRM);
	}
	
	/**
	 * 問い合わせ成功 - リダイレクトattribute設定
	 * @param redirectAttributes
	 */
	protected void setRedirectCompleteAttribute(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(WebConsts.ATTRIBUTE_COMPLETE, 
				MESSAGE_INQUIRY_COMPLETE);
	}
	
	/**
	 * 問い合わせ返信フォームattribute設定
	 * @param model
	 */
	protected void setReplyFormAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITTLE_INQUIRY_REPLY_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_REPLY_FORM);
	}
	
	/**
	 * 問い合わせ返信確認attribute設定
	 * @param model
	 */
	protected void setReplyConfirmAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, TITTLE_INQUIRY_REPLY_CONFIRM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,  CONT_INQUIRY_REPLY_CONFIRM);
	}
	
	/**
	 * 問い合わせ返信投稿 - リダイレクトattribute設定
	 * @param redirectAttributes
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
	 * @param id
	 * @param model
	 */
	protected void setReply(InquiryId inquiryId, Model model) {
		InquiryModel inquiryModel = this.inquiryService.select(inquiryId);
		
		model.addAttribute(AppConsts.ATTRIBUTE_INQUIRY, inquiryModel);
		model.addAttribute(WebConsts.ATTRIBUTE_ID,      inquiryId.getId());
	}
}
