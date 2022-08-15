package com.example.demo.app.servey;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.SurveyModel;
import com.example.demo.app.home.PageController;
import com.example.demo.app.service.SurveyService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	private final SurveyService surveyService;
	
	@Autowired
	public SurveyController(SurveyService service) {
		// TODO コンストラクタ
		this.surveyService = service;
	}
	
	@GetMapping
	public String index(
			@RequestParam(value = "pageidx", required = false, defaultValue = "1") int pageidx,
			Model model) {
		List<SurveyModel> list = surveyService.getAll();
		setPaging(list, pageidx, model);
		
		model.addAttribute("title", "アンケート一覧");
		model.addAttribute("cont", "アンケート一覧を表示してます。");
		list.clear();
		
		return "survey/index";
	}
	
	@GetMapping("/form")
	public String form(SurveyForm surveyForm,
			Model model,
			@ModelAttribute("complete") String complete) {
		
		model.addAttribute("title", "アンケート投稿フォーム");
		model.addAttribute("cont", "アンケートを入力してください。");
		
		return "survey/form";
	}
	
	@PostMapping("/form")
	public String formGoBack(SurveyForm surveyForm,
			Model model) {
		
		model.addAttribute("title", "アンケート投稿フォーム");
		model.addAttribute("cont", "アンケートを入力してください。");
		
		return "survey/form";
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "アンケート投稿フォーム");
			model.addAttribute("cont", "アンケートを入力してください。");
			return "survey/form";
		}
		
		model.addAttribute("title", "アンケート投稿確認");
		model.addAttribute("cont", "これでよろしいでしょうか？");
		return "survey/confirm";
	}
	
	@PostMapping("/complete")
	public String complete(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "アンケート投稿フォーム");
			model.addAttribute("cont", "以下の項目を入力してください。");
			return "survey/form";
		}
		
		SurveyModel survey = new SurveyModel();
		survey.setName(surveyForm.getName());
		survey.setAge(surveyForm.getAge());
		survey.setProfession(surveyForm.getProfession());
		survey.setMen_or_female(surveyForm.getMen_or_female());
		survey.setSatisfaction(surveyForm.getSatisfaction());
		survey.setComment(surveyForm.getComment());
		survey.setCreated(LocalDateTime.now());
		
		surveyService.save(survey);
		redirectAttributes.addFlashAttribute("complete", "投稿ありがとうございました！");
		return "redirect:/survey/form";
	}
	
	@GetMapping("/satis")
	public String satisfaction(
			Model model) {
		// アンケート統計画面一覧
		model.addAttribute("title", "アンケート統計");
		model.addAttribute("cont", "アンケートを集計した一覧です。");
		List<SurveySatisForm> list = this.surveyService.countSatisfactionAll();
		model.addAttribute("satisList", list);
		return "/survey/satistics";
	}
	
	@RequestMapping("/satis_reply")
	@ResponseBody
	public SurveyChangeForm satis_reply(
			@RequestBody SurveyChangeForm surveyChangeForm) {
		List<SurveySatisForm> list = null;
		if(surveyChangeForm.getId() == 1) {
			// 満足度集計
			if(surveyChangeForm.getAllNum() == 1) {
				// 全体満足度集計
				list = this.surveyService.countSatisfactionAll();
			}else {
				String sxName = surveyChangeForm.getSxName();
				String profName = surveyChangeForm.getProfName();
				String ageName = surveyChangeForm.getAgeName();
				int outsxName = 0;
				int outprofName = 0;
				int outageName = 0;
				
				if(!sxName.isEmpty()) {
					//性別集計
					switch(sxName) {
						// 男性別集計
						case "men-button":		outsxName = 1;	break;
						// 女性別集計
						case "famale-button":	outsxName = 2;	break;
						default:				outsxName = 0;	break;
					}
				}
				if(!profName.isEmpty()) {
					//職業別集計
					switch(profName) {
						//学生別集計
						case "student-button":	outprofName = 1;	break;
						//会社員別集計
						case "emplo-button":	outprofName = 2;	break;
						//芸能人別集計
						case "art-button":		outprofName = 3;	break;
						//主婦別集計
						case "wife-button":		outprofName = 4;	break;
						default:				outprofName = 0;	break;
					}
				}
				if(!ageName.isEmpty()) {
					// 年齢別集計
					if(!ageName.equals("main-age-button")) {
						String age = ageName.substring(4,6);
						outageName = Integer.parseInt(age);
					}else {
						outageName = 0;
					}
				}
				
				if(outsxName != 0 && outprofName != 0 && outageName != 0) {
					// 全て該当
					list = surveyService.countSatisfactionSxProfAge(outsxName, outprofName, outageName);
				}
				else if(outsxName != 0 && outprofName != 0) {
					// 性別＆職業別
					list = surveyService.countSatisfactionSxProf(outsxName, outprofName);
				}else if(outsxName != 0 && outageName != 0) {
					// 性別＆年齢別
					list = surveyService.countSatisfactionSxAge(outsxName, outageName);
				}else if(outprofName != 0 && outageName != 0) {
					// 職業＆年齢別
					list = surveyService.countSatisfactionProfAge(outprofName, outageName);
				}else if(outsxName != 0){
					// 性別別
					list = surveyService.countSatisfactionSx(outsxName);
				}else if(outprofName != 0) {
					// 職業別
					list = surveyService.countSatisfactionProf(outprofName);
				}else if(outageName != 0) {
					// 年齢別
					list = surveyService.countSatisfactionAge(outageName); 
				}else {
					// 全て
					list = this.surveyService.countSatisfactionAll();
				}
			}
		}
		
		if( list != null ) {	
			// 設定
			surveyChangeForm.setSatis5(list.get(0).getSatisfaction());
			surveyChangeForm.setSatis4(list.get(1).getSatisfaction());
			surveyChangeForm.setSatis3(list.get(2).getSatisfaction());
			surveyChangeForm.setSatis2(list.get(3).getSatisfaction());
			surveyChangeForm.setSatis1(list.get(4).getSatisfaction());
			
			surveyChangeForm.setAnswerCnt(
					list.get(0).getSatisfaction() + 
					list.get(1).getSatisfaction() + 
					list.get(2).getSatisfaction() + 
					list.get(3).getSatisfaction() + 
					list.get(4).getSatisfaction() );
		}	
		return surveyChangeForm;
	}
	
	private void setPaging(List<SurveyModel> list, int pageidx, Model model) {
		// TODO ページング設定
		PageController page = new PageController();
		page.setPage1Cnt(5);
		List<SurveyModel> surveyList = new ArrayList<SurveyModel>();
		List<Integer> pageList = new ArrayList<Integer>();
		
		int surveySize = list.size();
		int paging = (int)(surveySize / page.getPage1Cnt()) + 1;
		int idx = ((pageidx-1) * page.getPage1Cnt());
		
		// ページによるブログ投稿リストの再作成
		for(int cnt=0, pagelen=page.getPage1Cnt(); idx<surveySize && cnt<pagelen; idx++, cnt++){
			surveyList.add(list.get(idx));
		}
		for(int cnt=1; cnt<paging+1; cnt++) {
			pageList.add(cnt);
		}
		
		// ページクラスに設定
		page.setListSize(surveySize);
		page.setPageSize(paging);
		page.setCurrentID(pageidx);
		page.setPageNumberList(pageList);
		page.setPageName("/survey");
		
		model.addAttribute("surveyList", surveyList);
		model.addAttribute("paging", page);
	}
	
}
