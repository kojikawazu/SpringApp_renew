package com.example.demo.app.inquiry.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.entity.inquiry.InquiryModel;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryService;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;

/**
 * 問い合わせコントローラー<br>
 * implements {@link SuperInquiryController}
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryController extends SuperInquiryController {
	
	/** 問い合わせ一覧ページの最大数 */
	private static final int INQUIRY_PAGE_MAX = 5;
	
	/** --------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param inquiryService		{@link InquiryService}
	 * @param inquiryReplyService	{@link InquiryReplyService}
	 * @param userService			{@link UserServiceUse}
	 * @param loginService			{@link LoginServiceUse}
	 * @param sessionModel			{@link SessionModel}
	 * @param httpSession			{@link HttpSession}
	 * @param logMessage			{@link LogMessage}
	 */
	@Autowired
	public InquiryController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService,
			UserServiceUse 		userService,
			LoginServiceUse		loginService,
			SessionModel		sessionModel,
			HttpSession			httpSession,
			LogMessage			logMessage) {
		super(inquiryService, 
				inquiryReplyService, 
				userService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/** --------------------------------------------------------------- */

	/**
	 * 問い合わせ一覧ページ受信
	 * @param  cookieLoginId	ログインID(Cookie)
	 * @param  cookieUserId		ユーザーID(Cookie)
	 * @param  cookieUserName	ユーザー名(Cookie)
	 * @param  pageidx
	 * @param  request			{@link HttpServletRequest}
	 * @param  headerForm		{@link HeaderForm}
	 * @param  model			{@link Model}
	 * @return {@value AppConsts#URL_INQUIRY_INDEX}
	 */
	@GetMapping
	public String index(
			@CookieValue(name=WebConsts.COOKIE_LOGIN_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieLoginId,
			@CookieValue(name=WebConsts.COOKIE_USER_ID,
				required=false, 
				defaultValue=WebConsts.COOKIE_ZERO)		String cookieUserId,
			@CookieValue(name=WebConsts.COOKIE_USER_NAME,
				required=false, 
				defaultValue=WebConsts.COOKIE_NONE)		String cookieUserName,
			@RequestParam(value = WebConsts.ATTRIBUTE_PAGE_IDX, 
				required = false, 
				defaultValue = "1") 					int pageidx,
			HttpServletRequest	request,
			HeaderForm			headerForm,
			Model				model) {
		/** Cookieの設定 */
		this.headerController.setCookie(cookieLoginId, cookieUserId, cookieUserName);
		
		// ページング設定
		this.setPaging(pageidx, model);
		
		/** ヘッダーの設定 */
		this.headerController.setHeader(request, headerForm, model);
		
		// attribute設定
		this.setCommonAttribute(request, headerForm, model);
		this.setIndexAttribute(model);
		return AppConsts.URL_INQUIRY_INDEX;
	}
	
	/**
	 * ページング設定
	 * @param pageidx
	 * @param model {@link Model}
	 */
	public void setPaging(int pageidx, Model model) {
		List<InquiryModel> list			= this.inquiryService.getAllPlus(true);
		PageController     page			= new PageController();
		List<InquiryModel> inquiryList	= page.setPaging(list, pageidx, INQUIRY_PAGE_MAX);
		list.clear();
		
		// ページング名設定
		page.setPageName(AppConsts.REQUEST_MAPPING_INQUIRY);
		
		// 問い合わせリスト一覧
		model.addAttribute(AppConsts.ATTRIBUTE_INQUIRY_LIST, inquiryList);
		model.addAttribute(WebConsts.ATTRIBUTE_PAGING,       page);
	}
}
