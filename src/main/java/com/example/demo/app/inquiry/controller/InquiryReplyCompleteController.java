package com.example.demo.app.inquiry.controller;

import java.time.LocalDateTime;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.entity.inquiry.InquiryReplyModel;
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.common.SuperInquiryController;
import com.example.demo.app.inquiry.form.InquiryReplyForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ返信投稿成功コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryReplyCompleteController extends SuperInquiryController {

	/**
	 * コンストラクタ
	 * @param inquiryService		{@link InquiryService}
	 * @param inquiryReplyService	{@link InquiryReplyService}
	 * @param secUserService		{@link SecurityUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public InquiryReplyCompleteController(
			InquiryService      	inquiryService, 
			InquiryReplyService 	inquiryReplyService,
			SecurityUserServiceUse	secUserService,
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
	 * 問い合わせ返信投稿受信
	 * @param  detailUser			{@link SecLoginUserDetails}
	 * @param  id
	 * @param  request				{@link HttpServletRequest}
	 * @param  response				{@link HttpServletResponse}
	 * @param  headerForm			{@link HeaderForm}
	 * @param  inquiryReplyForm		{@link InquiryReplyForm}
	 * @param  result				{@link BindingResult}
	 * @param  model				{@link Model}
	 * @param  redirectAttributes	{@link RedirectAttributes}
	 * @return 	{@value AppConsts#URL_INQUIRY_REPLY_FORM}
	 * 			{@value AppConsts#REDIRECT_URL_INQUIRY_REPLY}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY_COMPLETE)
	public String reply_complete(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			@RequestParam(WebConsts.ATTRIBUTE_ID) 			int id,
			HttpServletRequest								request,
			HttpServletResponse 							response,
			HeaderForm 										headerForm,
			@Validated InquiryReplyForm						inquiryReplyForm,
			BindingResult									result,
			Model											model,
			RedirectAttributes								redirectAttributes) {
		InquiryId inquiryId = new InquiryId(id);
		
		if(result.hasErrors()) {
			// バリデートエラー、フォーム画面へ
			
			// Cookieの設定
			this.setInclude(detailUser, request, response, headerForm, model);
			// 返信対象設定
			this.setReply(inquiryId, model);
			// attribute設定
			this.setCommonAttribute(detailUser, request, response, headerForm, model);
			this.setReplyFormAttribute(detailUser, inquiryReplyForm, model);
			return AppConsts.URL_INQUIRY_REPLY_FORM;
		}
		
		// 問い合わせ返信登録
		InquiryReplyModel inquiry = new InquiryReplyModel(
				new InquiryId(id),
				new NameWord(inquiryReplyForm.getName()),
				new EmailWord(inquiryReplyForm.getEmail()),
				new CommentWord(inquiryReplyForm.getComment()),
				LocalDateTime.now()
				);
		this.getInquiryReplyService().save(inquiry);
		
		// フォーム画面へ
		this.setReplyCompleteAttribute(inquiryId, redirectAttributes);
		return AppConsts.REDIRECT_URL_INQUIRY_REPLY;
	}

}
