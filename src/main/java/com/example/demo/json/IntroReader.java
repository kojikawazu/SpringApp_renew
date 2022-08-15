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
	
	@Autowired
	public IntroReader() {
		// コンストラクタ
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
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootJsonNode = mapper.readTree(path.toFile());
			
			// 紹介文
			IntroWord intro          = this.readData(rootJsonNode, "intro");
			// 経験
			IntroList experienceList = this.readDataList(rootJsonNode, "experience");
			// 今後やりたいこと
			IntroWord after          = this.readData(rootJsonNode, "after");
			// スキル1
			SkillList skill1List     = this.readSkill(rootJsonNode, 1);
			// スキル2
			SkillList skill2List     = this.readSkill(rootJsonNode, 2);
			// スキル3
			SkillList skill3List     = this.readSkill(rootJsonNode, 3);
			// スキル4
			SkillList skill4List     = this.readSkill(rootJsonNode, 4);
			// URL
			IntroWord url            = this.readData(rootJsonNode, "url");
			// 趣味
			IntroList hobbyList      = this.readDataList(rootJsonNode, "hobby");
			// 最後に
			IntroWord word           = this.readData(rootJsonNode, "word");
			
			model = new IntroJSONModel(
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
					new IntroWord(rootJsonNode.get("intro").get(0).get("name").asText())
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
			String text = node.get("name").asText();
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
		for(JsonNode node : rootJsonNode.get("skill_" + dataNumber)) {
			String skill = node.get("name").asText();
			skillList.add(skill);
		}
		
		return new SkillList(skillList);
	}

}
