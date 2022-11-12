package com.example.demo.app.intro;

import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.intro.IntroService;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.entity.ExperienceModel;
import com.example.demo.common.entity.IntroJSONModel;
import com.example.demo.common.log.LogMessage;

/**
 * 自己紹介コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INTRO)
public class IntroController extends SuperIntroController {
	
	/** Jsonパス */
	private static final String JSON_DIR 				= "src/main/resources/static/json";
	/** 自己紹介ファイル */
	private static final String INTRO_JSON_FILE 		= "intro.json";
	/** Jsonパス */
	private static final String INTRO_JSON_PATH 		= JSON_DIR + "/" + INTRO_JSON_FILE;
	
	/** タイトルキーワード(配列) */
	private static final String[][] ATTRIBUTE_LIST = 
		{
			/** 自己紹介タイトル */
			{"introTitle", 			"intro"},
			/** 経験タイトル */
			{"experTitle", 			"experList"},
			/** 今後やりたいこと */
			{"afterTitle", 			"afterList"},
			/** スキル(言語) */
			{"skillLanguageTitle", 	"skillLanguageList"},
			/** スキル(ライブラリ) */
			{"skillLibraryTitle", 	"skillLibraryList"},
			/** スキル(フレームワーク) */
			{"skillFrameworkTitle", "skillFrameworkList"},
			/** スキル(OS) */
			{"skillOSTitle", 		"skillOSList"},
			/** スキル(Tool) */
			{"skillToolTitle", 		"skillToolList"},
			/** スキル(その他) */
			{"skillOtherTitle", 	"skillOtherList"},
			/** URL */
			{"urlTitle", 			"url"},
			/** 趣味 */
			{"hobbyTitle", 			"hobbyList"},
			/** 最後に一言 */
			{"wordTitle", 			"word"},
		};
	
	/** タイトル番号 */
	private static enum ATTRIBUTE_LIST_ENUM {
		A_INTRO, 
		A_EXPERIENCE, 
		A_AFTER, 
		A_SKILL_LANGUAGE, 
		A_SKILL_LIBRARY,
		A_SKILL_FRAMEWORK,
		A_SKILL_OS,
		A_SKILL_TOOL,
		A_SKILL_OTHER,
		A_URL,
		A_HOBBY,
		A_WORK
	};
	
	/** タイトル番号2 */
	private static enum ATTRIBUTE_ENUM  {
		TITLE, CONT;
	};
	
	/** ---------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param introService		{@link IntroService}
	 * @param secUserService	{@link SecurityUserServiceUse}
	 * @param loginService		{@link LoginServiceUse}
	 * @param sessionModel		{@link SessionModel}
	 * @param httpSession		{@link HttpSession}
	 * @param logMessage		{@link LogMessage}
	 */
	@Autowired
	public IntroController(
			IntroService 			introService,
			SecurityUserServiceUse	secUserService,
			LoginServiceUse			loginService,
			SessionModel			sessionModel,
			HttpSession				httpSession,
			LogMessage				logMessage) {
		super(introService,
				secUserService,
				loginService,
				sessionModel,
				httpSession,
				logMessage);
	}
	
	/**
	 * index
	 * @param  detailUser	{@link SecLoginUserDetails}
	 * @param  request		{@link HttpServletRequest}
	 * @param  response		{@link HttpServletResponse}
	 * @param  headerForm	{@link HeaderForm}
	 * @param  model		{@link Model}
	 * @return {@value AppConsts#URL_INTRO_INDEX}
	 */
	@GetMapping
	public String index(
			@AuthenticationPrincipal SecLoginUserDetails	detailUser,
			HttpServletRequest								request,
			HttpServletResponse 							response,
			HeaderForm 										headerForm,
			Model											model) {
		/** Cookieの設定 */
		this.getHeaderController().setCookie(detailUser, request, response);
		
		IntroJSONModel jsonModel = this.getIntroService().readerIntroData_byJSON(
				Paths.get(INTRO_JSON_PATH));
		
		/** タイトル */
		List<String> titleList = jsonModel.getTitleList();
		
		/** 名前 */
		this.setNameAttribute(jsonModel, model);
		
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
		
		/** スキルOS */
		this.setSkillOSAttribute(jsonModel, titleList, model);
		
		/** スキルTool */
		this.setSkillToolAttribute(jsonModel, titleList, model);
		
		/** スキルその他 */
		this.setSkillOtherAttribute(jsonModel, titleList, model);
		
		/** URL */
		this.setURLAttribute(jsonModel, titleList, model);
		
		/** 趣味 */
		this.setHobbyAttribute(jsonModel, titleList, model);
		
		/** 最後に一言 */
		this.setWordAttribute(jsonModel, titleList, model);
		
		/** ヘッダーの設定 */
		this.getHeaderController().setHeader(detailUser, request, headerForm, model);
		
		return AppConsts.URL_INTRO_INDEX;
	}
	
	/**
	 * タイトルattribute設定
	 * @param jsonModel
	 * @param model
	 */
	private void setNameAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> nameList = jsonModel.getNameList();
		model.addAttribute(
				WebConsts.ATTRIBUTE_TITLE, 
				nameList.get(ATTRIBUTE_ENUM.TITLE.ordinal()));
		model.addAttribute(
				WebConsts.ATTRIBUTE_CONT,  
				nameList.get(ATTRIBUTE_ENUM.CONT.ordinal()));
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
				jsonModel.getSkillLanguageList());
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
				jsonModel.getSkillLibraryList());
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
				jsonModel.getSkillFrameworkList());
	}
	
	/**
	 * OSattribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setSkillOSAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_OS.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_OS.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_OS.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],
				jsonModel.getSkillOSList());
	}
	
	/**
	 * Toolattribute設定
	 * @param jsonModel
	 * @param titleList
	 * @param model
	 */
	private void setSkillToolAttribute(IntroJSONModel jsonModel, List<String> titleList, Model model) {
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_TOOL.ordinal()][ATTRIBUTE_ENUM.TITLE.ordinal()], 
				titleList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_TOOL.ordinal()));
		model.addAttribute(
				ATTRIBUTE_LIST[ATTRIBUTE_LIST_ENUM.A_SKILL_TOOL.ordinal()][ATTRIBUTE_ENUM.CONT.ordinal()],
				jsonModel.getSkillToolList());
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
				jsonModel.getSkillOtherList());
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
