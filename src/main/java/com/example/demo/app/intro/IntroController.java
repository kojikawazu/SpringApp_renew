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
import com.example.demo.common.entity.ExperienceModel;
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
		
		/** タイトル */
		List<String> titleList = jsonModel.getTitleList();
		
		/** 紹介文 */
		this.setIntroAttribute(jsonModel, titleList, model);
		
		/** 経験 */
		this.setExperienceAttribute(jsonModel, titleList, model);
		
		/** 今後やりたいこと */
		this.setAfterAttribute(jsonModel, titleList, model);
		
		/** スキル言語 */
		this.setSkillLanguageAttribute(jsonModel, titleList, model);
		
		/** スキルライブラリ */
		this.setSkillLibraryAttribute(jsonModel, titleList, model);
		
		/** スキルフレームワーク */
		this.setSkillFrameworkAttribute(jsonModel, titleList, model);
		
		/** スキルその他 */
		this.setSkillOtherAttribute(jsonModel, titleList, model);
		
		/** URL */
		this.setURLAttribute(jsonModel, titleList, model);
		
		/** 趣味 */
		this.setHobbyAttribute(jsonModel, titleList, model);
		
		/** 最後に一言 */
		this.setWordAttribute(jsonModel, titleList, model);
		
		return AppConsts.URL_INTRO_INDEX;
	}
	
	/**
	 * 紹介文attribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setIntroAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],
				jsonModel.getIntro());
	}
	
	/**
	 * 経験attribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setExperienceAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		List<ExperienceModel> experList = jsonModel.getExperienceList();
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],  
				experList);
	}
	
	/**
	 * 今後やりたいことattribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setAfterAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_AFTER.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_AFTER.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_AFTER.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],
				jsonModel.getAfter());
	}
	
	/**
	 * 言語attribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setSkillLanguageAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],  
				jsonModel.getSkill1List());
	}
	
	/**
	 * ライブラリatttribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setSkillLibraryAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getSkill2List());
	}
	
	/**
	 * フレームワークattribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setSkillFrameworkAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],
				jsonModel.getSkill3List());
	}
	
	/**
	 * その他attribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setSkillOtherAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getSkill4List());
	}
	
	/**
	 * URLattribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setURLAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_URL.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_URL.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_URL.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getUrl());
	}
	
	/**
	 * 趣味attribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setHobbyAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getHobbyList());
	}
	
	/**
	 * 最後に一言attribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setWordAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_WORK.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_WORK.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_WORK.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()], 
				jsonModel.getWord());
	}
}
