package com.example.demo.app.blog.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.blog.main.form.BlogSelectedForm;
import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.blog.BlogMainServiceUse;
import com.example.demo.app.service.blog.BlogReplyServiceUse;
import com.example.demo.app.service.blog.BlogTagServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.encrypt.CommonEncrypt;
import com.example.demo.common.log.LogMessage;

/**
 * ブログメインコントローラー
 * <br>
 * extends {@link SuperBlogMainController}
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogMainController extends SuperBlogMainController {

	/** ブログ一覧ページの最大数 */
	private static final int BLOG_PAGE_MAX = 5;

	// --------------------------------------------------------------------------------------------------------------------------

	/**
	 * コンストラクタ
	 * @param blogMainService		{@link BlogMainServiceUse}
	 * @param blogReplyService		{@link BlogReplyServiceUse}
	 * @param blogTagService		{@link BlogTagServiceUse}
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public BlogMainController(
			BlogMainServiceUse		blogMainService, 
			BlogReplyServiceUse		blogReplyService, 
			BlogTagServiceUse		blogTagService,
			SecUserServiceUse		secUserService,
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

	// --------------------------------------------------------------------------------------------------------------------------

	/**
	 * index
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  blogSelectedForm	{@link BlogSelectedForm}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_BLOG_MAIN_INDEX}
	 */
	@GetMapping
	public String index(
			@AuthenticationPrincipal SecLoginUserDetails		detailUser,
			HttpServletRequest			request,
			HttpServletResponse 		response,
			HeaderForm					headerForm,
			@Validated BlogSelectedForm	blogSelectedForm,
			Model						model) {
		String result = "";
		result = this.execute(detailUser, request, response, headerForm, blogSelectedForm, model);
		return result;
	}

	/**
	 * index
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  blogSelectedForm	{@link BlogSelectedForm}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_BLOG_MAIN_INDEX}
	 */
	@PostMapping
	public String indexPost(
			@AuthenticationPrincipal SecLoginUserDetails		detailUser,
			HttpServletRequest			request,
			HttpServletResponse 		response,
			HeaderForm					headerForm,
			@Validated BlogSelectedForm	blogSelectedForm,
			Model						model) {
		String result = "";
		result = this.execute(detailUser, request, response, headerForm, blogSelectedForm, model);
		return result;
	}

	// --------------------------------------------------------------------------------------------------------------------------

	/**
	 * 受信処理
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  blogSelectedForm	{@link BlogSelectedForm}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_BLOG_MAIN_INDEX}
	 */
	private String execute(
			SecLoginUserDetails			detailUser,
			HttpServletRequest			request,
			HttpServletResponse 		response,
			HeaderForm					headerForm,
			BlogSelectedForm			blogSelectedForm,
			Model						model) {
		/// includeの設定
		this.setInclude(detailUser, request, response, headerForm, model);

		// ブログモデルの取得
		List<BlogMainModel> list = this.getBlogList(blogSelectedForm);
		// ページング設定
		this.setPaging(list, blogSelectedForm);
		// タグリストの設定
		this.setTagList();

		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
		this.setIndexAttribute(blogSelectedForm);
		list.clear();

		return AppConsts.URL_BLOG_MAIN_INDEX;
	}
	
	// -----------------------------------------------------------------------------------------

	/**
	 * ブログリストの取得
	 * @param  blogSelectedForm {@link BlogSelectedForm}
	 * @return {@link List}<{@link BlogMainModel}>
	 */
	private List<BlogMainModel> getBlogList(BlogSelectedForm blogSelectedForm) {
		if (blogSelectedForm == null)	return new ArrayList<BlogMainModel>();

		List<BlogMainModel> list 		= null;
		int 				selectidx 	= blogSelectedForm.getSelectIdxDecrypted();
		boolean				isDesc		= blogSelectedForm.getIsDescDecrypted();
		if (selectidx == 1 ||  selectidx == 0) {
			list = this.blogMainService.getAllPlus(isDesc);
		} else {
			BlogTagModel tagModel = this.blogTagService.select(new BlogTagId(selectidx));
			list = this.blogMainService.select_byTagPlus(
					tagModel.getTag(), 
					isDesc);
		}

		return list;
	}

	/**
	 * ページング設定
	 * @param list				{@link List}<{@link BlogMainModel}>
	 * @param  blogSelectedForm	{@link BlogSelectedForm}
	 */
	private void setPaging(
			List<BlogMainModel> list, 
			BlogSelectedForm	blogSelectedForm) {
		if (list == null || blogSelectedForm == null)	return;

		int                 pageIdx      = blogSelectedForm.getPagingIdx();
		PageController      page         = new PageController();
		List<BlogMainModel> blogpageList = page.setPaging(list, pageIdx, BLOG_PAGE_MAX);
		page.setPageName(AppConsts.REQUEST_MAPPING_BLOG);

		this.getModel().addAttribute(AppConsts.ATTRIBUTE_BLOG_MAIN_LIST,	blogpageList);
		this.getModel().addAttribute(WebConsts.ATTRIBUTE_PAGING,			page);
	}

	/**
	 * タグリストの設定
	 */
	private void setTagList() {
		List<BlogTagModel> list = this.blogTagService.getAll();
		this.getModel().addAttribute(AppConsts.ATTRIBUTE_TAG_LIST, list);
	}
}
