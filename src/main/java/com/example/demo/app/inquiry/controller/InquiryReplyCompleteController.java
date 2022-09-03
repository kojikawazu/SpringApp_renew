package com.example.demo.app.inquiry.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.app.inquiry.form.InquiryReplyForm;
import com.example.demo.app.service.InquiryReplyService;
import com.example.demo.app.service.InquiryService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ返信投稿成功コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryReplyCompleteController extends SuperInquiryController {

	/**
	 * コンストラクタ
	 * @param inquiryService
	 * @param inquiryReplyService
	 */
	@Autowired
	public InquiryReplyCompleteController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService) {
		super(inquiryService, inquiryReplyService);
	}
	
	/**
	 * 問い合わせ返信投稿受信
	 * @param  id
	 * @param  inquiryReplyForm
	 * @param  result
	 * @param  model
	 * @param  redirectAttributes
	 * @return リダイレクト:[問い合わせ返信フォーム]
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_REPLY_COMPLETE)
	public String reply_complete(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			@Validated InquiryReplyForm inquiryReplyForm,
			BindingResult               result,
			Model                       model,
			RedirectAttributes          redirectAttributes) {
		InquiryId inquiryId = new InquiryId(id);
		
		if(result.hasErrors()) {
			// バリデートエラー、フォーム画面へ
			this.setReply(inquiryId, model);
			this.setReplyFormAttribute(model);
			return AppConsts.URL_INQUIRY_REPLY_FORM;
		}
		
		// 問い合わせ返信登録
		InquiryReplyModel inquiry = new InquiryReplyModel(
				new InquiryId(id),
				new NameWord(inquiryReplyForm.getName()),
				new EmailWord(inquiryReplyForm.getEmail()),
				new CommentWord(inquiryReplyForm.getComment()),
				LocalDateTime.now()
				);
		this.inquiryReplyService.save(inquiry);
		
		// フォーム画面へ
		this.setReplyCompleteAttribute(inquiryId, redirectAttributes);
		return AppConsts.REDIRECT_URL_INQUIRY_REPLY;
	}

}
