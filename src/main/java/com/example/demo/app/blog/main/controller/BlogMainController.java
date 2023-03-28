package com.example.demo.app.blog.main.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.BlogSelectedForm;
import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.blog.BlogMainServiceUse;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
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

	/** 
	 * ブログ並び順
	 * false 昇順 true 降順
	 */
	private boolean descFlg = false;

	/**
	 * コンストラクタ
	 * @param blogMainService		{@link BlogMainServiceUse}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public BlogMainController(
			BlogMainServiceUse		blogMainService, 
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

		// ブログメインリストの降順設定
		this.setPageDesc();
	}

	/**
	 * index
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  pageidx
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
			@RequestParam(value = WebConsts.ATTRIBUTE_PAGE_IDX, 
						required = false, defaultValue = "1") 	int pageidx,
			HttpServletRequest			request,
			HttpServletResponse 		response,
			HeaderForm					headerForm,
			@Validated BlogSelectedForm	blogSelectedForm,
			Model						model) {
		/// Cookieの設定
		this.setInclude(detailUser, request, response, headerForm, model);

		List<BlogMainModel> list = setBlogList(blogSelectedForm);
		this.setPaging(list, pageidx, model);
		this.setSelectTag(model);

		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
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
