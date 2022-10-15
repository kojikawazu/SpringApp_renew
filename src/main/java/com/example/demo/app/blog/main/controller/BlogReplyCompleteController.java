package com.example.demo.app.blog.main.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.blog.BlogId;
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
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	public BlogReplyCompleteController(
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
	 * ブログ返信成功受信
	 * @param id
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
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			HeaderForm				headerForm,
			@Validated ReplyForm	replyForm,
			BindingResult			result,
			Model					model,
			RedirectAttributes		redirectAttributes) {
		BlogId blogReplyId = new BlogId(id);
		if (result.hasErrors()) {
			// エラー
			// attribute設定
			this.setCommonAttribute(model);
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
