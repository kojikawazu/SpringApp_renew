package com.example.demo.app.inquiry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.service.InquiryReplyService;
import com.example.demo.app.service.InquiryService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.exception.SQLNoDeleteException;
import com.example.demo.common.id.InquiryId;

/**
 * 問い合わせ削除コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryDeleteController extends SuperInquiryController {

	/**
	 * コンストラクタ
	 * @param inquiryService
	 * @param inquiryReplyService
	 */
	public InquiryDeleteController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService) {
		super(inquiryService, inquiryReplyService);
	}
	
	/**
	 * 削除受信
	 * @param  id
	 * @param  model
	 * @param  redirectAttributes
	 * @return リダイレクト：[問い合わせ一覧]
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_DELETE)
	public String delete(@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			Model                model,
			RedirectAttributes   redirectAttributes) {
		InquiryId inquiryId = new InquiryId(id);
		
		try {
			this.inquiryReplyService.delete_byInquiry(inquiryId);
		} catch(SQLNoDeleteException ex) {
			// 問い合わせ返信なしもある為、OK。
		}
		this.inquiryService.delete(inquiryId);
		
		// 問い合わせ一覧画面へ
		return AppConsts.REDIRECT_URL_INQUIRY_INDEX;
	}

}
