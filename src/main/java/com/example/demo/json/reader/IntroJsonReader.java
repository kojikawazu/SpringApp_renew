package com.example.demo.json.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.number.NormalNumber;
import com.example.demo.json.list.AfterList;
import com.example.demo.json.list.ExperienceList;
import com.example.demo.json.list.IntroList;
import com.example.demo.json.list.SkillList;
import com.example.demo.json.model.ExperienceModel;
import com.example.demo.json.model.IntroJSONModel;
import com.example.demo.json.word.IntroWord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json読み込みクラス
 * @author nanai
 *
 */
@Repository
public class IntroJsonReader implements SuperJsonReader {
	
	/** タイトル番号 */
	private static enum ATTRIBUTE_LIST_ENUM {
		A_NAME, 
		A_TITLE,
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
		A_WORD
	};
	
	/** JSONキーワード */
	private static final String JSON_SECTION_KEYWORD		= "name";
	
	/** JSONキーワード(header) */
	private static final String JSON_SECTION_HEADER			= "header";
	/** JSONキーワード(start) */
	private static final String JSON_SECTION_START			= "start";
	/** JSONキーワード(end) */
	private static final String JSON_SECTION_END			= "end";
	/** JSONデータ(now) */
	private static final String JSON_VALUE_NOW				= "now";
	/** JSONデータ(memberSum) */
	private static final String JSON_SECTION_MEMBER_SUM		= "memberSum";
	/** JSONデータ(position) */
	private static final String JSON_SECTION_POSITION	 	= "position";
	/** JSONデータ(overview) */
	private static final String JSON_SECTION_OVERVIEW 		= "overview";
	/** JSONデータ(personCharge) */
	private static final String JSON_SECTION_PERSON_CHARGE 	= "personCharge";
	/** JSONデータ(workPoint) */
	private static final String JSON_SECTION_WORK_POINT		= "workPoint";
	/** JSONデータ(tech) */
	private static final String JSON_SECTION_TECH 			= "tech";
	
	/**
	 * コンストラクタ
	 */
	@Autowired
	public IntroJsonReader() {
		
	}

