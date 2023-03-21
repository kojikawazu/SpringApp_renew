package com.example.demo.app.blog.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.blog.main.BlogMainId;
import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.service.blog.BlogMainService;
import com.example.demo.app.service.blog.BlogReplyService;
import com.example.demo.app.service.blog.BlogTagService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.log.LogMessage;

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
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	public BlogThanksController(
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
