package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.ReplyForm;
import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogId;

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
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	public BlogReplyController(
			BlogMainService  blogMainService, 
			BlogReplyService blogReplyService,
			BlogTagService   blogTagService) {
		super(blogMainService, blogReplyService, blogTagService);
	}
	
	/**
	 * ブログ返信フォーム受信(Post)
	 * @param  replyid
	 * @param  replyForm
	 * @param  model
	 * @param  complete
	 * @return ブログ返信フォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String reply(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int replyid,
			ReplyForm replyForm,
			Model     model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		BlogId blogReplyId = new BlogId(replyid);
		this.setReplyFormAttribute(blogReplyId, model);
		return AppConsts.URL_BLOG_REPLY_FORM;
	}
	
	/**
	 * ブログ返信フォーム受信(Get)
	 * @param  replyid
	 * @param  replyForm
	 * @param  model
	 * @param  complete
	 * @return ブログ返信フォームURL
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String replyGet(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int replyid,
			ReplyForm replyForm,
			Model     model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		BlogId blogReplyId = new BlogId(replyid);
		this.setReplyFormAttribute(blogReplyId, model);
		return AppConsts.URL_BLOG_REPLY_FORM;
	}
	
}
