package com.example.demo.app.blog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.blog.main.BlogMainId;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.id.blog.BlogId;

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
	 * @param blogMainService		{@link BlogMainService}
	 * @param blogReplyService		{@link BlogReplyService}
	 * @param blogTagService		{@link BlogTagService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	public BlogThanksController(
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
	 * [非同期通信]
	 * いいねプッシュ受信
	 * @param  thanksCnter	{@link BlogMainId}
	 * @return いいね数
	 */
	@RequestMapping(AppConsts.REQUEST_MAPPING_THANKS)
	@ResponseBody
	public String thanksIncrement2(
			BlogMainId thanksCnter) {
		BlogId id     = new BlogId(thanksCnter.getId());
		int    cnter  = this.blogMainService.thanksIncrement(id);
		if (cnter <= 0)	cnter = 0;
		return String.valueOf(cnter);
	}
}
