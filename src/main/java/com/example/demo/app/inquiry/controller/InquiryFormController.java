package com.example.demo.app.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.form.InquiryForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;

/**
 * 問い合わせフォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryFormController extends SuperInquiryController {
	
	/**
	 * コンストラクタ
	 * @param inquiryService		{@link InquiryService}
	 * @param inquiryReplyService	{@link InquiryReplyService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public InquiryFormController(
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
	 * 問い合わせフォーム受信(Get)
	 * @param  headerForm	{@link HeaderForm}
	 * @param  inquiryForm	{@link InquiryForm}
	 * @param  model		{@link Model}
	 * @param  complete
	 * @return {@value AppConsts#URL_INQUIRY_FORM}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String form(
			HeaderForm	headerForm,
			InquiryForm	inquiryForm,
			Model		model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		// attribute設定
		this.setCommonAttribute(model);
		this.setFormAttribute(model);
		return AppConsts.URL_INQUIRY_FORM;
	}
	
	/**
	 * 問い合わせフォーム受信(Post)
	 * @param  headerForm	{@link HeaderForm}
	 * @param  inquiryForm	{@link InquiryForm}
	 * @param  model		{@link Model}
	 * @return {@value AppConsts#URL_INQUIRY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String formGoBack(
			HeaderForm headerForm,
			InquiryForm inquiryForm,
			Model       model) {
		// attribute設定
		this.setCommonAttribute(model);
		this.setFormAttribute(model);
		return AppConsts.URL_INQUIRY_FORM;
	}

}
