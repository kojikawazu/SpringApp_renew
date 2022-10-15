package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.BlogForm;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.number.EditorSwitch;

/**
 * ブログ編集フォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogEditFormController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	public BlogEditFormController(
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
	 * ブログ編集フォーム受信(Get)
	 * @param  editor
	 * @param  headerForm	{@link HeaderForm}
	 * @param  blogForm		{@link BlogForm}
	 * @param  model		{@link Model}
	 * @param  complete
	 * @return {@value AppConsts#URL_BLOG_MAIN_FORM}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_EDIT)
	public String editGet(
			@RequestParam(value  = WebConsts.ATTRIBUTE_EDITOR, 
					required     = false, 
					defaultValue = WebConsts.ZERO_STRING) int editor,
			HeaderForm		headerForm,
			BlogForm		blogForm,
			Model			model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		EditorSwitch edit = new EditorSwitch(editor);
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setEditorAttribute(edit, blogForm, model);
		return AppConsts.URL_BLOG_MAIN_FORM;
	}
	
	/**
	 * ブログ編集フォーム受信(Post)
	 * @param  editor
	 * @param  headerForm	{@link HeaderForm}
	 * @param  blogForm		{@link BlogForm}
	 * @param  model		{@link Model}
	 * @param  complete
	 * @return {@value AppConsts#URL_BLOG_MAIN_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_EDIT)
	public String edit(
			@RequestParam(value  = WebConsts.ATTRIBUTE_EDITOR, 
					required     = false, 
					defaultValue = WebConsts.ZERO_STRING) int editor,
			HeaderForm		headerForm,
			BlogForm		blogForm,
			Model			model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		EditorSwitch edit = new EditorSwitch(editor);
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setEditorAttribute(edit, blogForm, model);
		return AppConsts.URL_BLOG_MAIN_FORM;
	}
}
