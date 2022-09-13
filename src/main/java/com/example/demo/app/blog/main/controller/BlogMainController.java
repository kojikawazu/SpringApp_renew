package com.example.demo.app.blog.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.blog.main.form.BlogSelectedForm;
import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.app.entity.BlogTagModel;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogTagId;

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
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	@Autowired
	public BlogMainController(
			BlogMainService blogMainService, 
			BlogReplyService blogReplyService, 
			BlogTagService blogTagService) {
		super(blogMainService,
				blogReplyService,
				blogTagService);
		
		/** ブログメインリストの降順設定 */
		this.setPageDesc();
	}
	
	/**
	 * index
	 * @param pageidx
	 * @param blogSelectedForm
	 * @param model
	 * @return
	 */
	@GetMapping
	public String index(
			@RequestParam(value = WebConsts.ATTRIBUTE_PAGE_IDX, 
						required = false, defaultValue = "1") int pageidx,
			@Validated BlogSelectedForm blogSelectedForm,
			Model                       model) {
		List<BlogMainModel> list = setBlogList(blogSelectedForm);
		this.setPaging(list, pageidx, model);
		this.setSelectTag(model);
		
		this.setIndexAttribute(model);
		list.clear();
		return AppConsts.URL_BLOG_MAIN_INDEX;
	}
	
	// -----------------------------------------------------------------------------------------
	
	/**
	 * ブログリストの取得
	 * @param  blogSelectedForm
	 * @return ブログメインモデルリスト
	 */
	private List<BlogMainModel> setBlogList(BlogSelectedForm blogSelectedForm) {
		List<BlogMainModel> list      = null;
		int                 selectidx = blogSelectedForm.getSelectIdx();
		if( selectidx == 1 ||  selectidx == 0 ) {
			list = this.blogMainService.getAllPlus(this.descFlg);
		}else {
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
	 * @param list
	 * @param pageidx
	 * @param model
	 */
	private void setPaging(List<BlogMainModel> list, int pageidx, Model model) {
		PageController      page         = new PageController();
		List<BlogMainModel> blogpageList = page.setPaging(list, pageidx, BLOG_PAGE_MAX);
		
		page.setPageName(AppConsts.REQUEST_MAPPING_BLOG);
		
		model.addAttribute(AppConsts.ATTRIBUTE_BLOG_MAIN_LIST, blogpageList);
		model.addAttribute(WebConsts.ATTRIBUTE_PAGING,         page);
	}
	
	/**
	 * タグリストの設定
	 * @param model
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
