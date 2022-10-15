package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.ReplyForm;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.blog.BlogId;

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
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	public BlogReplyConfirmController(
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
	 * ブログ返信確認受信
	 * @param  id
	 * @param  headerForm	{@link HeaderForm}
	 * @param  replyForm	{@link ReplyForm}
	 * @param  result		{@link BindingResult}
	 * @param  model		{@link Model}
	 * @return {@value AppConsts#URL_BLOG_REPLY_FORM}
	 * 			{@value AppConsts#URL_BLOG_REPLY_CONFIRM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY_CONFIRM)
	public String reply_confirm(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			HeaderForm				headerForm,
			@Validated ReplyForm	replyForm,
			BindingResult			result,
			Model					model) {
		BlogId blogReplyId = new BlogId(id);
		if (result.hasErrors()) {
			// エラー
			// attribute設定
			this.setCommonAttribute(model);
			this.setReplyFormAttribute(blogReplyId, model);
			return AppConsts.URL_BLOG_REPLY_FORM;
		}
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setReplyConfirmAttribute(blogReplyId, model);		
		return AppConsts.URL_BLOG_REPLY_CONFIRM;
	}
}
