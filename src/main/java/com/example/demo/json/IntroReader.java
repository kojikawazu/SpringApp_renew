package com.example.demo.json;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.entity.IntroJSONModel;
import com.example.demo.common.list.IntroList;
import com.example.demo.common.list.SkillList;
import com.example.demo.common.word.IntroWord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json読み込みクラス
 * @author nanai
 *
 */
@Repository
public class IntroReader implements IntroJsonReader {
	
	
	private static final String JSON_SECTION_KEYWORD    = "name";
	
	private static final String JSON_SECTION_SKILL      = "skill_";
	
	/** 名前 */
	private static final String JSON_SECTION_NAME       = "name";
	
	/** タイトル */
	private static final String JSON_SECTION_TITLE      = "title";
	
	/** 自己紹介 */
	private static final String JSON_SECTION_INTRO      = "intro";
	
	/** 経験 */
	private static final String JSON_SECTION_EXPERIENCE = "experience";
	
	/** 今後やりたいこと */
	private static final String JSON_SECTION_AFTER      = "after";
	
	/** URL */
	private static final String JSON_SECTION_URL        = "url";
	
	/** 趣味 */
	private static final String JSON_SECTION_HOBBY      = "hobby";

	/** 最後に */
	private static final String JSON_SECTION_WORD       = "word";
	
	/**
	 * コンストラクタ
	 */
	@Autowired
	public IntroReader() {
		
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
			int skill_number      = 1;
			
			/** 名前 */
			IntroList nameList        = this.readDataList(rootJsonNode,  JSON_SECTION_NAME);
			// タイトル
			IntroList titleList       = this.readDataList(rootJsonNode, JSON_SECTION_TITLE);
			// 紹介文
			IntroWord intro           = this.readData(rootJsonNode,     JSON_SECTION_INTRO);
			// 経験
			IntroList experienceList  = this.readDataList(rootJsonNode, JSON_SECTION_EXPERIENCE);
			// 今後やりたいこと
			IntroWord after           = this.readData(rootJsonNode,     JSON_SECTION_AFTER);
			// スキル1
			SkillList skill1List      = this.readSkill(rootJsonNode, skill_number);
			// スキル2
			SkillList skill2List      = this.readSkill(rootJsonNode, skill_number + 1);
			// スキル3
			SkillList skill3List      = this.readSkill(rootJsonNode, skill_number + 2);
			// スキル4
			SkillList skill4List      = this.readSkill(rootJsonNode, skill_number + 3);
			// URL
			IntroWord url             = this.readData(rootJsonNode,     JSON_SECTION_URL);
			// 趣味
			IntroList hobbyList       = this.readDataList(rootJsonNode, JSON_SECTION_HOBBY);
			// 最後に
			IntroWord word            = this.readData(rootJsonNode,     JSON_SECTION_WORD);
			
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
					after,
					// スキル1
					skill1List,
					// スキル2
					skill2List,
					// スキル3
					skill3List,
					// スキル4
					skill4List,
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
	 * @param rootJsonNode
	 * @param word
	 * @return データ(文字列)
	 */
	private IntroWord readData(JsonNode rootJsonNode, String word) {
		return (word == null || word == "" ? 
					new IntroWord(null) :
					new IntroWord(rootJsonNode.get(word).get(0).get(JSON_SECTION_KEYWORD).asText())
				);
	}
	
	/**
	 * データリストの読み込み
	 * @param rootJsonNode
	 * @return データリスト
	 */
	private IntroList readDataList(JsonNode rootJsonNode, String word) {
		if(word == null || word == "")	return new IntroList(null);
		
		List<String> dataList = new ArrayList<String>();
		for(JsonNode node : rootJsonNode.get(word)) {
			String text = node.get(JSON_SECTION_KEYWORD).asText();
			dataList.add(text);
		}
		
		return new IntroList(dataList);
	}
	
	/**
	 * スキルデータの読み込み
	 * @param rootJsonNode
	 * @return スキルデータリスト
	 */
	private SkillList readSkill(JsonNode rootJsonNode, int dataNumber) {
		if (dataNumber <= 0 || dataNumber > 4) return new SkillList(null);
		
		List<String> skillList = new ArrayList<String>();
		for(JsonNode node : rootJsonNode.get(JSON_SECTION_SKILL + dataNumber)) {
			String skill = node.get(JSON_SECTION_KEYWORD).asText();
			skillList.add(skill);
		}
		
		return new SkillList(skillList);
	}

}
