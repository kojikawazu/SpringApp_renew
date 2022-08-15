package com.example.demo.common.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.list.IntroList;
import com.example.demo.common.list.SkillList;
import com.example.demo.common.word.IntroWord;

public class IntroJSONModel {
	
	/** 自己紹介文字列 */
	private IntroWord intro;
	
	/** 経験リスト */
	private IntroList experienceList;
	
	/** 今後の計画文字列 */
	private IntroWord after;
	
	/** スキル1リスト */
	private SkillList skill1List;
	/** スキル2リスト */
	private SkillList skill2List;
	/** スキル3リスト */
	private SkillList skill3List;
	/** スキル4リスト */
	private SkillList skill4List;
	
	/** URL文字列 */
	private IntroWord url;
	
	/** 趣味リスト */
	private IntroList hobbyList;
	
	/** 意気込み文字列 */
	private IntroWord word;

	/**
	 * コンストラクタ
	 * @param intro
	 * @param experienceList
	 * @param after
	 * @param skill1List
	 * @param skill2List
	 * @param skill3List
	 * @param skill4List
	 * @param url
	 * @param hobbyList
	 * @param word
	 */
	public IntroJSONModel(
			IntroWord intro,
			IntroList experienceList,
			IntroWord after,
			SkillList skill1List,
			SkillList skill2List,
			SkillList skill3List,
			SkillList skill4List,
			IntroWord url,
			IntroList hobbyList,
			IntroWord word
			) {
		
		this.intro = (intro == null ?
				new IntroWord("") :
				intro);
		
		this.experienceList = (experienceList == null ?
				new IntroList(new ArrayList<>()) :
				experienceList);
		
		this.after = (after == null ?
				new IntroWord("") :
				after);
		
		this.skill1List = (skill1List == null ?
				new SkillList(new ArrayList<>()) :
				skill1List);
		
		this.skill2List = (skill2List == null ?
				new SkillList(new ArrayList<>()) :
				skill2List);
		
		this.skill3List = (skill3List == null ?
				new SkillList(new ArrayList<>()) :
				skill3List);
		
		this.skill4List = (skill4List == null ?
				new SkillList(new ArrayList<>()) :
				skill4List);
		
		this.url = (url == null ?
				new IntroWord("") :
				url);
		
		this.hobbyList = (hobbyList == null ?
				new IntroList(new ArrayList<>()) :
					hobbyList);
		
		this.word = (word == null ?
				new IntroWord("") :
				word);
	}
	
	/**
	 * コンストラクタ
	 * @param model
	 */
	public IntroJSONModel(
			IntroJSONModel model
			) {
		if(model == null) {
			this.intro          = new IntroWord("");
			this.experienceList = new IntroList(new ArrayList<>());
			this.after          = new IntroWord("");
			this.skill1List     = new SkillList(new ArrayList<>());
			this.skill2List     = new SkillList(new ArrayList<>());
			this.skill3List     = new SkillList(new ArrayList<>());
			this.skill4List     = new SkillList(new ArrayList<>());
			this.url            = new IntroWord("");
			this.hobbyList      = new IntroList(new ArrayList<>());
			this.word           = new IntroWord("");
			
		} else {
			this.intro          = new IntroWord(model.getIntro());
			this.experienceList = new IntroList(model.getExperienceList());
			this.after          = new IntroWord(model.getAfter());
			this.skill1List     = new SkillList(model.getSkill1List());
			this.skill2List     = new SkillList(model.getSkill2List());
			this.skill3List     = new SkillList(model.getSkill3List());
			this.skill4List     = new SkillList(model.getSkill4List());
			this.url            = new IntroWord(model.getUrl());
			this.hobbyList      = new IntroList(model.getHobbyList());
			this.word           = new IntroWord(model.getWord());
		}
	}

	public String getIntro() {
		return this.intro.getWord();
	}

	protected void setIntro(String intro) {
		if(intro == null)	return ;
		this.intro = new IntroWord(intro);
	}

	public List<String> getExperienceList() {
		return this.experienceList.getList();
	}

	protected void setExperienceList(List<String> experienceList) {
		if(experienceList == null) return ;
		this.experienceList = new IntroList(experienceList);
	}

	public String getAfter() {
		return this.after.getWord();
	}

	protected void setAfter(String after) {
		if(after == null)	return ;
		this.after = new IntroWord(after);
	}

	public List<String> getSkill1List() {
		return this.skill1List.getList();
	}

	protected void setSkill1List(List<String> skill1List) {
		if(skill1List == null)	return ;
		this.skill1List = new SkillList(skill1List);
	}

	public List<String> getSkill2List() {
		return this.skill2List.getList();
	}

	protected void setSkill2List(List<String> skill2List) {
		if(skill2List == null)	return ;
		this.skill2List = new SkillList(skill2List);
	}

	public List<String> getSkill3List() {
		return this.skill3List.getList();
	}

	protected void setSkill3List(List<String> skill3List) {
		if(skill3List == null)	return ;
		this.skill3List = new SkillList(skill3List);
	}

	public List<String> getSkill4List() {
		return this.skill4List.getList();
	}

	protected void setSkill4List(List<String> skill4List) {
		if(skill4List == null)	return ;
		this.skill4List = new SkillList(skill4List);
	}

	public String getUrl() {
		return this.url.getWord();
	}

	protected void setUrl(String url) {
		if(url == null)	return ;
		this.url = new IntroWord(url);
	}

	public List<String> getHobbyList() {
		return this.hobbyList.getList();
	}

	protected void setHobbyList(List<String> hobbyList) {
		if(hobbyList == null)	return ;
		this.hobbyList = new IntroList(hobbyList);
	}

	public String getWord() {
		return this.word.getWord();
	}

	protected void setWord(String word) {
		if(word == null)	return ;
		this.word = new IntroWord(word);
	}
}
