package com.example.demo.app.blog.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.blog.main.form.BlogForm;
import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.number.EditorSwitch;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.header.controller.HeaderController;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;

/**
 * スーパーブログクラス
 * @author nanai
 *
 */
public class SuperBlogMainController {
	
	/** ブログ一覧タイトル */
	private static final String TITLE_BLOG_INDEX              = "●●さんのブログ";
	
	/** ブログ一覧の一言 */
	private static final String CONT_BLOG_INDEX               = "気になる話題がありましたら閲覧お願いします。";
	
	/** ブログ投稿タイトル */
	private static final String TITLE_BLOG_ADD_FORM           = "ブログ投稿";
	
	/** ブログ投稿の一言 */
	private static final String CONT_BLOG_ADD_FORM            = "ブログを投稿してください。";
	
	/** ブログ編集タイトル */
	private static final String TITLE_BLOG_EDIT_FORM          = "ブログの編集";
	
	/** ブログ編集の一言 */
	private static final String CONT_BLOG_EDIT_FORM           = "ブログを編集してください。";
	
	/** ブログ投稿確認タイトル */
	private static final String TITLE_BLOG_ADD_CONFIRM        = "ブログ投稿確認";
	
	/** ブログ編集確認タイトル */
	private static final String TITLE_BLOG_EDIT_CONFIRM       = "ブログ変更確認";
	
	/** ブログ確認の一言  */
	private static final String CONT_BLOG_ADDEDIT_CONFIRM     = "これでよろしいですか？";
	
	/** ブログ投稿成功のメッセージ */
	private static final String MESSAGE_BLOG_ADD_COMPLETE     = "投稿されました。";
	
	/** ブログ編集成功のメッセージ */
	private static final String MESSAGE_BLOG_EDIT_COMPLETE    = "変更されました。";
	
	/** ブログ返信フォームタイトル */
	private static final String TITLE_BLOG_REPLY_FORM         = "コメント返信";
	
	/** ブログ返信フォームの一言 */
	private static final String CONT_BLOG_REPLY_FORM          = "ブログに対してのコメントを入力してください。";
	
	/** ブログ返信確認タイトル */
	private static final String TITLE_BLOG_REPLY_CONFIRM      = "コメント返信確認";
	
	/** ブログ返信確認の一言 */
	private static final String CONT_BLOG_REPLY_CONFIRM       = "これでよろしいですか？";
	
	/** ブログ返信成功のメッセージ */
	private static final String MESSAGE_BLOG_REPLY_COMPLETE   = "返信されました。";
	
	/** サービス */
	/** --------------------------------------------------------------- */
	
	/**
	 * ブログメインサービス 
	 * {@link BlogMainService} 
	 */
	protected final BlogMainService  blogMainService;
	
	/**
	 * ブログ返信サービス 
	 * {@link BlogReplyService} 
	 */
	protected final BlogReplyService blogReplyService;
	
	/**
	 * ブログタグサービス 
	 * {@link BlogTagService} 
	 */
	protected final BlogTagService   blogTagService;
	
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
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public SuperBlogMainController(
			BlogMainService			blogMainService, 
			BlogReplyService		blogReplyService, 
			BlogTagService			blogTagService,
			SecUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		this.blogMainService	= blogMainService;
		this.blogReplyService	= blogReplyService;
		this.blogTagService		= blogTagService;
		
		this.headerController	= new HeaderController(
									secUserService,
									loginService,
									sessionModel,
									httpSession,
									logMessage);
	}
	
	/**
	 * 共通attribute設定
	 * @param  detailUser	{@link SecLoginUserDetails}
	 * @param request		{@link HttpServletRequest}
	 * @param  response		{@link HttpServletResponse}
	 * @param headerForm	{@link HeaderForm}
	 * @param model 		{@link Model}
	 */
	protected void setInclude(
			SecLoginUserDetails	detailUser,
			HttpServletRequest	request,
			HttpServletResponse response,
			HeaderForm			headerForm,
			Model 				model) {
		/** ヘッダーの設定 */
		this.headerController.setCookie(detailUser, request, response);
	}
	
	/**
	 * 共通attribute設定
	 * @param  detailUser	{@link SecLoginUserDetails}
	 * @param request		{@link HttpServletRequest}
	 * @param  response		{@link HttpServletResponse}
	 * @param headerForm	{@link HeaderForm}
	 * @param model 		{@link Model}
	 */
	protected void setCommonAttribute(
			SecLoginUserDetails	detailUser,
			HttpServletRequest	request,
			HttpServletResponse response,
			HeaderForm			headerForm,
			Model 				model) {
		/** ヘッダーの設定 */
		this.headerController.setHeader(detailUser, request, headerForm, model);
	}
	
