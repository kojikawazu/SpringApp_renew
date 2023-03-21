package com.example.demo.app.inquiry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.common.SuperInquiryController;
import com.example.demo.app.inquiry.form.InquiryReplyForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;

/**
 * 問い合わせ返信確認コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryReplyConfirmController extends SuperInquiryController {
	
	/**
	 * コンストラクタ
	 * @param inquiryService		{@link InquiryService}
	 * @param inquiryReplyService	{@link InquiryReplyService}
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public InquiryReplyConfirmController(
			InquiryService      	inquiryService, 
			InquiryReplyService 	inquiryReplyService,
			SecUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		super(inquiryService, 
				inquiryReplyService, 
				secUserService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}

	/**
	 * 問い合わせ返信確認受信
	 * @param  detailUser		{@link SecLoginUserDetails}
	 * @param  id
	 * @param  request			{@link HttpServletRequest}
	 * @param  response			{@link HttpServletResponse}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  inquiryReplyForm	{@link InquiryReplyForm}
	 * @param  result			{@link BindingResult}
	 * @param  model			{@link Model}
	 * @return	{@value AppConsts#URL_INQUIRY_REPLY_FORM}
	 * 			{@value AppConsts#URL_INQUIRY_REPLY_CONFIRM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY_CONFIRM)
	public String reply_confirm(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			@RequestParam(WebConsts.ATTRIBUTE_ID) 			int id,
			HttpServletRequest								request,
			HttpServletResponse 							response,
			HeaderForm 										headerForm,
			@Validated InquiryReplyForm						inquiryReplyForm,
			BindingResult									result,
			Model											model) {
		// Cookieの設定
		this.setInclude(detailUser, request, response, headerForm, model);
		
		// 返信対象設定
		this.setReply(new InquiryId(id), model);
		
		if(result.hasErrors()) {
			// バリデートエラー。フォーム画面へ
			// attribute設定
			this.setCommonAttribute(detailUser, request, response, headerForm, model);
			this.setReplyFormAttribute(detailUser, inquiryReplyForm, model);
			return AppConsts.URL_INQUIRY_REPLY_FORM;
		}

		// 確認画面へ
		// attribute設定
		this.setCommonAttribute(detailUser, request, response, headerForm, model);
		this.setReplyConfirmAttribute(model);
		return AppConsts.URL_INQUIRY_REPLY_CONFIRM;
	}
}
