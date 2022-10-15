package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
 * ブログ返信フォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogReplyController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	public BlogReplyController(
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
	 * ブログ返信フォーム受信(Post)
	 * @param  replyid
	 * @param  headerForm		{@link HeaderForm}
	 * @param  replyForm		{@link ReplyForm}
	 * @param  model			{@link Model}
	 * @param  complete
	 * @return {@value AppConsts#URL_BLOG_REPLY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String reply(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int replyid,
			HeaderForm		headerForm,
			ReplyForm		replyForm,
			Model			model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		BlogId blogReplyId = new BlogId(replyid);
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setReplyFormAttribute(blogReplyId, model);
		return AppConsts.URL_BLOG_REPLY_FORM;
	}
	
	/**
	 * ブログ返信フォーム受信(Get)
	 * @param  replyid
	 * @param  headerForm		{@link HeaderForm}
	 * @param  replyForm		{@link ReplyForm}
	 * @param  model			{@link Model}
	 * @param  complete
	 * @return {@value AppConsts#URL_BLOG_REPLY_FORM}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String replyGet(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int replyid,
			HeaderForm		headerForm,
			ReplyForm		replyForm,
			Model			model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		BlogId blogReplyId = new BlogId(replyid);
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setReplyFormAttribute(blogReplyId, model);
		return AppConsts.URL_BLOG_REPLY_FORM;
	}
	
}
