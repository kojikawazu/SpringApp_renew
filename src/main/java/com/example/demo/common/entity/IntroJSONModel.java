package com.example.demo.common.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.list.AfterList;
import com.example.demo.common.list.ExperienceList;
import com.example.demo.common.list.IntroList;
import com.example.demo.common.list.SkillList;
import com.example.demo.common.word.IntroWord;

/**
 * JSON専用モデル
 * @author nanai
 *
 */
public class IntroJSONModel {
	
	/** 名前リスト */
	private IntroList 			nameList;
	
	/** タイトルリスト */
	private IntroList 			titleList;
	
	/** 自己紹介文字列 */
	private IntroWord 			intro;
	
	/** 経験リスト */
	private ExperienceList 		experienceList;
	
	/** 今後の計画文字列 */
	private AfterList 			afterList;
	
	/** スキル(言語)リスト */
	private SkillList 			skillLanguageList;
	/** スキル(ライブラリ)リスト */
	private SkillList 			skillLibraryList;
	/** スキル(フレームワーク)リスト */
	private SkillList 			skillFrameworkList;
	/** スキル(OS)リスト */
	private SkillList 			skillOSList;
	/** スキル(Tool)リスト */
	private SkillList 			skillToolList;
	/** スキル(その他)リスト */
	private SkillList 			skillOtherList;
	
	/** URL文字列 */
	private IntroWord			url;
	
	/** 趣味リスト */
	private IntroList 			hobbyList;
	
	/** 意気込み文字列 */
	private IntroWord 			word;

