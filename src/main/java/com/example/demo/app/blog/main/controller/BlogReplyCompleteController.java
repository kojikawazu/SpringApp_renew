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
import com.example.demo.app.entity.BlogReplyModel;
import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
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
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	public BlogReplyCompleteController(
			BlogMainService  blogMainService, 
			BlogReplyService blogReplyService,
			BlogTagService   blogTagService) {
		super(blogMainService, blogReplyService, blogTagService);
	}
	
	/**
	 * ブログ返信成功受信
	 * @param id
	 * @param replyForm
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return リダイレクト:[ブログ返信フォーム] or ブログ返信フォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY_COMPLETE)
	public String reply_complete(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			@Validated ReplyForm replyForm,
			BindingResult        result,
			Model                model,
			RedirectAttributes   redirectAttributes) {
		BlogId blogReplyId = new BlogId(id);
		if(result.hasErrors()) {
			// エラー
			this.setReplyFormAttribute(blogReplyId, model);
			return AppConsts.URL_BLOG_REPLY_FORM;
		}
		
		BlogReplyModel blog = new BlogReplyModel(
				new BlogId(id),
				new NameWord(replyForm.getName()),
				new NameWord(replyForm.getComment()),
				new ThanksCntNumber(0),
				LocalDateTime.now()
				);
		this.blogReplyService.save(blog);
		
		this.setReplyCompleteAttribute(blogReplyId, redirectAttributes);
		return AppConsts.REDIRECT_URL_BLOG_REPLY;
	}
}
