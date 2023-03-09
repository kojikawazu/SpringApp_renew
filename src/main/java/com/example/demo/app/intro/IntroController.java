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

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.intro.IntroService;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.session.user.SessionModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.LogMessage;
import com.example.demo.common.word.NormalWord;
import com.example.demo.json.model.ExperienceModel;
import com.example.demo.json.model.IntroJSONModel;

/**
 * 自己紹介コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_INTRO)
public class IntroController extends SuperIntroController {

	/** SuccessedのURL */
	private static final String RETUEN_SUCCESSED_URL 	= AppConsts.URL_INTRO_INDEX;
	/** HOMEへリダイレクトするURL */
	private static final String REDIRECT_HOME_URL		= AppConsts. REDIRECT_URL_HOME;

	/** Jsonパス */
	private static final String JSON_DIR 				= "src/main/resources/static/json";
	/** 自己紹介ファイル */
	private static final String INTRO_JSON_FILE 		= "intro.json";
	/** Jsonパス */
	private static final String INTRO_JSON_PATH 		= JSON_DIR + "/" + INTRO_JSON_FILE;

	/** attributeリスト番号 */
	private static enum ATTRIBUTE_LIST_ENUM {
		A_INTRO, 
		A_EXPERIENCE, 
		A_FUTURE_PLAN, 
		A_SKILL_LANGUAGE, 
		A_SKILL_LIBRARY,
		A_SKILL_FRAMEWORK,
		A_SKILL_OS,
		A_SKILL_TOOL,
		A_SKILL_OTHER,
		A_URL,
		A_HOBBY,
		A_ONE_LAST
	};

	/** attribute番号 */
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

		/** jsonデータの読み込み */
		IntroJSONModel jsonModel = this.getIntroService().readerIntroData_byJSON(
									Paths.get(INTRO_JSON_PATH));
		if (jsonModel == null) {
			// jsonデータの読み込みに失敗した為、homeへリダイレクト
			return REDIRECT_HOME_URL;
		}

		/** 名前 */
		this.setNameAttribute(jsonModel, model);
		/** 紹介文 */
		this.setIntroAttribute(jsonModel, model);
		/** 経験タイトル */
		this.setExperienceTitleAttribute(jsonModel, model);
		/** 経験 */
		this.setExperienceAttribute(jsonModel,  model);
		/** 今後やりたいこと */
		this.setFuturePlanAttribute(jsonModel,  model);
		/** スキル言語 */
		this.setSkillLanguageAttribute(jsonModel,  model);
		/** スキルライブラリ */
		this.setSkillLibraryAttribute(jsonModel,  model);
		/** スキルフレームワーク */
		this.setSkillFrameworkAttribute(jsonModel,  model);
		/** スキルOS */
		this.setSkillOSAttribute(jsonModel,  model);
		/** スキルTool */
		this.setSkillToolAttribute(jsonModel, model);
		/** スキルその他 */
		this.setSkillOtherAttribute(jsonModel, model);
		/** URL */
		this.setURLAttribute(jsonModel, model);
		/** 趣味 */
		this.setHobbyAttribute(jsonModel, model);
		/** 最後に一言 */
		this.setOneLastAttribute(jsonModel, model);

		/** ヘッダーの設定 */
		this.getHeaderController().setHeader(detailUser, request, headerForm, model);

		return RETUEN_SUCCESSED_URL;
	}

	/**
	 * タイトルattribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setNameAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> nameList = jsonModel.getNameList().getList();

		model.addAttribute(
				WebConsts.ATTRIBUTE_TITLE, 
				nameList.get(ATTRIBUTE_ENUM.TITLE.ordinal()));
		model.addAttribute(
				WebConsts.ATTRIBUTE_CONT,  
				nameList.get(ATTRIBUTE_ENUM.CONT.ordinal()));
	}

	/**
	 * 紹介文attribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setIntroAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				jsonModel.getIntro().getWord());
	}

	/**
	 * 経験タイトルattribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setExperienceTitleAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> experTittleKeyList      = jsonModel.getExperienceTitleModel().getExperienceTitleKeyList().getList();
		List<String> experTitleBodyList      = jsonModel.getExperienceTitleModel().getExperienceTitleBodyList().getList();
		int index = 0;

		for (String experTittleKey : experTittleKeyList) {
			model.addAttribute(experTittleKey, experTitleBodyList.get(index) + " :");
			index++;
		}
	}

	/**
	 * 経験attribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setExperienceAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal();
		List<ExperienceModel> experList = jsonModel.getExperienceList().getList();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				experList);
	}

	/**
	 * 今後やりたいことattribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setFuturePlanAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_FUTURE_PLAN.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				jsonModel.getFuturePlanList().getList());
	}

	/**
	 * 言語attribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setSkillLanguageAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				jsonModel.getSkillLanguageList());
	}

	/**
	 * ライブラリatttribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setSkillLibraryAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				jsonModel.getSkillLibraryList());
	}

	/**
	 * フレームワークattribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setSkillFrameworkAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				jsonModel.getSkillFrameworkList());
	}

	/**
	 * OSattribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setSkillOSAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_SKILL_OS.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				jsonModel.getSkillOSList());
	}

	/**
	 * Toolattribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setSkillToolAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_SKILL_TOOL.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				jsonModel.getSkillToolList());
	}

	/**
	 * その他attribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setSkillOtherAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				jsonModel.getSkillOtherList());
	}

	/**
	 * URLattribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setURLAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		NormalWord   url			= jsonModel.getUrl();
		int          index			= ATTRIBUTE_LIST_ENUM.A_URL.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				url.getWord());
	}

	/**
	 * 趣味attribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model		{@link Model}
	 */
	private void setHobbyAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		int          index			= ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				jsonModel.getHobbyList());
	}

	/**
	 * 最後に一言attribute設定
	 * @param jsonModel {@link IntroJSONModel}
	 * @param model     {@link Model}
	 */
	private void setOneLastAttribute(IntroJSONModel jsonModel, Model model) {
		List<String> titleKeyList 	= jsonModel.getAttributeKeyModel().getTitleKeyList().getList();
		List<String> bodyKeyList  	= jsonModel.getAttributeKeyModel().getBodyKeyList().getList();
		List<String> titleList 		= jsonModel.getTitleList().getList();
		NormalWord   oneLast		= jsonModel.getOneLast();
		int          index			= ATTRIBUTE_LIST_ENUM.A_ONE_LAST.ordinal();

		model.addAttribute(
				titleKeyList.get(index),
				titleList.get(index));
		model.addAttribute(
				bodyKeyList.get(index),
				oneLast.getWord());
	}
}
