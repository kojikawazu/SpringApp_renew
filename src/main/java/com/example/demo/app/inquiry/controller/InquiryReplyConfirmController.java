package com.example.demo.app.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.inquiry.form.InquiryReplyForm;
import com.example.demo.app.service.InquiryReplyService;
import com.example.demo.app.service.InquiryService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.InquiryId;

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
	 * @param service
	 * @param inquiryReplyService
	 */
	@Autowired
	public InquiryReplyConfirmController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService) {
		super(inquiryService, inquiryReplyService);
	}

	/**
	 * 問い合わせ返信確認受信
	 * @param id
	 * @param inquiryReplyForm
	 * @param result
	 * @param model
	 * @return 問い合わせ返信確認URL or 問い合わせ返信フォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY_CONFIRM)
	public String reply_confirm(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			@Validated InquiryReplyForm inquiryReplyForm,
			BindingResult               result,
			Model                       model) {
		this.setReply(new InquiryId(id), model);
		
		if(result.hasErrors()) {
			// バリデートエラー。フォーム画面へ
			this.setReplyFormAttribute(model);
			return AppConsts.URL_INQUIRY_REPLY_FORM;
		}

		// 確認画面へ
		this.setReplyConfirmAttribute(model);
		return AppConsts.URL_INQUIRY_REPLY_CONFIRM;
	}
}