	/**
	 * コンストラクタ
	 * @param nameList
	 * @param titleList
	 * @param intro
	 * @param experienceList
	 * @param after
	 * @param skillLanguageList
	 * @param skillLibraryList
	 * @param skillFrameworkList
	 * @param skillOSList
	 * @param skillToolList
	 * @param skillOtherList
	 * @param url
	 * @param hobbyList
	 * @param word
	 */
	public IntroJSONModel(
			IntroList 		nameList,
			IntroList 		titleList,
			IntroWord 		intro,
			ExperienceList 	experienceList,
			AfterList 		afterList,
			SkillList 		skillLanguageList,
			SkillList 		skillLibraryList,
			SkillList 		skillFrameworkList,
			SkillList		skillOSList,
			SkillList		skillToolList,
			SkillList 		skillOtherList,
			IntroWord 		url,
			IntroList 		hobbyList,
			IntroWord 		word
			) {
		
		this.nameList = (nameList == null ?
				new IntroList(new ArrayList<>()) :
					nameList);
		
		this.titleList = (titleList == null ?
				new IntroList(new ArrayList<>()) :
					titleList);
		
		this.intro = (intro == null ?
				new IntroWord("") :
				intro);
		
		this.experienceList = (experienceList == null ?
				new ExperienceList() :
				experienceList);
		
		this.afterList = (afterList == null ?
				new AfterList() :
				afterList);
		
		this.skillLanguageList = (skillLanguageList == null ?
				new SkillList(new ArrayList<>()) :
				skillLanguageList);
		
		this.skillLibraryList = (skillLibraryList == null ?
				new SkillList(new ArrayList<>()) :
				skillLibraryList);
		
		this.skillFrameworkList = (skillFrameworkList == null ?
				new SkillList(new ArrayList<>()) :
				skillFrameworkList);
		
		this.skillOSList = (skillOSList == null ?
				new SkillList(new ArrayList<>()) :
					skillOSList);
		
		this.skillToolList = (skillToolList == null ?
				new SkillList(new ArrayList<>()) :
					skillToolList);
		
		this.skillOtherList = (skillOtherList == null ?
				new SkillList(new ArrayList<>()) :
				skillOtherList);
		
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
			this.nameList			= new IntroList(new ArrayList<>());
			this.titleList			= new IntroList(new ArrayList<>());
			this.intro				= new IntroWord("");
			this.experienceList		= new ExperienceList();
			this.afterList			= new AfterList();
			this.skillLanguageList	= new SkillList(new ArrayList<>());
			this.skillLibraryList	= new SkillList(new ArrayList<>());
			this.skillFrameworkList	= new SkillList(new ArrayList<>());
			this.skillOSList		= new SkillList(new ArrayList<>());
			this.skillOtherList		= new SkillList(new ArrayList<>());
			this.url				= new IntroWord("");
			this.hobbyList			= new IntroList(new ArrayList<>());
			this.word				= new IntroWord("");
			
		} else {
			this.nameList			= new IntroList(model.getNameList());
			this.titleList			= new IntroList(model.getTitleList());
			this.intro				= new IntroWord(model.getIntro());
			this.experienceList		= new ExperienceList(model.getExperienceList());
			this.afterList			= new AfterList(model.getAfter());
			this.skillLanguageList	= new SkillList(model.getSkillLanguageList());
			this.skillLibraryList	= new SkillList(model.getSkillLibraryList());
			this.skillFrameworkList	= new SkillList(model.getSkillFrameworkList());
			this.skillOSList		= new SkillList(model.getSkillOSList());
			this.skillOtherList		= new SkillList(model.getSkillOtherList());
			this.url				= new IntroWord(model.getUrl());
			this.hobbyList			= new IntroList(model.getHobbyList());
			this.word				= new IntroWord(model.getWord());
		}
	}
	
	/** 
	 * 名前リスト取得
	 * @return List<String>  
	 */
	public List<String> getNameList() {
		return this.nameList.getList();
	}

	protected void setNameList(List<String> nameList) {
		if(nameList == null)	return ;
		this.nameList = new IntroList(nameList);
	}
	
	/** 
	 * タイトルリスト取得
	 * @return List<String>  
	 */
	public List<String> getTitleList() {
		return this.titleList.getList();
	}

	protected void setTitleList(List<String> titleList) {
		if(titleList == null)	return ;
		this.titleList = new IntroList(titleList);
	}

	/** 
	 * 自己紹介取得
	 * @return String  
	 */
	public String getIntro() {
		return this.intro.getWord();
	}

	protected void setIntro(String intro) {
		if(intro == null)	return ;
		this.intro = new IntroWord(intro);
	}

	/** 
	 * 経験リスト取得
	 * @return List<ExperienceModel>  
	 */
	public List<ExperienceModel> getExperienceList() {
		return this.experienceList.getList();
	}

	protected void setExperienceList(List<ExperienceModel> experienceList) {
		if(experienceList == null) return ;
		this.experienceList = new ExperienceList(experienceList);
	}

	/** 
	 * 今後やりたいことリスト取得
	 * @return List<String>  
	 */
	public List<String> getAfter() {
		return this.afterList.getList();
	}

	protected void setAfterList(List<String> afterList) {
		if(afterList == null)	return ;
		this.afterList = new AfterList(afterList);
	}

	/** 
	 * スキル(言語)取得
	 * @return List<String>  
	 */
	public List<String> getSkillLanguageList() {
		return this.skillLanguageList.getList();
	}

	protected void setSkillLanguageList(List<String> skillLanguageList) {
		if(skillLanguageList == null)	return ;
		this.skillLanguageList = new SkillList(skillLanguageList);
	}

	/** 
	 * スキル(ライブラリ)取得
	 * @return List<String>  
	 */
	public List<String> getSkillLibraryList() {
		return this.skillLibraryList.getList();
	}

	protected void setSkillLibraryList(List<String> skillLibraryList) {
		if(skillLibraryList == null)	return ;
		this.skillLibraryList = new SkillList(skillLibraryList);
	}

	/** 
	 * スキル(フレームワーク)取得
	 * @return List<String>  
	 */
	public List<String> getSkillFrameworkList() {
		return this.skillFrameworkList.getList();
	}

	protected void setSkill3List(List<String> skillFrameworkList) {
		if(skillFrameworkList == null)	return ;
		this.skillFrameworkList = new SkillList(skillFrameworkList);
	}

	/** 
	 * スキル(OS)取得
	 * @return List<String>  
	 */
	public List<String> getSkillOSList() {
		return this.skillOSList.getList();
	}

	protected void setSkillOSList(List<String> skillOSList) {
		if(skillOSList == null)	return ;
		this.skillOSList = new SkillList(skillOSList);
	}

	/** 
	 * スキル(Tool)取得
	 * @return List<String>  
	 */
	public List<String> getSkillToolList() {
		return this.skillToolList.getList();
	}

	protected void setSkillToolList(List<String> skillToolList) {
		if(skillToolList == null)	return ;
		this.skillToolList = new SkillList(skillToolList);
	}

	/** 
	 * スキル(その他)取得
	 * @return List<String>  
	 */
	public List<String> getSkillOtherList() {
		return this.skillOtherList.getList();
	}

	protected void setSkillOtherList(List<String> skillOtherList) {
		if(skillOtherList == null)	return ;
		this.skillOtherList = new SkillList(skillOtherList);
	}

	/** 
	 * URL取得
	 * @return String  
	 */
	public String getUrl() {
		return this.url.getWord();
	}

	protected void setUrl(String url) {
		if(url == null)	return ;
		this.url = new IntroWord(url);
	}

	/** 
	 * 趣味リスト取得
	 * @return List<String>  
	 */
	public List<String> getHobbyList() {
		return this.hobbyList.getList();
	}

	protected void setHobbyList(List<String> hobbyList) {
		if(hobbyList == null)	return ;
		this.hobbyList = new IntroList(hobbyList);
	}

	/** 
	 * 最後に一言取得
	 * @return String  
	 */
	public String getWord() {
		return this.word.getWord();
	}

	protected void setWord(String word) {
		if(word == null)	return ;
		this.word = new IntroWord(word);
	}
}
