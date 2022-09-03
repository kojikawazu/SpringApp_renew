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

import com.example.demo.app.entity.InquiryModel;
import com.example.demo.app.inquiry.form.InquiryForm;
import com.example.demo.app.service.InquiryReplyService;
import com.example.demo.app.service.InquiryService;
import com.example.demo.common.common.AppConsts;
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
	 * @param service
	 * @param inquiryReplyService
	 */
	@Autowired
	public InquiryCompleteController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService) {
		super(inquiryService, inquiryReplyService);
	}

	/**
	 * 問い合わせ成功受信
	 * @param  inquiryForm
	 * @param  result
	 * @param  model
	 * @param  redirectAttributes
	 * @return リダイレクト(問い合わせフォームURL)
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_COMPLETE)
	public String complete(
			@Validated InquiryForm inquiryForm,
			BindingResult          result,
			Model                  model,
			RedirectAttributes     redirectAttributes) {
		if(result.hasErrors()) {
			this.setFormAttribute(model);
			return AppConsts.URL_INQUIRY_FORM;
		}
		
		InquiryModel inquiry = new InquiryModel(
				new NameWord(inquiryForm.getName()),
				new NameWord(inquiryForm.getEmail()),
				new NameWord(inquiryForm.getComment()),
				LocalDateTime.now()
				);
		
		// 問い合わせデータ保存
		this.inquiryService.save(inquiry);
		
		this.setRedirectCompleteAttribute(redirectAttributes);
		return AppConsts.REDIRECT_URL_INQUIRY_FORM;
	}
	
}
