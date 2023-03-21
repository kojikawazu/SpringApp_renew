package com.example.demo.app.blog.main.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.blog.main.form.BlogForm;
import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.number.EditorSwitch;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
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
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	public BlogCompleteController(
			BlogMainService			blogMainService, 
			BlogReplyService		blogReplyService, 
			BlogTagService			blogTagService,
			SecUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		super(blogMainService,
				blogReplyService,
				blogTagService,
				secUserService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/**
	 * 投稿 or 編集実施受信
	 * @param  detailUser			{@link SecLoginUserDetails}
	 * @param  editor
	 * @param  request				{@link HttpServletRequest}
	 * @param  response				{@link HttpServletResponse}
	 * @param  headerForm			{@link HeaderForm}
	 * @param  blogForm				{@link BlogForm}
	 * @param  result				{@link BindingResult}
	 * @param  model				{@link Model}
	 * @param  redirectAttributes	{@link RedirectAttributes}
	 * @return {@value AppConsts#URL_BLOG_MAIN_FORM}
	 * 			{@value AppConsts#REDIRECT_URL_BLOG_FORM}
	 * 			{@value AppConsts#REDIRECT_URL_BLOG_EDIT}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_COMPLETE)
	public String complete(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			@RequestParam(WebConsts.ATTRIBUTE_EDITOR)		int editor,
			HttpServletRequest	request,
			HttpServletResponse response,
			HeaderForm			headerForm,
			@Validated BlogForm	blogForm,
			BindingResult		result,
			Model				model,
			RedirectAttributes	redirectAttributes) {
		EditorSwitch edit = new EditorSwitch(editor);
		
		if (result.hasErrors()) {
			// エラー
			
			/** Cookieの設定 */
			this.setInclude(detailUser, request, response, headerForm, model);
			
			// attribute設定
			this.setCommonAttribute(detailUser, request, response, headerForm, model);
			if (edit.isEdit()) {
				this.setEditorAttribute(edit, blogForm, model);
			} else {
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
		
		if (!edit.isEdit()) {
			// 追加作業
			this.executeAdd(blog, redirectAttributes);
			return AppConsts.REDIRECT_URL_BLOG_FORM;
		} else {
			// 編集作業
			this.executeEdit(edit, blogForm, blog, model, redirectAttributes);
			return AppConsts.REDIRECT_URL_BLOG_EDIT;
		}
	}
	
	/**
	 * 投稿作業
	 * @param blog					{@link BlogMainModel}
	 * @param redirectAttributes	{@link RedirectAttributes}
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
	 * @param edit					{@link EditorSwitch}
	 * @param blogForm				{@link BlogForm}
	 * @param blog					{@link BlogMainModel}
	 * @param model					{@link Model}
	 * @param redirectAttributes	{@link RedirectAttributes}
	 */
	private void executeEdit(
			EditorSwitch		edit, 
			BlogForm			blogForm,
			BlogMainModel		blog,
			Model				model,
			RedirectAttributes	redirectAttributes) {
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
	 * @param blog {@link BlogMainModel}
	 */
	private void saveTag(BlogMainModel blog) {
		String targetTag = blog.getTag();
		if (!this.blogTagService.isSelect_byTag(targetTag)) {
			// タグなし。タグの追加
			BlogTagModel tagModel = new BlogTagModel(
					new TagWord(targetTag));
			this.blogTagService.save(tagModel);
		}
	}
}
