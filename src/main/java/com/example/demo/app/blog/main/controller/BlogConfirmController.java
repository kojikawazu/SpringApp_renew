package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
 * ブログ投稿 or 編集確認コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogConfirmController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	public BlogConfirmController(
			BlogMainService  blogMainService, 
			BlogReplyService blogReplyService,
			BlogTagService   blogTagService) {
		super(blogMainService, blogReplyService, blogTagService);
	}
	
	/**
	 * 確認画面受信
	 * @param editor
	 * @param blogForm
	 * @param result
	 * @param model
	 * @return ブログ確認URL or ブログフォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_CONFIRM)
	public String confirm(
			@RequestParam(WebConsts.ATTRIBUTE_EDITOR) int editor,
			@Validated BlogForm blogForm,
			BindingResult       result,
			Model               model) {
		EditorSwitch edit = new EditorSwitch(editor);
		
		if(result.hasErrors()) {
			// エラー
			if( edit.isEdit() ) {
				this.setEditorAttribute(edit, blogForm, model);
			}else {
				this.setAddFormAttribute(edit, model);
			}
			return AppConsts.URL_BLOG_MAIN_FORM;
		}
		
		this.setConfirmAttribute(edit, model);
		return AppConsts.URL_BLOG_MAIN_CONFIRM;
	}
	
}
