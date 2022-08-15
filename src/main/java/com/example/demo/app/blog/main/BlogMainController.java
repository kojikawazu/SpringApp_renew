package com.example.demo.app.blog.main;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.app.entity.BlogReplyModel;
import com.example.demo.app.entity.BlogTagModel;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.BlogMainService;
import com.example.demo.app.service.BlogReplyService;
import com.example.demo.app.service.BlogTagService;

@Controller
@RequestMapping("/blog")
public class BlogMainController {

	// サービス
	private final BlogMainService blogMainService;
	private final BlogReplyService blogReplyService;
	private final BlogTagService blogTagService;
	
	@Autowired
	public BlogMainController(
			BlogMainService blogMainService, BlogReplyService blogReplyService, BlogTagService blogTagService) {
		// TODO コンストラクタ
		this.blogMainService = blogMainService;
		this.blogReplyService = blogReplyService;
		this.blogTagService = blogTagService;
	}
	
	@GetMapping
	public String index(
			@RequestParam(value = "pageidx", required = false, defaultValue = "1") int pageidx,
			@Validated BlogSelectedForm blogSelectedForm,
			Model model) {
		// TODO メイン
		List<BlogMainModel> list = setBlogList(blogSelectedForm);		
		setPaging(list, pageidx, model);
		setSelectTag(model);
		model.addAttribute("title", "●●さんのブログ");
		model.addAttribute("cont", "気になる話題がありましたら閲覧お願いします。");
		model.addAttribute("selectIdx", "0");
		list.clear();
		return "blog/index";
	}
	
	@GetMapping("/form")
	public String form(
			@RequestParam(value = "editor", required = false, defaultValue = "0") int editor,
			BlogForm blogForm,
			Model model,
			@ModelAttribute("complete") String complete) {
		// フォーム画面
		setAdd(editor, model);
		return "blog/form";
	}
	
	@PostMapping("/form")
	public String formGoBack(
			@RequestParam(value = "editor", required = false, defaultValue = "0") int editor,
			BlogForm blogForm,
			Model model,
			@ModelAttribute("complete") String complete) {
		// フォーム画面
		setAdd(editor, model);
		return "blog/form";
	}
	
	@GetMapping("/edit")
	public String editGet(
			@RequestParam(value = "editor", required = false, defaultValue = "0") int editor,
			BlogForm blogForm,
			Model model,
			@ModelAttribute("complete") String complete) {
		// TODO 編集画面へ
		setEditor(editor, blogForm, model);
		return "blog/form";
	}
	
	@PostMapping("/edit")
	public String edit(
			@RequestParam(value = "editor", required = false, defaultValue = "0") int editor,
			BlogForm blogForm,
			Model model,
			@ModelAttribute("complete") String complete) {
		// TODO 編集画面へ
		setEditor(editor, blogForm, model);
		return "blog/form";
	}
	
	@PostMapping("/confirm")
	public String confirm(
			@RequestParam("editor") int editor,
			@Validated BlogForm blogForm,
			BindingResult result,
			Model model) {
		// TODO 確認画面
		if(result.hasErrors()) {
			if( editor > 0 ) {
				setEditor(editor, blogForm, model);
			}else {
				setAdd(editor, model);
			}
			return "blog/form";
		}
		
		model.addAttribute("title", (editor > 0) ? "ブログ変更確認" : "ブログ投稿確認");
		model.addAttribute("cont", "これでよろしいですか？");
		model.addAttribute("editor", editor);
		
		return "blog/confirm";
	}
	
