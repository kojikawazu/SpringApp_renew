package com.example.demo.app.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
 * 問い合わせ返信フォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryReplyFormController extends SuperInquiryController {
	
	/**
	 * コンストラクタ
	 * @param service
	 * @param inquiryReplyService
	 */
	@Autowired
	public InquiryReplyFormController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService) {
		super(inquiryService, inquiryReplyService);
	}
	
	/**
	 * 問い合わせ返信受信(Post)
	 * @param  id
	 * @param  inquiryReplyForm
	 * @param  model
	 * @param  complete
	 * @return 問い合わせ返信フォームURL
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String reply(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			InquiryReplyForm inquiryReplyForm,
			Model            model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete
			) {
		this.setReply(new InquiryId(id), model);
		this.setReplyFormAttribute(model);
		return AppConsts.URL_INQUIRY_REPLY_FORM;
	}
	
	/**
	 * 問い合わせ返信受信(Get)
	 * @param  id
	 * @param  inquiryReplyForm
	 * @param  model
	 * @param  complete
	 * @return 問い合わせ返信フォームURL
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String replyGet(
			InquiryReplyForm inquiryReplyForm,
			Model            model,
			@ModelAttribute(WebConsts.ATTRIBUTE_ID)       int    id,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete
			) {
		this.setReply(new InquiryId(id), model);
		this.setReplyFormAttribute(model);
		return AppConsts.URL_INQUIRY_REPLY_FORM;
	}
}
