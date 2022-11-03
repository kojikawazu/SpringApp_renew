package com.example.demo.app.inquiry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.common.SuperInquiryController;
import com.example.demo.app.inquiry.form.InquiryReplyForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.inquiry.InquiryId;
import com.example.demo.common.log.LogMessage;

/**
 * 問い合わせ返信フォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryReplyFormController extends SuperInquiryController {
	
	/**
	 * コンストラクタ
	 * @param inquiryService		{@link InquiryService}
	 * @param inquiryReplyService	{@link InquiryReplyService}
	 * @param userService			{@link UserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public InquiryReplyFormController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService,
			UserServiceUse 		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			HttpSession			httpSession,
			LogMessage			logMessage) {
		super(inquiryService, 
				inquiryReplyService, 
				userService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/**
	 * 問い合わせ返信受信(Post)
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  id
	 * @param  request			{@link HttpServletRequest}
	 * @param  response 		{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  inquiryReplyForm	{@link InquiryReplyForm}
	 * @param  model			{@link Model}
	 * @param  complete			結果メッセージ
	 * @return {@value AppConsts#URL_INQUIRY_REPLY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String reply(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)		String cookieUserName,
			@RequestParam(WebConsts.ATTRIBUTE_ID) 		int id,
			HttpServletRequest	request,
			HttpServletResponse response,
			HeaderForm			headerForm,
			InquiryReplyForm	inquiryReplyForm,
			Model				model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete
			) {
		/** Cookieの設定 */
		this.getHeaderController().setCookie(request, response, cookieLoginId, cookieUserId, cookieUserName);
		/** ヘッダーの設定 */
		this.getHeaderController().setHeader(request, headerForm, model);
		
		this.setReply(new InquiryId(id), model);
		
		// attribute設定
		this.setCommonAttribute(request, headerForm, model);
		this.setReplyFormAttribute(model);
		return AppConsts.URL_INQUIRY_REPLY_FORM;
	}
	
	/**
	 * 問い合わせ返信受信(Get)
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  id
	 * @param  request			{@link HttpServletRequest}
	 * @param  response 		{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  inquiryReplyForm	{@link InquiryReplyForm}
	 * @param  model			{@link Model}
	 * @param  complete			結果メッセージ
	 * @return {@value AppConsts#URL_INQUIRY_REPLY_FORM}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String replyGet(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,	
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,		
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,	
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)		String cookieUserName,
			@ModelAttribute(WebConsts.ATTRIBUTE_ID)       int		id,
			HttpServletRequest	request,
			HttpServletResponse response,
			HeaderForm			headerForm,
			InquiryReplyForm	inquiryReplyForm,
			Model				model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String	complete
			) {
		/** Cookieの設定 */
		this.getHeaderController().setCookie(request, response, cookieLoginId, cookieUserId, cookieUserName);
		/** ヘッダーの設定 */
		this.getHeaderController().setHeader(request, headerForm, model);
		
		this.setReply(new InquiryId(id), model);
		
		// attribute設定
		this.setCommonAttribute(request, headerForm, model);
		this.setReplyFormAttribute(model);
		return AppConsts.URL_INQUIRY_REPLY_FORM;
	}
}
