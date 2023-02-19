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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.entity.inquiry.InquiryModel;
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.common.SuperInquiryController;
import com.example.demo.app.inquiry.form.InquiryForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
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
	 * @param secUserService		{@link SecurityUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public InquiryCompleteController(
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
	
	/** --------------------------------------------------------------- */

	/**
	 * 問い合わせ成功受信
	 * @param  detailUser			{@link SecLoginUserDetails}
	 * @param  request				{@link HttpServletRequest}
	 * @param  response				{@link HttpServletResponse}
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
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			HttpServletRequest								request,
			HttpServletResponse 							response,
			HeaderForm 										headerForm,
			@Validated InquiryForm							inquiryForm,
			BindingResult									result,
			Model											model,
			RedirectAttributes								redirectAttributes) {
		if(result.hasErrors()) {
			// エラー
			
			// attribute設定
			this.setInclude(detailUser, request, response, headerForm, model);
			this.setCommonAttribute(detailUser, request, response, headerForm, model);
			this.setFormAttribute(inquiryForm, model);
			return AppConsts.URL_INQUIRY_FORM;
		}
		
		InquiryModel inquiry = new InquiryModel(
				new NameWord(inquiryForm.getName()),
				new EmailWord(inquiryForm.getEmail()),
				new CommentWord(inquiryForm.getComment()),
				LocalDateTime.now()
				);
		
		// 問い合わせデータ保存
		this.getInquiryService().save(inquiry);
		
		// redirectAttribute設定
		this.setRedirectCompleteAttribute(redirectAttributes);
		return AppConsts.REDIRECT_URL_INQUIRY_FORM;
	}
	
}
