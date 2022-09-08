package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.blog.main.BlogMainId;
import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.id.BlogReplyId;

/**
 * ブログ返信いいねプッシュコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_BLOG)
public class BlogReplyThanksController extends SuperBlogMainController {

	/**
	 * コンストラクタ
	 * @param blogMainService
	 * @param blogReplyService
	 * @param blogTagService
	 */
	public BlogReplyThanksController(
			BlogMainService  blogMainService, 
			BlogReplyService blogReplyService,
			BlogTagService   blogTagService) {
		super(blogMainService, blogReplyService, blogTagService);
	}
	
	/**
	 * [非同期通信]
	 * 返信いいねプッシュ受信
	 * @param  thanksCnter
	 * @return いいね数
	 */
	@RequestMapping(AppConsts.REQUEST_MAPPING_REPLY_THANKS)
	@ResponseBody
	public String replyThanksIncrement(
			BlogMainId thanksCnter) {
		BlogReplyId id     = new BlogReplyId(thanksCnter.getId());
		int         cnter  = this.blogReplyService.thanksIncrement(id);
		if(cnter <= 0)	cnter = 0;
		return String.valueOf(cnter);
	}
}