	/**
	 * Json読取
	 * @param path パス
	 */
	@Override
	public IntroJSONModel reader(Path path) {
		// JSON読み取り
		IntroJSONModel model = null;
		try {
			ObjectMapper mapper   = new ObjectMapper();
			JsonNode rootJsonNode = mapper.readTree(path.toFile());
		
			/** ヘッダー */
			IntroList 		headerList			= this.readDataList(rootJsonNode, JSON_SECTION_HEADER);
			List<String> 	headerStringList	= headerList.getList();
			
			/** 名前 */
			IntroList nameList					= this.readDataList(rootJsonNode,  
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_NAME.ordinal()));
			/** タイトル */
			IntroList titleList					= this.readDataList(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_TITLE.ordinal()));
			/** 紹介文 */
			IntroWord intro						= this.readData(rootJsonNode,
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_INTRO.ordinal()));
			/** 経験 */
			ExperienceList experienceList  		= this.readExperienceList(rootJsonNode, headerList);
			/** 今後やりたいこと */
			AfterList afterList					= this.readAfterList(rootJsonNode, headerList);
			/** スキル(言語) */
			SkillList skillLanguageList			= this.readSkill(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LANGUAGE.ordinal()));
			/** スキル(ライブラリ) */
			SkillList skillLibraryList			= this.readSkill(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_LIBRARY.ordinal()));
			/** スキル(フレームワーク) */
			SkillList skillFrameworkList		= this.readSkill(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_FRAMEWORK.ordinal()));
			/** スキル(OS) */
			SkillList skillOSList				= this.readSkill(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_OS.ordinal()));
			/** スキル(Tool) */
			SkillList skillToolList				= this.readSkill(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_TOOL.ordinal()));
			/** スキル(その他) */
			SkillList skillOtherList			= this.readSkill(rootJsonNode, 
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_SKILL_OTHER.ordinal()));
			/** URL */
			IntroWord url						= this.readData(rootJsonNode,
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_URL.ordinal()));
			/** 趣味 */
			IntroList hobbyList					= this.readDataList(rootJsonNode,
													headerStringList.get(ATTRIBUTE_LIST_ENUM.A_HOBBY.ordinal()));
			/** 最後に */
			IntroWord word						= this.readData(rootJsonNode,
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

		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return model;
	}
	
	/**
	 * データの読み込み
	 * @param  rootJsonNode
	 * @param  word
	 * @return データ(文字列)
	 */
	private IntroWord readData(JsonNode rootJsonNode, String word) {
		return (rootJsonNode == null || word == null || word == "" ? 
					new IntroWord(null) :
					new IntroWord(rootJsonNode.get(word).get(0).get(JSON_SECTION_KEYWORD).asText())
				);
	}
	
	/**
	 * データリストの読み込み
	 * @param  rootJsonNode
	 * @param  word
	 * @return データリスト
	 */
	private IntroList readDataList(JsonNode rootJsonNode, String word) {
		if(rootJsonNode == null || word == null || word == "")	return new IntroList(null);
		
		List<String> dataList = new ArrayList<String>();
		for(JsonNode node : rootJsonNode.get(word)) {
			String text = node.get(JSON_SECTION_KEYWORD).asText();
			dataList.add(text);
		}
		
		return new IntroList(dataList);
	}
	
	/**
	 * データリストの読み込み + 長文へ変換
	 * @param  rootJsonNode
	 * @param  word
	 * @return データ
	 */
	private String readDataLongString(JsonNode rootJsonNode, String word) {
		if(rootJsonNode == null || word == null || word == "")	return "";
		
		String dataLong = "";
		for(JsonNode node : rootJsonNode.get(word)) {
			String text = node.get(JSON_SECTION_KEYWORD).asText();
			dataLong = dataLong + text;
		}
		
		return dataLong;
	}
	
	/**
	 * データリストの読み込み
	 * @param rootJsonNode
	 * @param introList		ヘッダーリスト
	 * @return 				経験リスト
	 */
	private ExperienceList readExperienceList(JsonNode rootJsonNode, IntroList headerList) {
		if(rootJsonNode == null || headerList == null || headerList.getList().isEmpty())	return new ExperienceList(null);
		
		ExperienceList 			list 				= new ExperienceList();
		List<ExperienceModel> 	experList 			= list.getList();
		List<String> 			headerStringList	= headerList.getList();
		String					experKeyword		= headerStringList.get(ATTRIBUTE_LIST_ENUM.A_EXPERIENCE.ordinal());
		
		for(JsonNode node : rootJsonNode.get(experKeyword)) {
			/** 経験    */
			String exper 				= node.get(JSON_SECTION_KEYWORD).asText();
			/** 開始日付 */
			String start 				= node.get(JSON_SECTION_START).asText();
			/** 終了日付 */
			String end 					= node.get(JSON_SECTION_END).asText();
			/** メンバー人数 */
			NormalNumber memberSum		= new NormalNumber(
											Integer.valueOf(node.get(JSON_SECTION_MEMBER_SUM).asText()));
			/** ポジション */
			String position				= node.get(JSON_SECTION_POSITION).asText();
			/** 概要 */
			String overview				= this.readDataLongString(node, JSON_SECTION_OVERVIEW);
			/** 担当業務 */
			IntroList personChargeList	= this.readDataList(node, JSON_SECTION_PERSON_CHARGE);
			/** 業務ポイント */
			String workPoint			= this.readDataLongString(node, JSON_SECTION_WORK_POINT);
			/** 技術リスト */
			IntroList techList			= this.readDataList(node, JSON_SECTION_TECH);
			
			// String型 → LocalDateTime型へ変換
			LocalDateTime 		startLocalDateTime 	= this.changeLocalDateTime(start);
			LocalDateTime 		endLocalDateTime 	= this.changeLocalDateTime(end);
			
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
		}
		
		return list;
	}
	
	/**
	 * 今後やりたいことデータの読み込み
	 * @param rootJsonNode
	 * @return データリスト
	 */
	private AfterList readAfterList(JsonNode rootJsonNode, IntroList headerList) {
		if(rootJsonNode == null)	return new AfterList(null);
		
		List<String> 			dataList 			= new ArrayList<String>();
		List<String> 			headerStringList	= headerList.getList();
		String					afterKeyword		= headerStringList.get(ATTRIBUTE_LIST_ENUM.A_AFTER.ordinal());
		for(JsonNode node : rootJsonNode.get(afterKeyword)) {
			String text = node.get(JSON_SECTION_KEYWORD).asText();
			dataList.add(text);
		}
		
		return new AfterList(dataList);
	}
	
	/**
	 * スキルデータの読み込み
	 * @param rootJsonNode
	 * @return スキルデータリスト
	 */
	private SkillList readSkill(JsonNode rootJsonNode, String keyword) {
		if(rootJsonNode == null || keyword == null)	return new SkillList(null);
		
		List<String> skillList = new ArrayList<String>();
		for(JsonNode node : rootJsonNode.get(keyword)) {
			String skill = node.get(JSON_SECTION_KEYWORD).asText();
			skillList.add(skill);
		}
		
		return new SkillList(skillList);
	}

	/**
	 * String型 → LocalDateTime型へ変換
	 * @param  timeString
	 * @return LocalDateTime型の時間
	 */
	private LocalDateTime changeLocalDateTime(String timeString) {
		DateTimeFormatter 	formatPatternA 	= DateTimeFormatter.ofPattern("uuuu/M/dd");
		DateTimeFormatter 	formatPatternB 	= DateTimeFormatter.ofPattern("uuuu/MM/dd");
		String 				dayFormatString = "/01";
		int 				timeLength 		= timeString.length();
		int 				lengthStandard  = 6;
		LocalTime			timeFormat		= LocalTime.of(0, 0);
		
		// nowは現在の日付を返す
		if(timeString.equals(JSON_VALUE_NOW)) {
			return LocalDateTime.now();
		}
		
		// uuuu/MM + /01
		timeString = timeString + dayFormatString;
		// uuuu/MM/01 or uuuu/M/01
		LocalDate localDate = LocalDate.parse(timeString, 
					(timeLength == lengthStandard ? 
					formatPatternA : formatPatternB));
		// uuuu/MM/01 mm:ss
		LocalDateTime localDateTime = LocalDateTime.of(localDate, timeFormat);
		
		return localDateTime;
	}
}
