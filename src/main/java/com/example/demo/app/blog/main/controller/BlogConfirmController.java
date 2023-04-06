package com.example.demo.app.blog.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.BlogForm;
import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.number.EditorSwitch;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainServiceUse;
import com.example.demo.app.service.blog.BlogReplyServiceUse;
import com.example.demo.app.service.blog.BlogTagServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;

/**
 * ブログ投稿 or 編集確認コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogConfirmController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService		{@link BlogMainServiceUse}
	 * @param blogReplyService		{@link BlogReplyServiceUse}
	 * @param blogTagService		{@link BlogTagServiceUse}
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	public BlogConfirmController(
			BlogMainServiceUse		blogMainService, 
			BlogReplyServiceUse		blogReplyService, 
			BlogTagServiceUse		blogTagService,
			SecUserServiceUse		secUserService,
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
	 * 確認画面受信
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  editor
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  blogForm			{@link BlogForm}
	 * @param  result			{@link BindingResult}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_BLOG_MAIN_FORM}
	 * 			{@value AppConsts#URL_BLOG_MAIN_CONFIRM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_CONFIRM)
	public String confirm(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			@RequestParam(WebConsts.ATTRIBUTE_EDITOR)		int editor,
			HttpServletRequest	request,
			HttpServletResponse response,
			HeaderForm			headerForm,
			@Validated BlogForm	blogForm,
			BindingResult		result,
			Model				model) {
		EditorSwitch edit = new EditorSwitch(editor);
		
		/** Cookieの設定 */
		this.setInclude(detailUser, request, response, headerForm, model);
		
		if (result.hasErrors()) {
			// エラー
			// attribute設定
			this.setCommonAttribute(detailUser, request, response, headerForm, model);
			if (edit.isEdit()) {
				this.setEditorAttribute(edit, blogForm, model);
			} else {
				this.setAddFormAttribute(edit, model);
			}
			return AppConsts.URL_BLOG_MAIN_FORM;
		}
		
		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
		this.setConfirmAttribute(edit, model);
		return AppConsts.URL_BLOG_MAIN_CONFIRM;
	}
	
}
