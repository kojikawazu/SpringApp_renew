package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.blog.main.form.BlogSelectedForm;
import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;

/**
 * ブログ選択コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogSelectedController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	public BlogSelectedController(
			BlogMainService  blogMainService, 
			BlogReplyService blogReplyService,
			BlogTagService   blogTagService) {
		super(blogMainService, blogReplyService, blogTagService);
	}
	
	/**
	 * ブログ検索受信
	 * @param blogSelectedForm
	 * @param model
	 * @param redirectAttributes
	 * @return リダイレクト:[ブログ一覧]
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_SELECTED)
	public String selectedTag(
			@Validated BlogSelectedForm blogSelectedForm,
			Model              model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute(WebConsts.ATTRIBUTE_SELECT_IDX, 
				blogSelectedForm.getSelectIdx());
		return AppConsts.REDIRECT_URL_BLOG_INDEX;
	}

}
