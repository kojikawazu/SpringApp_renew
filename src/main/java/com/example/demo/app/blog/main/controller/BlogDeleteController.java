package com.example.demo.app.blog.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.exception.SQLNoDeleteException;
import com.example.demo.common.log.LogMessage;

/**
 * ブログ削除フォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogDeleteController extends SuperBlogMainController {

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
	public BlogDeleteController(
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
	 * ブログ削除受信
	 * @param id
	 * @param request				{@link HttpServletRequest}
	 * @param  headerForm			{@link HeaderForm}
	 * @param model					{@link model}
	 * @param redirectAttributes	{@link RedirectAttributes}
	 * @return {@value AppConsts#REDIRECT_URL_BLOG_INDEX}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_DELETE)
	public String delete(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			HttpServletRequest	request,
			HeaderForm			headerForm,
			Model				model,
			RedirectAttributes	redirectAttributes) {
		BlogId blogId = new BlogId(id);
		
		try {
			this.blogReplyService.delete_byBlogid(blogId);
		} catch(SQLNoDeleteException ex) {
			// DeleteできなくてもOK
		}
		this.blogMainService.delete(blogId);
		
		return AppConsts.REDIRECT_URL_BLOG_INDEX;
	}
}
