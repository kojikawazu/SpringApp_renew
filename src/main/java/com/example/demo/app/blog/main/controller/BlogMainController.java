package com.example.demo.app.blog.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.BlogSelectedForm;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.blog.BlogTagId;
import com.example.demo.common.log.LogMessage;

/**
 * ブログメインコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogMainController extends SuperBlogMainController {

	/** ブログ一覧ページの最大数 */
	private static final int BLOG_PAGE_MAX = 5;
	
	/** 
	 * ブログ並び順
	 * false 昇順 true 降順
	 */
	private boolean descFlg = false;
	
	/**
	 * コンストラクタ
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param userService			{@link UserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public BlogMainController(
			BlogMainService		blogMainService, 
			BlogReplyService	blogReplyService, 
			BlogTagService		blogTagService,
			UserServiceUse 		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			HttpSession			httpSession,
			LogMessage			logMessage) {
		super(blogMainService,
				blogReplyService,
				blogTagService,
				userService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
		
		/** ブログメインリストの降順設定 */
		this.setPageDesc();
	}
	
	/**
	 * index
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  pageidx
	 * @param request			{@link HttpServletRequest}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  blogSelectedForm	{@link BlogSelectedForm}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_BLOG_MAIN_INDEX}
	 */
	@GetMapping
	public String index(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
			required=false, 
			defaultValue=WebConsts.COOKIE_ZERO)					String cookieLoginId,
		@CookieValue(name=WebConsts.COOKIE_USER_ID,
			required=false, 
			defaultValue=WebConsts.COOKIE_ZERO)					String cookieUserId,
		@CookieValue(name=WebConsts.COOKIE_USER_NAME,
			required=false, 
			defaultValue=WebConsts.COOKIE_NONE)					String cookieUserName,
			@RequestParam(value = WebConsts.ATTRIBUTE_PAGE_IDX, 
						required = false, defaultValue = "1") 	int pageidx,
			HttpServletRequest			request,
			HeaderForm					headerForm,
			@Validated BlogSelectedForm	blogSelectedForm,
			Model						model) {
		/** Cookieの設定 */
		this.headerController.setCookie(cookieLoginId, cookieUserId, cookieUserName);
		
		List<BlogMainModel> list = setBlogList(blogSelectedForm);
		this.setPaging(list, pageidx, model);
		this.setSelectTag(model);
		
		/** ヘッダーの設定 */
		this.headerController.setHeader(request, headerForm, model);
		
		// attribute設定
		this.setCommonAttribute(request, headerForm, model);
		this.setIndexAttribute(model);
		list.clear();
		return AppConsts.URL_BLOG_MAIN_INDEX;
	}
	
	// -----------------------------------------------------------------------------------------
	
	/**
	 * ブログリストの取得
	 * @param  blogSelectedForm {@link BlogSelectedForm}
	 * @return List({@link BlogMainModel})
	 */
	private List<BlogMainModel> setBlogList(
			BlogSelectedForm blogSelectedForm) {
		List<BlogMainModel> list      = null;
		int                 selectidx = blogSelectedForm.getSelectIdx();
		if (selectidx == 1 ||  selectidx == 0) {
			list = this.blogMainService.getAllPlus(this.descFlg);
		} else {
			BlogTagModel tagModel = this.blogTagService.select(
					new BlogTagId(selectidx));
			list = this.blogMainService.select_byTagPlus(
					tagModel.getTag(), 
					this.descFlg);
		}
		return list;
	}
	
	/**
	 * ページング設定
	 * @param list		List({@link BlogMainModel})
	 * @param pageidx
	 * @param model		{@link Model}
	 */
	private void setPaging(
			List<BlogMainModel> list, 
			int pageidx, 
			Model model) {
		PageController      page         = new PageController();
		List<BlogMainModel> blogpageList = page.setPaging(list, pageidx, BLOG_PAGE_MAX);
		
		page.setPageName(AppConsts.REQUEST_MAPPING_BLOG);
		
		model.addAttribute(AppConsts.ATTRIBUTE_BLOG_MAIN_LIST, blogpageList);
		model.addAttribute(WebConsts.ATTRIBUTE_PAGING,         page);
	}
	
	/**
	 * タグリストの設定
	 * @param model	{@link Model}
	 */
	private void setSelectTag(Model model) {
		List<BlogTagModel> list = this.blogTagService.getAll();
		model.addAttribute(AppConsts.ATTRIBUTE_TAG_LIST, list);
	}
	
	/**
	 * 並び順を降順に設定
	 */
	private void setPageDesc() {
		this.descFlg = true;
	}
	
	/**
	 * 並び順を昇順に設定
	 */
	private void setPageAsc() {
		this.descFlg = false;
	}
}
