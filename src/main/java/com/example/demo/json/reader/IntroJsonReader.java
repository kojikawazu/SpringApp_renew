package com.example.demo.json.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.common.list.WordList;
import com.example.demo.common.log.JsonLogWriter;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.NormalWord;
import com.example.demo.json.common.JsonCommonConstants;
import com.example.demo.json.common.JsonCommonFunctions;
import com.example.demo.json.list.ExperienceList;
import com.example.demo.json.model.AttributeKeyModel;
import com.example.demo.json.model.ExperienceModel;
import com.example.demo.json.model.ExperienceTitleModel;
import com.example.demo.json.model.IntroJSONModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json読み込みクラス
 * @author nanai
 *
 */
@Repository
public class IntroJsonReader implements SuperJsonReader {

	/** リスト番号 */
	private static enum ATTRIBUTE_LIST_ENUM {
		A_ATTR_HEADER,
		A_ATTR,
		A_NAME, 
		A_TITLE,
		A_INTRO,
		A_EXPERIENCE_TITLE_HEADER,
		A_EXPERIENCE_TITLE,
		A_EXPERIENCE_HEADER,
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

	/** attributeKeyリスト番号 */
	private static enum ATTRIBUTE_KEY_HEADER_LIST_ENUM {
		A_TITLE_KEY,
		A_BODY_KEY
	}

	/** 経験タイトル番号 */
	private static enum ATTRIBUTE_EXPERIENCE_TITLE_LIST_ENUM {
		A_EXPER_TITLE_KEY,
		A_EXPER_TITLE_BODY
	}
	
	/** 経験リスト番号 */
	private static enum ATTRIBUTE_EXPERIENCE_LIST_ENUM {
		A_EXPER_NAME,
		A_EXPER_START,
		A_EXPER_END,
		A_EXPER_MEMBER_SUM,
		A_EXPER_POSITION,
		A_EXPER_OVERVIEW,
		A_EXPER_PERSON_CHARGE,
		A_EXPER_WORK_POINT,
		A_EXPER_TECH
	};

	/**
	 * コンストラクタ
	 */
	public IntroJsonReader() {
		
	}

