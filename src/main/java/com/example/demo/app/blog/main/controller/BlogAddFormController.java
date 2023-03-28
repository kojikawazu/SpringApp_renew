package com.example.demo.app.blog.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.BlogForm;
import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.number.EditorSwitch;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainServiceUse;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;

/**
 * ブログ投稿フォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogAddFormController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService		{@link BlogMainServiceUse}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	public BlogAddFormController(
			BlogMainServiceUse		blogMainService, 
			BlogReplyService		blogReplyService, 
			BlogTagService			blogTagService,
			SecUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		super(blogMainService,
				blogReplyService,
				blogTagService,
				secUserService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/**
	 * ブログ投稿フォーム受信(Get)
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  editor
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  blogForm			{@link BlogForm}
	 * @param  model			{@link Model}
	 * @param  complete 		結果メッセージ
	 * @return {@value AppConsts#URL_BLOG_MAIN_FORM}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String form(
			@AuthenticationPrincipal SecLoginUserDetails		detailUser,
			@RequestParam(value      = WebConsts.ATTRIBUTE_EDITOR, 
						required     = false, 
						defaultValue = WebConsts.ZERO_STRING) 	int editor,
			HttpServletRequest	request,
			HttpServletResponse response,
			HeaderForm			headerForm,
			BlogForm			blogForm,
			Model				model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		EditorSwitch edit = new EditorSwitch(editor);
		
		/** Cookieの設定 */
		this.setInclude(detailUser, request, response, headerForm, model);
		
		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
		this.setAddFormAttribute(edit, model);
		return AppConsts.URL_BLOG_MAIN_FORM;
	}
	
	/**
	 * ブログ投稿フォーム受信(Post)
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  editor
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  blogForm			{@link BlogForm}
	 * @param  model			{@link Model}
	 * @param  complete 		結果メッセージ
	 * @return {@value AppConsts#URL_BLOG_MAIN_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String formGoBack(
			@AuthenticationPrincipal SecLoginUserDetails		detailUser,
			@RequestParam(value      = WebConsts.ATTRIBUTE_EDITOR, 
						required     = false, 
						defaultValue = WebConsts.ZERO_STRING)	int editor,
			HttpServletRequest	request,
			HttpServletResponse response,
			HeaderForm			headerForm,
			BlogForm			blogForm,
			Model				model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		EditorSwitch edit = new EditorSwitch(editor);
		
		/** Cookieの設定 */
		this.setInclude(detailUser, request, response, headerForm, model);
		
		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
		this.setAddFormAttribute(edit, model);
		return AppConsts.URL_BLOG_MAIN_FORM;
	}

}
