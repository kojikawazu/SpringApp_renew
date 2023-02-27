package com.example.demo.json.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.list.WordList;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.NormalWord;
import com.example.demo.json.common.JsonCommonConstants;
import com.example.demo.json.common.JsonCommonFunctions;
import com.example.demo.json.list.ExperienceList;
import com.example.demo.json.model.ExperienceModel;
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
		A_NAME, 
		A_TITLE,
		A_INTRO,
		A_EXPERIENCE_HEADER,
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
		A_WORD
	};

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
	@Autowired
	public IntroJsonReader() {
		
	}

	/**
	 * Json読取
	 * @param path 	{@link Path} 			パス
	 * @return 		{@link IntroJSONModel} 	自己紹介JSONモデル
	 */
	@Override
	public IntroJSONModel reader(Path path) {
		// JSON読み取り
		IntroJSONModel model = null;
		try {
			ObjectMapper mapper   = new ObjectMapper();
			JsonNode rootJsonNode = mapper.readTree(path.toFile());

			/** ヘッダー */
			WordList 		headerList			= JsonCommonFunctions.readData_returnList(rootJsonNode, JsonCommonConstants.JSON_SECTION_HEADER);
			List<String> 	headerStringList	= headerList.getList();
			/** 名前 */
			WordList 		nameList			= JsonCommonFunctions.readData_returnList(rootJsonNode,  
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_NAME.ordinal()));
			/** タイトル */
			WordList 		titleList			= JsonCommonFunctions.readData_returnList(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_TITLE.ordinal()));
			/** 紹介文 */
			NormalWord 		intro				= JsonCommonFunctions.readData(rootJsonNode,
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal()));
			/** 経験 */
			ExperienceList 	experienceList  	= this.readExperienceList(rootJsonNode, headerList);
			/** 今後やりたいこと */
			WordList 		afterList			= JsonCommonFunctions.readData_returnList(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_AFTER.ordinal()));
			/** スキル(言語) */
			WordList 		skillLanguageList	= JsonCommonFunctions.readData_returnList(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal()));
			/** スキル(ライブラリ) */
			WordList 		skillLibraryList	= JsonCommonFunctions.readData_returnList(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal()));
			/** スキル(フレームワーク) */
			WordList 		skillFrameworkList	= JsonCommonFunctions.readData_returnList(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal()));
			/** スキル(OS) */
			WordList 		skillOSList			= JsonCommonFunctions.readData_returnList(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_OS.ordinal()));
			/** スキル(Tool) */
			WordList 		skillToolList		= JsonCommonFunctions.readData_returnList(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_TOOL.ordinal()));
			/** スキル(その他) */
			WordList 		skillOtherList		= JsonCommonFunctions.readData_returnList(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal()));
			/** URL */
			NormalWord 		url					= JsonCommonFunctions.readData(rootJsonNode,
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_URL.ordinal()));
			/** 趣味 */
			WordList 		hobbyList			= JsonCommonFunctions.readData_returnList(rootJsonNode,
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal()));
			/** 最後に */
			NormalWord 		word				= JsonCommonFunctions.readData(rootJsonNode,
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_WORD.ordinal()));

			model = new IntroJSONModel(
					// 名前
					nameList,
					// タイトル
					titleList,
					// 紹介文
					intro,
					// 経験
					experienceList,
					// 今後やりたいこと
					afterList,
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
					word
					);

		} catch(IOException ioe) {
			ioe.printStackTrace();
		}

		return model;
	}

	/**
	 * データリストの読み込み
	 * @param rootJsonNode	{@link JsonNode} 		jsonノード
	 * @param headerList	{@link WordList}		ヘッダーリスト
	 * @return 				{@link ExperienceList}	経験リスト
	 */
	private ExperienceList readExperienceList(JsonNode rootJsonNode, WordList headerList) {
		if (rootJsonNode == null || headerList == null || headerList.getList().isEmpty())	return new ExperienceList();

		ExperienceList 			list 				= new ExperienceList();
		List<ExperienceModel> 	experList 			= list.getList();
		List<String> 			headerStringList	= headerList.getList();
		String					experKeyword		= "";
		WordList 				experHeaderList		= null;

		try {
			// キーワード取得
			experKeyword = headerStringList.get(ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal());

			// 経験ヘッダー取得
			experHeaderList = JsonCommonFunctions.readData_returnList(rootJsonNode, 
					headerStringList.get(ATTRIBUTE_LIST_ENUM.A_EXPERIENCE_HEADER.ordinal()));
		} catch(NullPointerException | IndexOutOfBoundsException ex) {
			// 取得失敗
			return list;
		}

		for (JsonNode node : rootJsonNode.get(experKeyword)) {
			try {
				/** 経験    */
				NormalWord exper 			= new NormalWord(
												node.get(experHeaderList.getList().get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_NAME.ordinal())).asText());
				/** 開始日付 */
				String start 				= node.get(experHeaderList.getList().get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_START.ordinal())).asText();
				/** 終了日付 */
				String end 					= node.get(experHeaderList.getList().get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_END.ordinal())).asText();
				/** メンバー人数 */
				NormalNumber memberSum		= new NormalNumber(
												Integer.valueOf(
													node.get(
														experHeaderList.getList().get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_MEMBER_SUM.ordinal())).asText()));
				/** ポジション */
				NormalWord position			= new NormalWord(
												node.get(experHeaderList.getList().get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_POSITION.ordinal())).asText());
				/** 概要 */
				NormalWord overview			= JsonCommonFunctions.readDataLongString(node, 
												experHeaderList.getList().get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_OVERVIEW.ordinal()));
				/** 担当業務 */
				WordList personChargeList	= JsonCommonFunctions.readData_returnList(node, 
												experHeaderList.getList().get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_PERSON_CHARGE.ordinal()));
				/** 業務ポイント */
				NormalWord workPoint		= JsonCommonFunctions.readDataLongString(node, 
												experHeaderList.getList().get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_WORK_POINT.ordinal()));
				/** 技術リスト */
				WordList techList			= JsonCommonFunctions.readData_returnList(node, 
												experHeaderList.getList().get(ATTRIBUTE_EXPERIENCE_LIST_ENUM.A_EXPER_TECH.ordinal()));

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
			}
		}

		return list;
	}
}
