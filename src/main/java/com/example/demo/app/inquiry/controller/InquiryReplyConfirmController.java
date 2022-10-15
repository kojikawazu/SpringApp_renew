package com.example.demo.app.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.form.InquiryReplyForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.inquiry.InquiryId;

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
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public InquiryReplyConfirmController(
			InquiryService	inquiryService, 
			InquiryReplyService	inquiryReplyService,
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		super(inquiryService, 
				inquiryReplyService, 
				userServiceUse, 
				sessionLoginUser);
	}

	/**
	 * 問い合わせ返信確認受信
	 * @param  id
	 * @param  headerForm		{@link HeaderForm}
	 * @param  inquiryReplyForm	{@link InquiryReplyForm}
	 * @param  result			{@link BindingResult}
	 * @param  model			{@link Model}
	 * @return	{@value AppConsts#URL_INQUIRY_REPLY_FORM}
	 * 			{@value AppConsts#URL_INQUIRY_REPLY_CONFIRM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY_CONFIRM)
	public String reply_confirm(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			HeaderForm 					headerForm,
			@Validated InquiryReplyForm	inquiryReplyForm,
			BindingResult				result,
			Model						model) {
		this.setReply(new InquiryId(id), model);
		
		if(result.hasErrors()) {
			// バリデートエラー。フォーム画面へ
			// attribute設定
			this.setCommonAttribute(model);
			this.setReplyFormAttribute(model);
			return AppConsts.URL_INQUIRY_REPLY_FORM;
		}

		// 確認画面へ
		// attribute設定
		this.setCommonAttribute(model);
		this.setReplyConfirmAttribute(model);
		return AppConsts.URL_INQUIRY_REPLY_CONFIRM;
	}
}