	@PostMapping("/complete")
	public String complete(
			@Validated BlogForm blogForm,
			@RequestParam("editor") int editor,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		// TODO 投稿or編集
		if(result.hasErrors()) {
			if( editor > 0 ) {
				setEditor(editor, blogForm, model);
			}else {
				setAdd(editor, model);
			}
			return "blog/form";
		}
		
		BlogMainModel blog = new BlogMainModel();
		blog.setTitle(blogForm.getBlogTitle());
		blog.setTag(blogForm.getTag());
		blog.setComment(blogForm.getBlogContents());
		blog.setCreated(LocalDateTime.now());
		blog.setUpdated(LocalDateTime.now());
		
		if( editor == 0 ) {
			// 追加作業
			blogMainService.save(blog);
			saveTag(blog);
			redirectAttributes.addFlashAttribute("complete", "投稿されました。");
			return "redirect:/blog/form";
		}else {
			// 編集作業
			blog.setThanksCnt(blogForm.getThanksCnt());
			blog.setId(editor);
			blogMainService.update(blog);
			
			redirectAttributes.addAttribute("editor", editor);
			redirectAttributes.addFlashAttribute("blogForm", blogForm);
			redirectAttributes.addFlashAttribute("model", model);
			redirectAttributes.addFlashAttribute("complete", "変更されました。");
			
			return "redirect:/blog/edit";
		}
	}
	
	@PostMapping("/reply")
	public String reply(
			@RequestParam("id") int replyid,
			ReplyForm replyForm,
			Model model,
			@ModelAttribute("complete") String complete) {
		// TODO 返信投稿画面
		setReplyForm(replyid, model);
		return "blog/reply_form";
	}
	
	@GetMapping("/reply")
	public String replyGet(
			@RequestParam("id") int replyid,
			ReplyForm replyForm,
			Model model,
			@ModelAttribute("complete") String complete) {
		// TODO 返信投稿画面
		setReplyForm(replyid, model);
		return "blog/reply_form";
	}
	
	
	@PostMapping("/reply_confirm")
	public String reply_confirm(
			@RequestParam("id") int id,
			@Validated ReplyForm replyForm,
			BindingResult result,
			Model model) {
		// TODO 確認画面
		if(result.hasErrors()) {
			setReplyForm(id, model);
			return "blog/reply_form";
		}
		
		model.addAttribute("title", "コメント投稿確認");
		model.addAttribute("cont", "これでよろしいですか？");
		
		BlogMainModel blogmainModel = blogMainService.select(id);
		model.addAttribute("blogMain", blogmainModel);
		model.addAttribute("id", id);
		
		return "blog/reply_confirm";
	}
	
	@PostMapping("/reply_complete")
	public String reply_complete(
			@RequestParam("id") int id,
			@Validated ReplyForm replyForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		// TODO 返信コメント
		if(result.hasErrors()) {
			setReplyForm(id, model);
			return "blog/reply";
		}
		
		BlogReplyModel blog = new BlogReplyModel();
		blog.setCommentid(id);
		blog.setThanksCnt(0);
		blog.setName(replyForm.getName());
		blog.setComment(replyForm.getComment());
		blog.setCreated(LocalDateTime.now());
		
		blogReplyService.save(blog);
		redirectAttributes.addAttribute("id", id);
		redirectAttributes.addFlashAttribute("complete", "投稿されました。");
		return "redirect:/blog/reply";
	}
	
	@PostMapping("/delete")
	public String delete(
			@RequestParam("id") int id,
			Model model,
			RedirectAttributes redirectAttributes) {
		// TODO 削除
		blogReplyService.delete_byBlogid(id);
		blogMainService.delete(id);
		
		return "redirect:/blog/";
	}
	
	@PostMapping("/selected")
	public String selectedTag(
			@Validated BlogSelectedForm blogSelectedForm,
			Model model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("selectIdx", blogSelectedForm.getSelectIdx());
		return "redirect:/blog/";
	}
	
	// 非同期通信
	@RequestMapping("/thanks")
	@ResponseBody
	public String thanksIncrement2(BlogMainId thanksCnter) {
		// TODO いいね数加算
		int cnter = blogMainService.thanksIncrement(thanksCnter.getId());
		if(cnter != -1)	cnter = 0;
		return String.valueOf(cnter);
	}
	
