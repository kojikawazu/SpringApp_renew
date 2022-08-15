package com.example.demo.app.inquiry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.InquiryModel;
import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.InquiryReplyService;
import com.example.demo.app.service.InquiryService;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
	
	private final InquiryService inquiryService;
	private final InquiryReplyService inquiryReplyService;
	
	@Autowired
	public InquiryController(InquiryService service, InquiryReplyService inquiryReplyService) {
		// TODO コンストラクタ
		this.inquiryService = service;
		this.inquiryReplyService = inquiryReplyService;
	}

	@GetMapping
	public String index(
			@RequestParam(value = "pageidx", required = false, defaultValue = "1") int pageidx,
			Model model) {
		List<InquiryModel> list = inquiryService.getAll();
		setPaging(list, pageidx, model);
		
		model.addAttribute("title", "お問い合わせ一覧");
		model.addAttribute("cont", "サンプルお問い合わせ情報一覧画面です。お問い合わせ一覧はこちらになります。");
		list.clear();
		
		return "inquiry/index";
	}
	
	@GetMapping("/form")
	public String form(
			InquiryForm inquiryForm,
			Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "お問い合わせフォーム");
		model.addAttribute("cont", "以下の項目を入力してください。");
		return "inquiry/form";
	}
	
	@PostMapping("/form")
	public String formGoBack(
			InquiryForm inquiryForm,
			Model model) {
		model.addAttribute("title", "お問い合わせフォーム");
		model.addAttribute("cont", "以下の項目を入力してください。");
		return "inquiry/form";
	}
	
	@PostMapping("/confirm")
	public String confirm(
			@Validated InquiryForm inquiryForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "お問い合わせフォーム");
			model.addAttribute("cont", "以下の項目を入力してください。");
			return "inquiry/form";
		}
		model.addAttribute("title", "確認画面");
		model.addAttribute("cont", "これでよろしいでしょうか？");
		return "inquiry/confirm";
	}
	
	@PostMapping("/complete")
	public String complete(
			@Validated InquiryForm inquiryForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "お問い合わせ一覧");
			model.addAttribute("cont", "以下の項目を入力してください。");
			return "inquiry/form";
		}
		
		InquiryModel inquiry = new InquiryModel();
		inquiry.setName(inquiryForm.getName());
		inquiry.setEmail(inquiryForm.getEmail());
		inquiry.setComment(inquiryForm.getComment());
		inquiry.setCreated(LocalDateTime.now());
		
		inquiryService.save(inquiry);
		redirectAttributes.addFlashAttribute("complete", "投稿しました。");
		return "redirect:/inquiry/form";
	}
	
	@PostMapping("/reply")
	public String reply(
			@RequestParam("id") int id,
			InquiryReplyForm inquiryReplyForm,
			Model model,
			@ModelAttribute("complete") String complete
			) {
		setReply(id, model);
		return "inquiry/reply_form";
	}
	
	@GetMapping("/reply")
	public String replyGet(
			@RequestParam("id") int id,
			InquiryReplyForm inquiryReplyForm,
			Model model,
			@ModelAttribute("complete") String complete
			) {
		setReply(id, model);
		return "inquiry/reply_form";
	}
	
	@PostMapping("/reply_confirm")
	public String reply_confirm(
			@RequestParam("id") int id,
			@Validated InquiryReplyForm inquiryReplyForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			setReply(id, model);
			return "inquiry/reply_form";
		}
		model.addAttribute("title", "確認画面");
		model.addAttribute("cont", "これでよろしいでしょうか？");
		
		InquiryModel inquiryModel =inquiryService.select(id);
		model.addAttribute("inquiry", inquiryModel);
		model.addAttribute("id", id);
		
		return "inquiry/reply_confirm";
	}
	
	@PostMapping("/reply_complete")
	public String reply_complete(
			@RequestParam("id") int id,
			@Validated InquiryReplyForm inquiryReplyForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			setReply(id, model);
			return "inquiry/reply_form";
		}
		
		InquiryReplyModel inquiry = new InquiryReplyModel();
		inquiry.setInquiry_id(id);
		inquiry.setName(inquiryReplyForm.getName());
		inquiry.setEmail(inquiryReplyForm.getEmail());
		inquiry.setComment(inquiryReplyForm.getComment());
		inquiry.setCreated(LocalDateTime.now());
		
		inquiryReplyService.save(inquiry);
		redirectAttributes.addFlashAttribute("complete", "投稿しました。");
		return "redirect:/inquiry/form";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("id") int id,
			Model model,
			RedirectAttributes redirectAttributes) {
		inquiryReplyService.delete_byInquiry(id);
		inquiryService.delete(id);
		return "redirect:/inquiry/";
	}
	
	public void setReply(int id, Model model) {
		// TODO 返信フォーム設定
		model.addAttribute("title", "コメント投稿");
		model.addAttribute("cont", "お問い合わせに対してのコメントを入力してください。");
		
		InquiryModel inquiryModel =inquiryService.select(id);
		model.addAttribute("inquiry", inquiryModel);
		
		model.addAttribute("id", id);
	}
	
	public void setPaging(List<InquiryModel> list, int pageidx, Model model) {
		// TODO ページング設定
		PageController page = new PageController();
		page.setPage1Cnt(5);
		List<InquiryModel> inquiryList = new ArrayList<InquiryModel>();
		List<Integer> pageList = new ArrayList<Integer>();
		
		int blogSize = list.size();
		int paging = (int)(blogSize / page.getPage1Cnt()) + 1;
		int idx = ((pageidx-1) * page.getPage1Cnt());
		
		// ページによるお問い合わせリストの再作成
		for(int cnt=0, pagelen=page.getPage1Cnt(); idx<blogSize && cnt<pagelen; idx++, cnt++){
			inquiryList.add(list.get(idx));
		}
		for(int cnt=1; cnt<paging+1; cnt++) {
			pageList.add(cnt);
		}
		
		// 返信リストの作成
		for(int cnt=0, len=inquiryList.size(); cnt<len; cnt++){
			List<InquiryReplyModel> replyList = null;
			InquiryModel inquiry = inquiryList.get(cnt);
			replyList = inquiryReplyService.select_byInquiryId(inquiry.getId());
			inquiry.setReplyList(replyList);
		}
		
		// ページング設定
		page.setListSize(blogSize);
		page.setPageSize(paging);
		page.setCurrentID(pageidx);
		page.setPageNumberList(pageList);
		page.setPageName("/inquiry");
		
		model.addAttribute("inquiryList", inquiryList);
		model.addAttribute("paging", page);
	}
}
