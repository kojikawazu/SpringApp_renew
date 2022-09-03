package com.example.demo.app.inquiry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.entity.InquiryModel;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.InquiryReplyService;
import com.example.demo.app.service.InquiryService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;

/**
 * 問い合わせコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INQUIRY)
public class InquiryController extends SuperInquiryController {
	
	/** 問い合わせ一覧ページの最大数 */
	private static final int INQUIRY_PAGE_MAX = 5;
	
	/**
	 * コンストラクタ
	 * @param service
	 * @param inquiryReplyService
	 */
	@Autowired
	public InquiryController(
			InquiryService      inquiryService, 
			InquiryReplyService inquiryReplyService) {
		super(inquiryService, inquiryReplyService);
	}

	/**
	 * 問い合わせ一覧ページ受信
	 * @param pageidx
	 * @param model
	 * @return 問い合わせ一覧URL
	 */
	@GetMapping
	public String index(
			@RequestParam(value = WebConsts.ATTRIBUTE_PAGE_IDX, 
				required = false, defaultValue = "1") int pageidx,
			Model model) {
		
		// ページング設定
		this.setPaging(pageidx, model);
		
		this.setIndexAttribute(model);
		return AppConsts.URL_INQUIRY_INDEX;
	}
	
	/**
	 * ページング設定
	 * @param list
	 * @param pageidx
	 * @param model
	 */
	public void setPaging(int pageidx, Model model) {
		List<InquiryModel> list        = this.inquiryService.getAllPlus(true);
		PageController     page        = new PageController();
		List<InquiryModel> inquiryList = page.setPaging(list, pageidx, INQUIRY_PAGE_MAX);
		list.clear();
		
		// ページング名設定
		page.setPageName(AppConsts.REQUEST_MAPPING_INQUIRY);
		
		// 問い合わせリスト一覧
		model.addAttribute(AppConsts.ATTRIBUTE_INQUIRY_LIST, inquiryList);
		model.addAttribute(WebConsts.ATTRIBUTE_PAGING,       page);
	}
}
