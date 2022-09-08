package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.BlogForm;
import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.number.EditorSwitch;

/**
 * ブログ投稿フォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogAddFormController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	public BlogAddFormController(
			BlogMainService  blogMainService, 
			BlogReplyService blogReplyService,
			BlogTagService   blogTagService) {
		super(blogMainService, blogReplyService, blogTagService);
	}
	
	/**
	 * ブログ投稿フォーム受信(Get)
	 * @param  editor
	 * @param  blogForm
	 * @param  model
	 * @param  complete
	 * @return ブログ投稿フォームURL
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String form(
			@RequestParam(value      = WebConsts.ATTRIBUTE_EDITOR, 
						required     = false, 
						defaultValue = WebConsts.ZERO_STRING) int editor,
			BlogForm blogForm,
			Model    model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		EditorSwitch edit = new EditorSwitch(editor);
		this.setAddFormAttribute(edit, model);
		return AppConsts.URL_BLOG_MAIN_FORM;
	}
	
	/**
	 * ブログ投稿フォーム受信(Post)
	 * @param  editor
	 * @param  blogForm
	 * @param  model
	 * @param  complete
	 * @return ブログ投稿フォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String formGoBack(
			@RequestParam(value      = WebConsts.ATTRIBUTE_EDITOR, 
						required     = false, 
						defaultValue = WebConsts.ZERO_STRING) int editor,
			BlogForm blogForm,
			Model    model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		EditorSwitch edit = new EditorSwitch(editor);
		this.setAddFormAttribute(edit, model);
		return AppConsts.URL_BLOG_MAIN_FORM;
	}

}
