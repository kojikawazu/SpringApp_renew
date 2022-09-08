package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.blog.main.BlogMainId;
import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;

/**
 * ブログいいねプッシュコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogThanksController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	public BlogThanksController(
			BlogMainService  blogMainService, 
			BlogReplyService blogReplyService,
			BlogTagService   blogTagService) {
		super(blogMainService, blogReplyService, blogTagService);
	}
	
	/**
	 * [非同期通信]
	 * いいねプッシュ受信
	 * @param  thanksCnter
	 * @return いいね数
	 */
	@RequestMapping(AppConsts.REQUEST_MAPPING_THANKS)
	@ResponseBody
	public String thanksIncrement2(
			BlogMainId thanksCnter) {
		BlogId id     = new BlogId(thanksCnter.getId());
		int    cnter  = this.blogMainService.thanksIncrement(id);
		if(cnter <= 0)	cnter = 0;
		return String.valueOf(cnter);
	}
}