	// 非同期通信
	@RequestMapping("/reply_thanks")
	@ResponseBody
	public String replyThanksIncrement(BlogMainId thanksCnter) {
		// TODO いいね数加算
		int cnter = blogReplyService.thanksIncrement(thanksCnter.getId());
		return String.valueOf(cnter);
	}
	
	// -----------------------------------------------------------------------------------------
	
	private void setAdd(int editor, Model model) {
		// 追加共通画面
		model.addAttribute("title", "ブログ投稿");
		model.addAttribute("cont", "ブログを投稿してください。");
		model.addAttribute("editor", editor);
	}
	
	private void setEditor(int editor, BlogForm blogForm, Model model) {
		// TODO 編集共通処理
		BlogMainModel result = blogMainService.select(editor);
		
		model.addAttribute("title", "ブログの編集");
		model.addAttribute("cont", "ブログを編集してください。");
		model.addAttribute("editor", editor);
		
		// データの取得
		blogForm.setBlogTitle(result.getTitle());
		blogForm.setTag(result.getTag());
		blogForm.setBlogContents(result.getComment());
		blogForm.setThanksCnt(result.getThanksCnt());
	}
	
	private void setReplyForm(int id, Model model) {
		// TODO 返信元の加工
		model.addAttribute("title", "コメント投稿");
		model.addAttribute("cont", "ブログに対してのコメントを入力してください。");
		
		BlogMainModel blogmainModel = blogMainService.select(id);
		model.addAttribute("blogMain", blogmainModel);
		
		model.addAttribute("id", id);
	}
	
	private void setPaging(List<BlogMainModel> list, int pageidx, Model model) {
		// TODO ページング設定
		PageController page = new PageController();
		page.setPage1Cnt(5);
		List<BlogMainModel> blogpageList = new ArrayList<BlogMainModel>();
		List<Integer> pageList = new ArrayList<Integer>();
		
		int blogSize = list.size();
		int paging = (int)(blogSize / page.getPage1Cnt()) + 1;
		int idx = ((pageidx-1) * page.getPage1Cnt());
		
		// ページによるブログ投稿リストの再作成
		for(int cnt=0, pagelen=page.getPage1Cnt(); idx<blogSize && cnt<pagelen; idx++, cnt++){
			blogpageList.add(list.get(idx));
		}
		for(int cnt=1; cnt<paging+1; cnt++) {
			pageList.add(cnt);
		}
		
		// 返信リストの作成
		for(int cnt=0, len=blogpageList.size(); cnt<len; cnt++ ) {
			List<BlogReplyModel> replyList = null;
			BlogMainModel blogModel = blogpageList.get(cnt);
			replyList = blogReplyService.select_byBlogId(blogModel.getId());
			blogModel.setReplyList(replyList);
		}
		
		// ページクラスに設定
		page.setListSize(blogSize);
		page.setPageSize(paging);
		page.setCurrentID(pageidx);
		page.setPageNumberList(pageList);
		page.setPageName("/blog");
		
		model.addAttribute("blogmainList", blogpageList);
		model.addAttribute("paging", page);
	}
	
	private List<BlogMainModel> setBlogList(BlogSelectedForm blogSelectedForm) {
		List<BlogMainModel> list;
		int selectidx =  blogSelectedForm.getSelectIdx();
		if( selectidx == 1 ||  selectidx == 0 ) {
			list = blogMainService.getAll();
		}else {
			BlogTagModel tagModel = blogTagService.select(selectidx);
			list = blogMainService.select_byTag(tagModel.getTag());
		}
		return list;
	}
	
	private void setSelectTag(Model model) {
		// TODO タグリストの設定
		List<BlogTagModel> list = blogTagService.getAll();
		model.addAttribute("tagList", list);
	}
	
	private void saveTag(BlogMainModel blog) {
		// TODO タグリストをチェックし、ない場合は追加。
		String targetTag = blog.getTag();
		if( !blogTagService.isSelect_byTag(targetTag) ) {
			BlogTagModel tagModel = new BlogTagModel();
			tagModel.setTag(targetTag);
			blogTagService.save(tagModel);
		}
	}
}
