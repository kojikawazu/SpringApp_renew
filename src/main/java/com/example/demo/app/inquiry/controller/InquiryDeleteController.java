package com.example.demo.app.inquiry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.exception.SQLNoDeleteException;
import com.example.demo.common.id.inquiry.InquiryId;

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
	 * @param inquiryService		{@link InquiryService}
	 * @param inquiryReplyService	{@link InquiryReplyService}
	 * @param userServiceUse		{@link UserServiceUse}
	 * @param sessionLoginUser		{@link SessionLoginUser}
	 */
	public InquiryDeleteController(
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
	 * 削除受信
	 * @param  id
	 * @param  headerForm			{@link HeaderForm}
	 * @param  model				{@link model}
	 * @param  redirectAttributes	{@link RedirectAttributes}
	 * @return {@value AppConsts#REDIRECT_URL_INQUIRY_INDEX}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_DELETE)
	public String delete(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			HeaderForm 			headerForm,
			Model				model,
			RedirectAttributes	redirectAttributes) {
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
