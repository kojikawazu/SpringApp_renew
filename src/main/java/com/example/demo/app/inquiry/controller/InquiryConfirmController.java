package com.example.demo.app.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.form.InquiryForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;

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
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public InquiryConfirmController(
			InquiryService		inquiryService, 
			InquiryReplyService	inquiryReplyService,
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		super(inquiryService, 
				inquiryReplyService, 
				userServiceUse, 
				sessionLoginUser);
	}
	
	/**
	 * 問い合わせ確認受信
	 * @param  headerForm	{@link HeaderForm}
	 * @param  inquiryForm  {@link InquiryForm}
	 * @param  result		{@link BindingResult}
	 * @param  model		{@link Model}
	 * @return 	{@value AppConsts#URL_INQUIRY_FORM}
	 * 			{@value AppConsts#URL_INQUIRY_CONFIRM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_CONFIRM)
	public String confirm(
			HeaderForm 				headerForm,
			@Validated InquiryForm	inquiryForm,
			BindingResult			result,
			Model					model) {
		if(result.hasErrors()) {
			// バリデートエラー, フォーム画面へ
			// attribute設定
			this.setCommonAttribute(model);
			this.setFormAttribute(model);
			return AppConsts.URL_INQUIRY_FORM;
		}
		// バリデートOK 確認画面へ
		// attribute設定
		this.setCommonAttribute(model);
		this.setConfirmAttribute(model);
		return AppConsts.URL_INQUIRY_CONFIRM;
	}
	
}