	/**
	 * Json読取
	 * @param path 	{@link Path} 			パス
	 * @return 		{@link IntroJSONModel} 	自己紹介JSONモデル
	 */
	@Override
	public IntroJSONModel reader(Path path) {
		JsonLogWriter 	jsonLogWriter 	= JsonLogWriter.getInstance();
		IntroJSONModel 	model 			= null;

		jsonLogWriter.setLogLevelAll();
		jsonLogWriter.start("");
		jsonLogWriter.setLogLevelWarning();

		// ファイルの場所
		jsonLogWriter.error("File path. [" + path.getFileName() + "]");
		if (!path.toFile().exists()) {
			// ファイル存在しないと読み込みエラー
			jsonLogWriter.error("File not exists. [" + path.getFileName() + "]");
			return model;
		}

		try {
			// json読み込み
			ObjectMapper mapper   = new ObjectMapper();
			JsonNode rootJsonNode = mapper.readTree(path.toFile());

			/** ヘッダー */
			WordList 		headerList			= JsonCommonFunctions.readData_returnList(rootJsonNode, JsonCommonConstants.JSON_SECTION_HEADER);
			List<String> 	headerStringList	= headerList.getList();

			/** attributeKey */
			AttributeKeyModel		attrKeyModel		= this.readAttributeKeyModel(rootJsonNode, headerList);
			/** 名前 */
			WordList 				nameList			= JsonCommonFunctions.readData_returnList(rootJsonNode,  
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_NAME.ordinal()));
			/** タイトル */
			WordList 				titleList			= JsonCommonFunctions.readData_returnList(rootJsonNode, 
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_TITLE.ordinal()));
			/** 紹介文 */
			NormalWord 				intro				= JsonCommonFunctions.readData(rootJsonNode,
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal()));
			/** 経験タイトル */
			ExperienceTitleModel	experTitleModel		= this.readerExperienceTitleModel(rootJsonNode, headerList);
			/** 経験 */
			ExperienceList 			experienceList  	= this.readExperienceList(rootJsonNode, headerList);
			/** 今後やりたいこと */
			WordList 				futurePlanList		= JsonCommonFunctions.readData_returnList(rootJsonNode, 
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_FUTURE_PLAN.ordinal()));
			/** スキル(言語) */
			WordList 				skillLanguageList	= JsonCommonFunctions.readData_returnList(rootJsonNode, 
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal()));
			/** スキル(ライブラリ) */
			WordList 				skillLibraryList	= JsonCommonFunctions.readData_returnList(rootJsonNode, 
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal()));
			/** スキル(フレームワーク) */
			WordList 				skillFrameworkList	= JsonCommonFunctions.readData_returnList(rootJsonNode, 
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal()));
			/** スキル(OS) */
			WordList 				skillOSList			= JsonCommonFunctions.readData_returnList(rootJsonNode, 
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_OS.ordinal()));
			/** スキル(Tool) */
			WordList 				skillToolList		= JsonCommonFunctions.readData_returnList(rootJsonNode, 
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_TOOL.ordinal()));
			/** スキル(その他) */
			WordList 				skillOtherList		= JsonCommonFunctions.readData_returnList(rootJsonNode, 
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal()));
			/** URL */
			NormalWord 				url					= JsonCommonFunctions.readData(rootJsonNode,
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_URL.ordinal()));
			/** 趣味 */
			WordList 				hobbyList			= JsonCommonFunctions.readData_returnList(rootJsonNode,
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal()));
			/** 最後に */
			NormalWord 		 		oneLast				= JsonCommonFunctions.readData(rootJsonNode,
															headerStringList.get(ATTRIBUTE_LIST_ENUM.A_ONE_LAST.ordinal()));

			// モデル生成
			model = new IntroJSONModel(
					// attrbuteKey
					attrKeyModel,
					// 名前
					nameList,
					// タイトル
					titleList,
					// 紹介文
					intro,
					// 経験タイトル
					experTitleModel,
					// 経験
					experienceList,
					// 今後やりたいこと
					futurePlanList,
					// スキル(言語)
					skillLanguageList,
					// スキル(ライブラリ)
					skillLibraryList,
					// スキル(フレームワーク)
					skillFrameworkList,
					// スキル(OS)
					skillOSList,
					// スキル(Tool)
					skillToolList,
					// スキル(その他)
					skillOtherList,
					// URL
					url,
					// 趣味
					hobbyList,
					// 最後に
					oneLast
					);

		} catch(IOException ioe) {
			jsonLogWriter.error(ioe.getMessage());
		}
		jsonLogWriter.setLogLevelAll();
		jsonLogWriter.successed("model isNotNull(): " + (model != null));
		jsonLogWriter.setLogLevelWarning();

		return model;
	}

	/**
	 * attributeKeyデータリストの読み込み
	 * @param rootJsonNode	{@link JsonNode} 			jsonノード
	 * @param headerList	{@link WordList}			ヘッダーリスト
	 * @return 				{@link AttributeKeyModel}	attributeKeyリスト
	 */
	private AttributeKeyModel readAttributeKeyModel(JsonNode rootJsonNode, WordList headerList) {
		if (rootJsonNode == null || headerList == null || headerList.getList().isEmpty())	return new AttributeKeyModel();
		JsonLogWriter		jsonLogWriter			= JsonLogWriter.getInstance();
		AttributeKeyModel	model					= new AttributeKeyModel();
		List<String>		headerStringList 		= headerList.getList();
		String				attrKeyword				= "";
		WordList 			attrHeaderList			= null;
		List<String>		attrHeaderStringList 	= null; 

		jsonLogWriter.setLogLevelAll();
		jsonLogWriter.start("");
		jsonLogWriter.setLogLevelWarning();

		try {
			// キーワード取得
			attrKeyword = headerStringList.get(ATTRIBUTE_LIST_ENUM.A_ATTR.ordinal());

			// attributeKeyヘッダー取得
			attrHeaderList = JsonCommonFunctions.readData_returnList(rootJsonNode, 
					headerStringList.get(ATTRIBUTE_LIST_ENUM.A_ATTR_HEADER.ordinal()));
			attrHeaderStringList = attrHeaderList.getList();
		} catch(NullPointerException | IndexOutOfBoundsException ex) {
			// 取得失敗
			jsonLogWriter.error(ex.getMessage());
			return model;
		}

		for (JsonNode node : rootJsonNode.get(attrKeyword)) {
			/** タイトルキーリスト */
			WordList 	titleKeyList	= JsonCommonFunctions.readData_returnList(node, 
											attrHeaderStringList.get(ATTRIBUTE_KEY_HEADER_LIST_ENUM.A_TITLE_KEY.ordinal()));
			/** ボディキーリスト  */
			WordList 	bodyKeyList		= JsonCommonFunctions.readData_returnList(node, 
											attrHeaderStringList.get(ATTRIBUTE_KEY_HEADER_LIST_ENUM.A_BODY_KEY.ordinal()));

			// モデル設定
			model.setTitleKeyList(titleKeyList);
			model.setBodyKeyList(bodyKeyList);
		}

		jsonLogWriter.setLogLevelAll();
		jsonLogWriter.successed("list titleKey size(): " + model.getTitleKeyList().getList().size());
		jsonLogWriter.successed("list bodyKey  size(): " + model.getBodyKeyList().getList().size());
		jsonLogWriter.setLogLevelWarning();
		return model;
	}

	/**
	 * 経験タイトルモデル読み込み
	 * @param rootJsonNode	{@link JsonNode} 				jsonノード
	 * @param headerList	{@link WordList}				ヘッダーリスト
	 * @return 				{@link ExperienceTitleModel}	経験タイトルモデル
	 * @return
	 */
	private ExperienceTitleModel readerExperienceTitleModel(JsonNode rootJsonNode, WordList headerList) {
		if (rootJsonNode == null || headerList == null || headerList.getList().isEmpty())	return new ExperienceTitleModel();
		JsonLogWriter 			jsonLogWriter 					= JsonLogWriter.getInstance();
		ExperienceTitleModel	experTitleModel					= new ExperienceTitleModel();
		List<String> 			headerStringList				= headerList.getList();
		String					experTitleKeyword				= "";
		WordList 				experTitleHeaderList			= null;
		List<String>			experHeaderTittleStringList 	= null;

		jsonLogWriter.setLogLevelAll();
		jsonLogWriter.start("");
		jsonLogWriter.setLogLevelWarning();
		try {
			// キーワード取得
			experTitleKeyword = headerStringList.get(ATTRIBUTE_LIST_ENUM.A_EXPERIENCE_TITLE.ordinal());

			// 経験ヘッダー取得
			experTitleHeaderList = JsonCommonFunctions.readData_returnList(rootJsonNode, 
					headerStringList.get(ATTRIBUTE_LIST_ENUM.A_EXPERIENCE_TITLE_HEADER.ordinal()));
			experHeaderTittleStringList = experTitleHeaderList.getList();
		} catch(NullPointerException | IndexOutOfBoundsException ex) {
			// 取得失敗
			jsonLogWriter.error(ex.getMessage());
			return experTitleModel;
		}

		for (JsonNode node : rootJsonNode.get(experTitleKeyword)) {
			try {
				/** 経験タイトルattributeKey */
				WordList experTitleKeyList		= JsonCommonFunctions.readData_returnList(node, 
													experHeaderTittleStringList.get(ATTRIBUTE_EXPERIENCE_TITLE_LIST_ENUM.A_EXPER_TITLE_KEY.ordinal()));
				/** 経験タイトルbody */
				WordList experTitleBodyList		= JsonCommonFunctions.readData_returnList(node, 
													experHeaderTittleStringList.get(ATTRIBUTE_EXPERIENCE_TITLE_LIST_ENUM.A_EXPER_TITLE_BODY.ordinal()));

				experTitleModel.setExperienceTitleKeyList(experTitleKeyList);
				experTitleModel.setExperienceTitleBodyList(experTitleBodyList);
			}catch(NullPointerException | IndexOutOfBoundsException ex) {
				// 取得失敗
				jsonLogWriter.error(ex.getMessage());
			}
		}

		jsonLogWriter.setLogLevelAll();
		jsonLogWriter.info("experienceTitleKeyList size(): " + experTitleModel.getExperienceTitleKeyList().getList().size());
		jsonLogWriter.info("experienceTitleBodyList size(): " + experTitleModel.getExperienceTitleBodyList().getList().size());
		jsonLogWriter.setLogLevelWarning();

		return experTitleModel;
	}
	
	/**
	 * 経験データリストの読み込み
	 * @param rootJsonNode	{@link JsonNode} 		jsonノード
	 * @param headerList	{@link WordList}		ヘッダーリスト
	 * @return 				{@link ExperienceList}	経験リスト
	 */
	private ExperienceList readExperienceList(JsonNode rootJsonNode, WordList headerList) {
		if (rootJsonNode == null || headerList == null || headerList.getList().isEmpty())	return new ExperienceList();
		JsonLogWriter 			jsonLogWriter 			= JsonLogWriter.getInstance();
		ExperienceList 			list 					= new ExperienceList();
		List<ExperienceModel> 	experList 				= list.getList();
		List<String> 			headerStringList		= headerList.getList();
		String					experKeyword			= "";
		WordList 				experHeaderList			= null;
		List<String>			experHeaderStringList 	= null;

		jsonLogWriter.setLogLevelAll();
		jsonLogWriter.start("");
		jsonLogWriter.setLogLevelWarning();
		try {
			// キーワード取得
			experKeyword = headerStringList.get(ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal());

			// 経験ヘッダー取得
			experHeaderList = JsonCommonFunctions.readData_returnList(rootJsonNode, 
					headerStringList.get(ATTRIBUTE_LIST_ENUM.A_EXPERIENCE_HEADER.ordinal()));
			experHeaderStringList = experHeaderList.getList();
		} catch(NullPointerException | IndexOutOfBoundsException ex) {
			// 取得失敗
			jsonLogWriter.error(ex.getMessage());
			return list;
		}

		for (JsonNode node : rootJsonNode.get(experKeyword)) {
			try {
				/** 経験    */
				NormalWord exper 			= new NormalWord(
												node.get(experHeaderStringList.get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_NAME.ordinal())).asText());
				/** 開始日付 */
				String start 				= node.get(experHeaderStringList.get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_START.ordinal())).asText();
				/** 終了日付 */
				String end 					= node.get(experHeaderStringList.get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_END.ordinal())).asText();
				/** メンバー人数 */
				NormalNumber memberSum		= new NormalNumber(
												Integer.valueOf(
													node.get(
														experHeaderStringList.get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_MEMBER_SUM.ordinal())).asText()));
				/** ポジション */
				NormalWord position			= new NormalWord(
												node.get(experHeaderStringList.get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_POSITION.ordinal())).asText());
				/** 概要 */
				NormalWord overview			= JsonCommonFunctions.readDataLongString(node, 
												experHeaderStringList.get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_OVERVIEW.ordinal()));
				/** 担当業務 */
				WordList personChargeList	= JsonCommonFunctions.readData_returnList(node, 
												experHeaderStringList.get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_PERSON_CHARGE.ordinal()));
				/** 業務ポイント */
				NormalWord workPoint		= JsonCommonFunctions.readDataLongString(node, 
												experHeaderStringList.get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_WORK_POINT.ordinal()));
				/** 技術リスト */
				WordList techList			= JsonCommonFunctions.readData_returnList(node, 
												experHeaderStringList.get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_TECH.ordinal()));

				// String型 → LocalDateTime型へ変換
				LocalDateTime 		startLocalDateTime 	= JsonCommonFunctions.changeLocalDateTime(start);
				LocalDateTime 		endLocalDateTime 	= JsonCommonFunctions.changeLocalDateTime(end);

				// モデル生成
				ExperienceModel model = new ExperienceModel(
						exper,
						startLocalDateTime,
						endLocalDateTime,
						memberSum,
						position,
						overview,
						personChargeList,
						workPoint,
						techList);

				// リスト追加
				experList.add(model);
			} catch(NullPointerException | IndexOutOfBoundsException ex) {
				// 取得失敗
				jsonLogWriter.error(ex.getMessage());
			}
		}
		jsonLogWriter.setLogLevelAll();
		jsonLogWriter.successed("list size(): " + list.getList().size());
		jsonLogWriter.setLogLevelWarning();

		return list;
	}
}
