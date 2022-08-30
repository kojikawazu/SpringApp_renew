package com.example.demo.app.intro;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.service.IntroService;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.entity.IntroJSONModel;

/**
 * 自己紹介コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INTRO)
public class IntroController {
	
	/** Jsonパス */
	private static final String JSON_PATH = "src/main/resources/static/json/intro.json";
	
	/** タイトルキーワード(配列) */
	private static final String[][] ATTRIBUTE_LIST = 
		{
			/** 自己紹介タイトル */
			{"introTitle", "intro"},
			/** 経験タイトル */
			{"experTitle", "experList"},
			/** 今後やりたいこと */
			{"afterTitle", "after"},
			/** スキル(言語) */
			{"skill1Title", "skill1List"},
			/** スキル(ライブラリ) */
			{"skill2Title", "skill2List"},
			/** スキル(フレームワーク) */
			{"skill3Title", "skill3List"},
			/** スキル(その他) */
			{"skill4Title", "skill4List"},
			/** URL */
			{"urlTitle", "url"},
			/** 趣味 */
			{"hobbyTitle", "hobbyList"},
			/** 最後に一言 */
			{"wordTitle", "word"},
		};
	
	/** タイトル番号 */
	private static enum ATTRIBUTE_LIST_ENUM {
		A_INTRO, 
		A_EXPERIENCE, 
		A_AFTER, 
		A_SKILL_LANGUAGE, 
		A_SKILL_LIBRARY,
		A_SKILL_FRAMEWORK,
		A_SKILL_OTHER,
		A_URL,
		A_HOBBY,
		A_WORK
	};
	
	/** タイトル番号2 */
	private static enum ATTRIBUTE_ENUM  {
		TITLE, CONT;
	};
	
	/** サービス */
	private final IntroService introService;

	/**
	 * コンストラクタ
	 * @param introService
	 */
	@Autowired
	public IntroController(IntroService introService) {
		this.introService = introService;
	}
	
	/**
	 * index
	 * @param model
	 * @return
	 */
	@GetMapping
	public String index(Model model) {
		
		IntroJSONModel jsonModel = this.introService.readerIntroData_byJSON(
				Paths.get(JSON_PATH));
		
		// 名前
		List<String> nameList = jsonModel.getNameList();
		model.addAttribute(
				WebConsts.ATTRIBUTE_TITLE, 
				nameList.get(ATTRIBUTE_ENUM.TITLE.ordinal()));
		model.addAttribute(
				WebConsts.ATTRIBUTE_CONT,  
				nameList.get(ATTRIBUTE_ENUM.CONT.ordinal()));
		
		// タイトル
		List<String> titleList = jsonModel.getTitleList();
		// 紹介文
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],
				jsonModel.getIntro());
		
		// 経験
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],  
				jsonModel.getExperienceList());
		
		// 今後やりたいこと
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_AFTER.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_AFTER.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_AFTER.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],
				jsonModel.getAfter());
		
		// スキル言語
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],  
				jsonModel.getSkill1List());
		
		// スキルライブラリ
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getSkill2List());
		
		
		// スキルフレームワーク
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],
				jsonModel.getSkill3List());
		
		// スキルその他
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getSkill4List());
		
		// URL
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_URL.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_URL.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_URL.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getUrl());
		
		// 趣味
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getHobbyList());
		
		// 最後に一言
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_WORK.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_WORK.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_WORK.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getWord());
		
		return AppConsts.URL_INTRO_INDEX;
	}
	
}
