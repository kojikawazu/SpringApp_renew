package com.example.demo.app.blog.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.BlogForm;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;
import com.example.demo.common.number.EditorSwitch;

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
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param userService			{@link UserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	public BlogAddFormController(
			BlogMainService		blogMainService, 
			BlogReplyService	blogReplyService, 
			BlogTagService		blogTagService,
			UserServiceUse 		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			HttpSession			httpSession,
			LogMessage			logMessage) {
		super(blogMainService,
				blogReplyService,
				blogTagService,
				userService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/**
	 * ブログ投稿フォーム受信(Get)
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
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
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)				String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)				String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)				String cookieUserName,
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
		this.headerController.setCookie(request, response, cookieLoginId, cookieUserId, cookieUserName);
		/** ヘッダーの設定 */
		this.headerController.setHeader(request, headerForm, model);
		
		// attribute設定
		this.setCommonAttribute(request, headerForm, model);
		this.setAddFormAttribute(edit, model);
		return AppConsts.URL_BLOG_MAIN_FORM;
	}
	
	/**
	 * ブログ投稿フォーム受信(Post)
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
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
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)				String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)				String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)				String cookieUserName,
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
		this.headerController.setCookie(request, response, cookieLoginId, cookieUserId, cookieUserName);
		/** ヘッダーの設定 */
		this.headerController.setHeader(request, headerForm, model);
		
		// attribute設定
		this.setCommonAttribute(request, headerForm, model);
		this.setAddFormAttribute(edit, model);
		return AppConsts.URL_BLOG_MAIN_FORM;
	}

}
