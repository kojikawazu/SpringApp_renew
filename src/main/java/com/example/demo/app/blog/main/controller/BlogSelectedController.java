package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.blog.main.form.BlogSelectedForm;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
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
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	public BlogSelectedController(
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
	 * ブログ検索受信
	 * @param  blogSelectedForm		{@link HeaderForm}
	 * @param  blogSelectedForm		{@link BlogSelectedForm}
	 * @param  model				{@link Model}
	 * @param  redirectAttributes	{@link RedirectAttributes}
	 * @return {@value AppConsts#REDIRECT_URL_BLOG_INDEX}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_SELECTED)
	public String selectedTag(
			HeaderForm					headerForm,
			@Validated BlogSelectedForm	blogSelectedForm,
			Model						model,
			RedirectAttributes			redirectAttributes) {
		redirectAttributes.addAttribute(WebConsts.ATTRIBUTE_SELECT_IDX, 
				blogSelectedForm.getSelectIdx());
		return AppConsts.REDIRECT_URL_BLOG_INDEX;
	}

}