	/**
	 * 一覧attribute設定
	 * @param model {@link Model}
	 */
	protected void setIndexAttribute(Model model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE,      TITLE_BLOG_INDEX);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,       CONT_BLOG_INDEX);
		model.addAttribute(WebConsts.ATTRIBUTE_SELECT_IDX, "0");
	}
	
	/**
	 * 追加フォームattribute設定
	 * @param editor	{@link EditorSwitch}
	 * @param model		{@link Model}
	 */
	protected void setAddFormAttribute(
			EditorSwitch edit, 
			Model        model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE,  TITLE_BLOG_ADD_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,   CONT_BLOG_ADD_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_EDITOR, edit.getEditorNumber());
	}
	
	/**
	 * 編集フォームattribute設定
	 * @param editor	{@link EditorSwitch}
	 * @param blogForm	{@link BlogForm}
	 * @param model		{@link Model}
	 */
	protected void setEditorAttribute(
			EditorSwitch edit, 
			BlogForm     blogForm, 
			Model        model) {
		BlogId        blogId = new BlogId(edit.getEditorNumber());
		BlogMainModel result = this.blogMainService.select(blogId);
		
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE,  TITLE_BLOG_EDIT_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,   CONT_BLOG_EDIT_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_EDITOR, edit.getEditorNumber());
		
		// データの取得
		blogForm.setBlogTitle(   result.getTitle());
		blogForm.setTag(         result.getTag());
		blogForm.setBlogContents(result.getComment());
		blogForm.setThanksCnt(   result.getThanksCnt());
	}
	
	/**
	 * 投稿 or 編集確認attribute設定
	 * @param edit		{@link EditorSwitch}
	 * @param model		{@link Model}
	 */
	protected void setConfirmAttribute(
			EditorSwitch edit, 
			Model        model) {
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE, edit.isEdit() ? 
				TITLE_BLOG_EDIT_CONFIRM : TITLE_BLOG_ADD_CONFIRM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,   CONT_BLOG_ADDEDIT_CONFIRM);
		model.addAttribute(WebConsts.ATTRIBUTE_EDITOR, edit.getEditorNumber());
	}
	
	/**
	 * 投稿成功redirect_attribute設定
	 * @param redirectAttributes {@link RedirectAttributes}
	 */
	protected void setAddCompleteAttribute(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(
				WebConsts.ATTRIBUTE_COMPLETE,
				MESSAGE_BLOG_ADD_COMPLETE);
	}
	
	/**
	 * 編集成功redirect_attribute設定
	 * @param editor				{@link EditorSwitch}
	 * @param blogForm				{@link BlogForm}
	 * @param model					{@link Model}
	 * @param redirectAttributes	{@link RedirectAttributes}
	 */
	protected void setEditCompleteAttribute(
			EditorSwitch       edit, 
			BlogForm           blogForm,
			Model              model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute(
				WebConsts.ATTRIBUTE_EDITOR,    edit.getEditorNumber());
		redirectAttributes.addFlashAttribute(
				AppConsts.ATTRIBUTE_BLOG_FORM, blogForm);
		redirectAttributes.addFlashAttribute(
				WebConsts.ATTRIBUTE_MODEL,     model);
		redirectAttributes.addFlashAttribute(
				WebConsts.ATTRIBUTE_COMPLETE, MESSAGE_BLOG_EDIT_COMPLETE);
	}
	
	/**
	 * 返信フォームattribute設定
	 * @param blogReplyId	{@link BlogId}
	 * @param model			{@link Model}
	 */
	protected void setReplyFormAttribute(
			BlogId blogReplyId, 
			Model  model) {
		BlogMainModel blogmainModel = this.blogMainService.select(blogReplyId);
		
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE,     TITLE_BLOG_REPLY_FORM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,      CONT_BLOG_REPLY_FORM);
		model.addAttribute(AppConsts.ATTRIBUTE_BLOG_MAIN, blogmainModel);
		model.addAttribute(WebConsts.ATTRIBUTE_ID,        blogReplyId.getId());
	}
	
	/**
	 * 返信確認attribute設定
	 * @param blogReplyId
	 * @param model
	 */
	protected void setReplyConfirmAttribute(
			BlogId blogReplyId, 
			Model  model) {
		BlogMainModel blogmainModel = this.blogMainService.select(blogReplyId);
		
		model.addAttribute(WebConsts.ATTRIBUTE_TITLE,     TITLE_BLOG_REPLY_CONFIRM);
		model.addAttribute(WebConsts.ATTRIBUTE_CONT,      CONT_BLOG_REPLY_CONFIRM);
		model.addAttribute(AppConsts.ATTRIBUTE_BLOG_MAIN, blogmainModel);
		model.addAttribute(WebConsts.ATTRIBUTE_ID,        blogReplyId.getId());
	}
	
	/**
	 * 返信成功redirect_attribute設定
	 * @param redirectAttributes
	 */
	protected void setReplyCompleteAttribute(
			BlogId             blogReplyId, 
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute(WebConsts.ATTRIBUTE_ID, blogReplyId.getId());
		redirectAttributes.addFlashAttribute(
				WebConsts.ATTRIBUTE_COMPLETE, 
				MESSAGE_BLOG_REPLY_COMPLETE);
	}
}
