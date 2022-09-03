package com.example.demo.app.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.inquiry.form.InquiryForm;
import com.example.demo.app.service.InquiryReplyService;
import com.example.demo.app.service.InquiryService;
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
	 * @param service
	 * @param inquiryReplyService
	 */
	@Autowired
	public InquiryConfirmController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService) {
		super(inquiryService, inquiryReplyService);
	}
	
	/**
	 * 問い合わせ確認受信
	 * @param  inquiryForm
	 * @param  result
	 * @param  model
	 * @return 問い合わせ確認URL or 問い合わせフォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_CONFIRM)
	public String confirm(
			@Validated InquiryForm inquiryForm,
			BindingResult          result,
			Model                  model) {
		if(result.hasErrors()) {
			// バリデートエラー, フォーム画面へ
			this.setFormAttribute(model);
			return AppConsts.URL_INQUIRY_FORM;
		}
		// バリデートOK 確認画面へ
		this.setConfirmAttribute(model);
		return AppConsts.URL_INQUIRY_CONFIRM;
	}
	
}
