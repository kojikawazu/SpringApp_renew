package com.example.demo.app.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.inquiry.form.InquiryForm;
import com.example.demo.app.service.InquiryReplyService;
import com.example.demo.app.service.InquiryService;
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
	 * @param service
	 * @param inquiryReplyService
	 */
	@Autowired
	public InquiryFormController(
			InquiryService      service, 
			InquiryReplyService inquiryReplyService) {
		super(service, inquiryReplyService);
	}
	
	/**
	 * 問い合わせフォーム受信(Get)
	 * @param inquiryForm
	 * @param model
	 * @param complete
	 * @return 問い合わせフォームURL
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String form(
			InquiryForm inquiryForm,
			Model       model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete) {
		this.setFormAttribute(model);
		return AppConsts.URL_INQUIRY_FORM;
	}
	
	/**
	 * 問い合わせフォーム受信(Post)
	 * @param inquiryForm
	 * @param model
	 * @return 問い合わせフォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String formGoBack(
			InquiryForm inquiryForm,
			Model       model) {
		this.setFormAttribute(model);
		return AppConsts.URL_INQUIRY_FORM;
	}

}
