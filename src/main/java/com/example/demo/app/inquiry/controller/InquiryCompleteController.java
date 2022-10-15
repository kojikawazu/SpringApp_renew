package com.example.demo.app.inquiry.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.inquiry.InquiryModel;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.form.InquiryForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;
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
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public InquiryCompleteController(
			InquiryService		inquiryService, 
			InquiryReplyService inquiryReplyService,
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		super(inquiryService, 
				inquiryReplyService, 
				userServiceUse, 
				sessionLoginUser);
	}

	/**
	 * 問い合わせ成功受信
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
			HeaderForm 				headerForm,
			@Validated InquiryForm	inquiryForm,
			BindingResult			result,
			Model					model,
			RedirectAttributes		redirectAttributes) {
		if(result.hasErrors()) {
			// attribute設定
			this.setCommonAttribute(model);
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
