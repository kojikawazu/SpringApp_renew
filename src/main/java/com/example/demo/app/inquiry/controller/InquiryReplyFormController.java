package com.example.demo.app.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
 * 問い合わせ返信フォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryReplyFormController extends SuperInquiryController {
	
	/**
	 * コンストラクタ
	 * @param inquiryService		{@link InquiryService}
	 * @param inquiryReplyService	{@link InquiryReplyService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	@Autowired
	public InquiryReplyFormController(
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
	 * 問い合わせ返信受信(Post)
	 * @param  id
	 * @param  headerForm		{@link HeaderForm}
	 * @param  inquiryReplyForm	{@link InquiryReplyForm}
	 * @param  model			{@link Model}
	 * @param  complete
	 * @return {@value AppConsts#URL_INQUIRY_REPLY_FORM}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String reply(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			HeaderForm			headerForm,
			InquiryReplyForm	inquiryReplyForm,
			Model				model,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String complete
			) {
		this.setReply(new InquiryId(id), model);
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setReplyFormAttribute(model);
		return AppConsts.URL_INQUIRY_REPLY_FORM;
	}
	
	/**
	 * 問い合わせ返信受信(Get)
	 * @param  headerForm		{@link HeaderForm}
	 * @param  inquiryReplyForm	{@link InquiryReplyForm}
	 * @param  model			{@link Model}
	 * @param  id
	 * @param  complete
	 * @return {@value AppConsts#URL_INQUIRY_REPLY_FORM}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_REPLY)
	public String replyGet(
			HeaderForm			headerForm,
			InquiryReplyForm	inquiryReplyForm,
			Model				model,
			@ModelAttribute(WebConsts.ATTRIBUTE_ID)       int		id,
			@ModelAttribute(WebConsts.ATTRIBUTE_COMPLETE) String	complete
			) {
		this.setReply(new InquiryId(id), model);
		
		// attribute設定
		this.setCommonAttribute(model);
		this.setReplyFormAttribute(model);
		return AppConsts.URL_INQUIRY_REPLY_FORM;
	}
}
