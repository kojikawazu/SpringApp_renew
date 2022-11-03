package com.example.demo.app.inquiry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.common.SuperInquiryController;
import com.example.demo.app.inquiry.form.InquiryForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;

/**
 * 問い合わせ確認コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryConfirmController extends SuperInquiryController {
	
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
	public InquiryConfirmController(
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
	 * 問い合わせ確認受信
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  inquiryForm  	{@link InquiryForm}
	 * @param  result			{@link BindingResult}
	 * @param  model			{@link Model}
	 * @return 	{@value AppConsts#URL_INQUIRY_FORM}
	 * 			{@value AppConsts#URL_INQUIRY_CONFIRM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_CONFIRM)
	public String confirm(
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
			HttpServletResponse 	response,
			HeaderForm 				headerForm,
			@Validated InquiryForm	inquiryForm,
			BindingResult			result,
			Model					model) {
		/** Cookieの設定 */
		this.getHeaderController().setCookie(request, response, cookieLoginId, cookieUserId, cookieUserName);
		/** ヘッダーの設定 */
		this.getHeaderController().setHeader(request, headerForm, model);
		
		if(result.hasErrors()) {
			// バリデートエラー, フォーム画面へ
			// attribute設定
			this.setCommonAttribute(request, headerForm, model);
			this.setFormAttribute(inquiryForm, model);
			return AppConsts.URL_INQUIRY_FORM;
		}
		// バリデートOK 確認画面へ
		// attribute設定
		this.setCommonAttribute(request, headerForm, model);
		this.setConfirmAttribute(model);
		return AppConsts.URL_INQUIRY_CONFIRM;
	}
	
}
