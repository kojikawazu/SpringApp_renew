package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.exception.SQLNoDeleteException;
import com.example.demo.common.id.blog.BlogId;

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
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	public BlogDeleteController(
			BlogMainService		blogMainService, 
			BlogReplyService	blogReplyService, 
			BlogTagService		blogTagService,
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		super(blogMainService,
				blogReplyService,
				blogTagService,
				userServiceUse,
				sessionLoginUser);
	}
	
	/**
	 * ブログ削除受信
	 * @param id
	 * @param  headerForm			{@link HeaderForm}
	 * @param model					{@link model}
	 * @param redirectAttributes	{@link RedirectAttributes}
	 * @return {@value AppConsts#REDIRECT_URL_BLOG_INDEX}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_DELETE)
	public String delete(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
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
