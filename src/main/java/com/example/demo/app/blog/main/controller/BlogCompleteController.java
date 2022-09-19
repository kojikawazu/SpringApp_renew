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

import com.example.demo.app.blog.main.form.BlogForm;
import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.app.entity.BlogTagModel;
import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.number.EditorSwitch;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

/**
 * ブログ投稿 or 編集実施コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogCompleteController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	public BlogCompleteController(
			BlogMainService  blogMainService, 
			BlogReplyService blogReplyService,
			BlogTagService   blogTagService) {
		super(blogMainService, blogReplyService, blogTagService);
	}
	
	/**
	 * 投稿 or 編集実施受信
	 * @param blogForm
	 * @param editor
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return リダイレクト:[ブログ投稿] or リダイレクト[ブログ編集] or ブログ投稿フォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_COMPLETE)
	public String complete(
			@Validated BlogForm blogForm,
			@RequestParam(WebConsts.ATTRIBUTE_EDITOR) int editor,
			BindingResult result,
			Model         model,
			RedirectAttributes redirectAttributes) {
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
		
		BlogMainModel blog = new BlogMainModel(
				new TittleWord(blogForm.getBlogTitle()),
				new TagWord(blogForm.getTag()),
				new CommentWord(blogForm.getBlogContents()),
				new ThanksCntNumber(0),
				LocalDateTime.now(),
				LocalDateTime.now()
				);
		
		if( !edit.isEdit() ) {
			// 追加作業
			this.executeAdd(blog, redirectAttributes);
			return AppConsts.REDIRECT_URL_BLOG_FORM;
		}else {
			// 編集作業
			this.executeEdit(edit, blogForm, blog, model, redirectAttributes);
			return AppConsts.REDIRECT_URL_BLOG_EDIT;
		}
	}
	
	/**
	 * 投稿作業
	 * @param blog
	 * @param redirectAttributes
	 */
	private void executeAdd(
			BlogMainModel      blog, 
			RedirectAttributes redirectAttributes) {
		this.blogMainService.save(blog);
		this.saveTag(blog);
		
		this.setAddCompleteAttribute(redirectAttributes);
	}
	
	/**
	 * 編集作業
	 * @param edit
	 * @param blogForm
	 * @param blog
	 * @param model
	 * @param redirectAttributes
	 */
	private void executeEdit(
			EditorSwitch       edit, 
			BlogForm           blogForm,
			BlogMainModel      blog,
			Model              model,
			RedirectAttributes redirectAttributes) {
		blog = new BlogMainModel(
				new BlogId(edit.getEditorNumber()),
				new TittleWord(blog.getTitle()),
				new TagWord(blog.getTag()),
				new CommentWord(blog.getComment()),
				new ThanksCntNumber(blogForm.getThanksCnt()),
				blog.getCreated(),
				blog.getUpdated()
				);
		this.blogMainService.update(blog);
		
		this.setEditCompleteAttribute(edit, blogForm, model, redirectAttributes);
	}
	
	/**
	 * タグの保存
	 * @param blog
	 */
	private void saveTag(BlogMainModel blog) {
		String targetTag = blog.getTag();
		if( !this.blogTagService.isSelect_byTag(targetTag) ) {
			// タグなし。タグの追加
			BlogTagModel tagModel = new BlogTagModel(
					new TagWord(targetTag));
			this.blogTagService.save(tagModel);
		}
	}
}
