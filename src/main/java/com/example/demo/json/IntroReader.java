package com.example.demo.json;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.app.intro.IntroJSONModel;
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
			model = new IntroJSONModel();
			
			// 紹介文
			model.setIntro(rootJsonNode.get("intro").get(0).get("text").asText());
			
			// 経験
			List<String> exList = new ArrayList<String>();
			for(JsonNode node : rootJsonNode.get("experience")) {
				String text = node.get("text").asText();
				exList.add(text);
			}
			model.setExperienceList(exList);
			
			// 今後やりたいこと
			model.setAfter(rootJsonNode.get("after").get(0).get("text").asText());
			
			// スキル1
			List<String> skill1List = new ArrayList<String>();
			for(JsonNode node : rootJsonNode.get("skill_1")) {
				String skill1 = node.get("name").asText();
				skill1List.add(skill1);
			}
			model.setSkill1List(skill1List);
			
			// スキル2
			List<String> skill2List = new ArrayList<String>();
			for(JsonNode node : rootJsonNode.get("skill_2")) {
				String skill2 = node.get("name").asText();
				skill2List.add(skill2);
			}
			model.setSkill2List(skill2List);
			
			// スキル3
			List<String> skill3List = new ArrayList<String>();
			for(JsonNode node : rootJsonNode.get("skill_3")) {
				String skill3 = node.get("name").asText();
				skill3List.add(skill3);
			}
			model.setSkill3List(skill3List);
			
			// スキル4
			List<String> skill4List = new ArrayList<String>();
			for(JsonNode node : rootJsonNode.get("skill_4")) {
				String skill4 = node.get("name").asText();
				skill4List.add(skill4);
			}
			model.setSkill4List(skill4List);
			
			// URL
			model.setUrl(rootJsonNode.get("url").get(0).get("text").asText());
			
			// 趣味
			List<String> hobbyList = new ArrayList<String>();
			for(JsonNode node : rootJsonNode.get("hobby")) {
				String hobby = node.get("name").asText();
				hobbyList.add(hobby);
			}
			model.setHobbyList(hobbyList);
			
			// 最後に
			model.setWord(rootJsonNode.get("word").get(0).get("text").asText());
						
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return model;
	}

}
