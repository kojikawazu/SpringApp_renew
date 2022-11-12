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

import com.example.demo.app.blog.main.form.ReplyForm;
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.blog.BlogId;
import com.example.demo.common.log.LogMessage;

/**
 * ブログ返信確認コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogReplyConfirmController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param secUserService		{@link SecurityUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	public BlogReplyConfirmController(
			BlogMainService			blogMainService, 
			BlogReplyService		blogReplyService, 
			BlogTagService			blogTagService,
			SecurityUserServiceUse	secUserService,
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
	 * ブログ返信確認受信
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  id
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  replyForm		{@link ReplyForm}
	 * @param  result			{@link BindingResult}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_BLOG_REPLY_FORM}
	 * 			{@value AppConsts#URL_BLOG_REPLY_CONFIRM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY_CONFIRM)
	public String reply_confirm(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			@RequestParam(WebConsts.ATTRIBUTE_ID)			int id,
			HttpServletRequest		request,
			HttpServletResponse 	response,
			HeaderForm				headerForm,
			@Validated ReplyForm	replyForm,
			BindingResult			result,
			Model					model) {
		BlogId blogReplyId = new BlogId(id);
		
		/** Cookieの設定 */
		this.setInclude(detailUser, request, response, headerForm, model);
		
		if (result.hasErrors()) {
			// エラー
			// attribute設定
			this.setCommonAttribute(detailUser, request, response, headerForm, model);
			this.setReplyFormAttribute(blogReplyId, model);
			return AppConsts.URL_BLOG_REPLY_FORM;
		}
		
		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
		this.setReplyConfirmAttribute(blogReplyId, model);
		return AppConsts.URL_BLOG_REPLY_CONFIRM;
	}
}
