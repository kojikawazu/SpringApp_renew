package com.example.demo.app.blog.main.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.blog.main.form.ReplyForm;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.blog.BlogId;
import com.example.demo.common.log.LogMessage;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信実施コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogReplyCompleteController extends SuperBlogMainController  {

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
	public BlogReplyCompleteController(
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
	 * ブログ返信成功受信
	 * @param  cookieLoginId		ログインID(Cookie)
	 * @param  cookieUserId			ユーザーID(Cookie)
	 * @param  cookieUserName		ユーザー名(Cookie)
	 * @param  id
	 * @param  request				{@link HttpServletRequest}
	 * @param  response				{@link HttpServletResponse}
	 * @param  headerForm			{@link HeaderForm}
	 * @param  replyForm			{@link ReplyForm}
	 * @param  result				{@link BindingResult}
	 * @param  model				{@link Model}
	 * @param  redirectAttributes	{@link RedirectAttributes}
	 * @return {@value AppConsts#URL_BLOG_REPLY_FORM}
	 * 			{@value AppConsts#REDIRECT_URL_BLOG_REPLY}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY_COMPLETE)
	public String reply_complete(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)		String cookieUserName,
			@RequestParam(WebConsts.ATTRIBUTE_ID)		int id,
			HttpServletRequest		request,
			HttpServletResponse 	response,
			HeaderForm				headerForm,
			@Validated ReplyForm	replyForm,
			BindingResult			result,
			Model					model,
			RedirectAttributes		redirectAttributes) {
		BlogId blogReplyId = new BlogId(id);
		
		if (result.hasErrors()) {
			// エラー
			
			/** Cookieの設定 */
			this.headerController.setCookie(request, response, cookieLoginId, cookieUserId, cookieUserName);
			/** ヘッダーの設定 */
			this.headerController.setHeader(request, headerForm, model);
			
			// attribute設定
			this.setCommonAttribute(request, headerForm, model);
			this.setReplyFormAttribute(blogReplyId, model);
			return AppConsts.URL_BLOG_REPLY_FORM;
		}
		
		BlogReplyModel blog = new BlogReplyModel(
				new BlogId(id),
				new NameWord(replyForm.getName()),
				new CommentWord(replyForm.getComment()),
				new ThanksCntNumber(0),
				LocalDateTime.now()
				);
		this.blogReplyService.save(blog);
		
		this.setReplyCompleteAttribute(blogReplyId, redirectAttributes);
		return AppConsts.REDIRECT_URL_BLOG_REPLY;
	}
}
