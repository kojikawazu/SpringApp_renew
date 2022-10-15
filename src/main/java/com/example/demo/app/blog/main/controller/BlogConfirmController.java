package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
 * ブログ投稿 or 編集確認コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogConfirmController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	public BlogConfirmController(
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
	 * 確認画面受信
	 * @param  editor
	 * @param  headerForm		{@link HeaderForm}
	 * @param  blogForm			{@link BlogForm}
	 * @param  result			{@link BindingResult}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_BLOG_MAIN_FORM}
	 * 			{@value AppConsts#URL_BLOG_MAIN_CONFIRM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_CONFIRM)
	public String confirm(
			@RequestParam(WebConsts.ATTRIBUTE_EDITOR) int editor,
			HeaderForm			headerForm,
			@Validated BlogForm	blogForm,
			BindingResult		result,
			Model				model) {
		EditorSwitch edit = new EditorSwitch(editor);
		
		if (result.hasErrors()) {
			// エラー
			// attribute設定
			this.setCommonAttribute(model);
			if (edit.isEdit()) {
				this.setEditorAttribute(edit, blogForm, model);
			} else {
				this.setAddFormAttribute(edit, model);
			}
			return AppConsts.URL_BLOG_MAIN_FORM;
		}
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setConfirmAttribute(edit, model);
		return AppConsts.URL_BLOG_MAIN_CONFIRM;
	}
	
}
