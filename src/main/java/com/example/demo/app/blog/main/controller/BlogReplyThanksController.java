package com.example.demo.app.blog.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.blog.main.BlogMainId;
import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.service.blog.BlogMainServiceUse;
import com.example.demo.app.service.blog.BlogReplyServiceUse;
import com.example.demo.app.service.blog.BlogTagServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.log.LogMessage;

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
	 * @param blogMainService		{@link BlogMainServiceUse}
	 * @param blogReplyService		{@link BlogReplyServiceUse}
	 * @param blogTagService		{@link BlogTagServiceUse}
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	public BlogReplyThanksController(
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
	
	/**
	 * [非同期通信]
	 * 返信いいねプッシュ受信
	 * @param  thanksCnter {@link BlogMainId}
	 * @return いいね数
	 */
	@RequestMapping(AppConsts.REQUEST_MAPPING_REPLY_THANKS)
	@ResponseBody
	public String replyThanksIncrement(
			BlogMainId thanksCnter) {
		BlogReplyId id     = new BlogReplyId(thanksCnter.getId());
		int         cnter  = this.blogReplyService.thanksIncrement(id);
		if (cnter <= 0)	cnter = 0;
		return String.valueOf(cnter);
	}
}
