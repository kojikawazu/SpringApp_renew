package com.example.demo.app.inquiry.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.inquiry.InquiryModel;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.form.InquiryForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ投稿成功コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryCompleteController extends SuperInquiryController {
	
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
	public InquiryCompleteController(
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
	
	/** --------------------------------------------------------------- */

	/**
	 * 問い合わせ成功受信
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  request				{@link HttpServletRequest}
	 * @param  headerForm			{@link HeaderForm}
	 * @param  inquiryForm			{@link InquiryForm}
	 * @param  result				{@link BindingResult}
	 * @param  model				{@link Model}
	 * @param  redirectAttributes	{@link RedirectAttributes}
	 * @return	{@value AppConsts#URL_INQUIRY_FORM}
	 * 			{@value AppConsts#REDIRECT_URL_INQUIRY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_COMPLETE)
	public String complete(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)		String cookieUserName,
			HttpServletRequest		request,
			HeaderForm 				headerForm,
			@Validated InquiryForm	inquiryForm,
			BindingResult			result,
			Model					model,
			RedirectAttributes		redirectAttributes) {
		if(result.hasErrors()) {
			// エラー
			
			/** Cookieの設定 */
			this.headerController.setCookie(cookieLoginId, cookieUserId, cookieUserName);
			/** ヘッダーの設定 */
			this.headerController.setHeader(request, headerForm, model);
			// attribute設定
			this.setCommonAttribute(request, headerForm, model);
			this.setFormAttribute(model);
			return AppConsts.URL_INQUIRY_FORM;
		}
		
		InquiryModel inquiry = new InquiryModel(
				new NameWord(inquiryForm.getName()),
				new EmailWord(inquiryForm.getEmail()),
				new CommentWord(inquiryForm.getComment()),
				LocalDateTime.now()
				);
		
		// 問い合わせデータ保存
		this.inquiryService.save(inquiry);
		
		// redirectAttribute設定
		this.setRedirectCompleteAttribute(redirectAttributes);
		return AppConsts.REDIRECT_URL_INQUIRY_FORM;
	}
	
}
