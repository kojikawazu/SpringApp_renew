package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.exception.SQLNoDeleteException;
import com.example.demo.common.id.BlogId;

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
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	public BlogDeleteController(
			BlogMainService  blogMainService, 
			BlogReplyService blogReplyService,
			BlogTagService   blogTagService) {
		super(blogMainService, blogReplyService, blogTagService);
	}
	
	/**
	 * ブログ削除受信
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return redirect:[ブログ一覧URL]
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_DELETE)
	public String delete(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			Model              model,
			RedirectAttributes redirectAttributes) {
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
