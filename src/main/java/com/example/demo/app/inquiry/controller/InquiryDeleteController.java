package com.example.demo.app.inquiry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.inquiry.common.SuperInquiryController;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.exception.SQLNoDeleteException;
import com.example.demo.common.log.LogMessage;

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
	 * @param secUserService		{@link SecUserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	public InquiryDeleteController(
			InquiryService      	inquiryService, 
			InquiryReplyService 	inquiryReplyService,
			SecUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		super(inquiryService, 
				inquiryReplyService, 
				secUserService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/**
	 * 削除受信
	 * @param  id
	 * @param  request				{@link HttpServletRequest}
	 * @param  headerForm			{@link HeaderForm}
	 * @param  model				{@link model}
	 * @param  redirectAttributes	{@link RedirectAttributes}
	 * @return {@value AppConsts#REDIRECT_URL_INQUIRY_INDEX}
	 */
	@PostMapping(AppConsts.REQUEST_MAPPING_DELETE)
	public String delete(
			@RequestParam(WebConsts.ATTRIBUTE_ID) int id,
			HttpServletRequest	request,
			HeaderForm 			headerForm,
			Model				model,
			RedirectAttributes	redirectAttributes) {
		InquiryId inquiryId = new InquiryId(id);
		
		try {
			this.getInquiryReplyService().delete_byInquiry(inquiryId);
		} catch(SQLNoDeleteException ex) {
			// 問い合わせ返信なしもある為、OK。
		}
		this.getInquiryService().delete(inquiryId);
		
		// 問い合わせ一覧画面へ
		return AppConsts.REDIRECT_URL_INQUIRY_INDEX;
	}

}
