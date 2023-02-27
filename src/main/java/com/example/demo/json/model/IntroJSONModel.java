package com.example.demo.json.model;

import com.example.demo.common.list.WordList;
import com.example.demo.common.word.NormalWord;
import com.example.demo.json.list.ExperienceList;

/**
 * JSON専用モデル
 * @author nanai
 *
 */
public class IntroJSONModel {

	/** 名前リスト */
	private WordList 			nameList;

	/** タイトルリスト */
	private WordList 			titleList;

	/** 自己紹介文字列 */
	private NormalWord 			intro;

	/** 経験リスト */
	private ExperienceList 		experienceList;

	/** 今後の計画文字列 */
	private WordList 			afterList;

	/** スキル(言語)リスト */
	private WordList 			skillLanguageList;
	/** スキル(ライブラリ)リスト */
	private WordList 			skillLibraryList;
	/** スキル(フレームワーク)リスト */
	private WordList 			skillFrameworkList;
	/** スキル(OS)リスト */
	private WordList 			skillOSList;
	/** スキル(Tool)リスト */
	private WordList 			skillToolList;
	/** スキル(その他)リスト */
	private WordList 			skillOtherList;

	/** URL文字列 */
	private NormalWord			url;

	/** 趣味リスト */
	private WordList 			hobbyList;

	/** 意気込み文字列 */
	private NormalWord 			word;

	/**
	 * コンストラクタ
	 */
	public IntroJSONModel() {
		this.nameList			= new WordList();
		this.titleList			= new WordList();
		this.intro				= new NormalWord();
		this.experienceList		= new ExperienceList();
		this.afterList			= new WordList();
		this.skillLanguageList	= new WordList();
		this.skillLibraryList	= new WordList();
		this.skillFrameworkList	= new WordList();
		this.skillOSList		= new WordList();
		this.skillToolList		= new WordList();
		this.skillOtherList		= new WordList();
		this.url				= new NormalWord();
		this.hobbyList			= new WordList();
		this.word				= new NormalWord();
	}
	
	/**
	 * コンストラクタ
	 * @param nameList				{@link WordList}		名前リスト
	 * @param titleList				{@link WordList}		タイトルリスト
	 * @param intro					{@link NormalWord}		自己紹介文字列
	 * @param experienceList		{@link ExperienceList}	経験リスト
	 * @param after					{@link WordList}		今後の計画文字列
	 * @param skillLanguageList		{@link WordList}		スキル(言語)リスト
	 * @param skillLibraryList		{@link WordList}		スキル(ライブラリ)リスト
	 * @param skillFrameworkList	{@link WordList}		スキル(フレームワーク)リスト
	 * @param skillOSList			{@link WordList}		スキル(OS)リスト
	 * @param skillToolList			{@link WordList}		スキル(Tool)リスト
	 * @param skillOtherList		{@link WordList}		スキル(その他)リスト
	 * @param url					{@link NormalWord}		URL文字列
	 * @param hobbyList				{@link WordList}		趣味リスト
	 * @param word					{@link NormalWord}		意気込み文字列
	 */
	public IntroJSONModel(
			WordList 		nameList,
			WordList 		titleList,
			NormalWord 		intro,
			ExperienceList 	experienceList,
			WordList 		afterList,
			WordList 		skillLanguageList,
			WordList 		skillLibraryList,
			WordList 		skillFrameworkList,
			WordList		skillOSList,
			WordList		skillToolList,
			WordList 		skillOtherList,
			NormalWord 		url,
			WordList 		hobbyList,
			NormalWord 		word
			) {
		this();
		this.setNameList(nameList);
		this.setTitleList(titleList);
		this.setIntro(intro);
		this.setExperienceList(experienceList);
		this.setAfterList(afterList);
		this.setSkillLanguageList(skillLanguageList);
		this.setSkillLibraryList(skillLibraryList);
		this.setSkillFrameworkList(skillFrameworkList);
		this.setSkillOSList(skillOSList);
		this.setSkillToolList(skillToolList);
		this.setSkillOtherList(skillOtherList);
		this.setUrl(url);
		this.setHobbyList(hobbyList);
		this.setWord(word);
	}

	/**
	 * コンストラクタ
	 * @param model {@link IntroJSONModel}
	 */
	public IntroJSONModel(
			IntroJSONModel model
			) {
		this();
		if(model != null) {
			this.nameList.setList(model.getNameList());
			this.titleList.setList(model.getTitleList());
			this.intro.setWord(model.getIntro());
			this.experienceList.setList(model.getExperienceList());
			this.afterList.setList(model.getAfterList());
			this.skillLanguageList.setList(model.getSkillLanguageList());
			this.skillLibraryList.setList(model.getSkillLibraryList());
			this.skillFrameworkList.setList(model.getSkillFrameworkList());
			this.skillOSList.setList(model.getSkillOSList());
			this.skillOtherList.setList(model.getSkillOtherList());
			this.url.setWord(model.getUrl());
			this.hobbyList.setList(model.getHobbyList());
			this.word.setWord(model.getWord());
		}
	}

	/** 
	 * 名前リスト取得
	 * @return {@link WordList}
	 */
	public WordList getNameList() {
		return this.nameList;
	}

	/**
	 * 名前リスト設定
	 * @param nameList {@link WordList}
	 */
	public void setNameList(WordList nameList) {
		if(nameList == null)	return ;
		this.nameList.setList(nameList);
	}

	/** 
	 * タイトルリスト取得
	 * @return {@link WordList}
	 */
	public WordList getTitleList() {
		return this.titleList;
	}

	/**
	 * タイトルリスト設定
	 * @param titleList {@link WordList}
	 */
	public void setTitleList(WordList titleList) {
		if(titleList == null)	return ;
		this.titleList.setList(titleList);
	}

	/** 
	 * 自己紹介取得
	 * @return {@link NormalWord}
	 */
	public NormalWord getIntro() {
		return this.intro;
	}

	/**
	 * 自己紹介設定
	 * @param intro {@link NormalWord}
	 */
	public void setIntro(NormalWord intro) {
		if(intro == null)	return ;
		this.intro.setWord(intro);
	}

	/** 
	 * 経験リスト取得
	 * @return {@link ExperienceList}
	 */
	public ExperienceList getExperienceList() {
		return this.experienceList;
	}

	/**
	 * 経験リスト設定
	 * @param experienceList {@link ExperienceList}
	 */
	public void setExperienceList(ExperienceList experienceList) {
		if(experienceList == null) return ;
		this.experienceList.setList(experienceList);
	}

	/** 
	 * 今後やりたいことリスト取得
	 * @return {@link WordList}
	 */
	public WordList getAfterList() {
		return this.afterList;
	}

	/**
	 * 今後やりたいことリスト設定
	 * @param afterList {@link WordList}
	 */
	public void setAfterList(WordList afterList) {
		if(afterList == null)	return ;
		this.afterList.setList(afterList);
	}

	/** 
	 * スキル(言語)取得
	 * @return {@link WordList}
	 */
	public WordList getSkillLanguageList() {
		return this.skillLanguageList;
	}

	/**
	 * スキル(言語)設定
	 * @param skillLanguageList {@link WordList}
	 */
	public void setSkillLanguageList(WordList skillLanguageList) {
		if(skillLanguageList == null)	return ;
		this.skillLanguageList.setList(skillLanguageList);
	}

	/** 
	 * スキル(ライブラリ)取得
	 * @return {@link WordList}
	 */
	public WordList getSkillLibraryList() {
		return this.skillLibraryList;
	}

	/**
	 * スキル(ライブラリ)設定
	 * @param skillLibraryList {@link WordList}
	 */
	public void setSkillLibraryList(WordList skillLibraryList) {
		if(skillLibraryList == null)	return ;
		this.skillLibraryList.setList(skillLibraryList);
	}

	/** 
	 * スキル(フレームワーク)取得
	 * @return {@link WordList}
	 */
	public WordList getSkillFrameworkList() {
		return this.skillFrameworkList;
	}

	/**
	 * スキル(フレームワーク)設定
	 * @param skillFrameworkList {@link WordList}
	 */
	public void setSkillFrameworkList(WordList skillFrameworkList) {
		if(skillFrameworkList == null)	return ;
		this.skillFrameworkList.setList(skillFrameworkList);
	}

	/** 
	 * スキル(OS)取得
	 * @return {@link WordList}
	 */
	public WordList getSkillOSList() {
		return this.skillOSList;
	}

	/**
	 * スキル(OS)設定
	 * @param skillOSList {@link WordList}
	 */
	public void setSkillOSList(WordList skillOSList) {
		if(skillOSList == null)	return ;
		this.skillOSList.setList(skillOSList);
	}

	/** 
	 * スキル(Tool)取得
	 * @return {@link WordList}
	 */
	public WordList getSkillToolList() {
		return this.skillToolList;
	}

	/**
	 * スキル(Tool)設定
	 * @param skillToolList {@link WordList}
	 */
	public void setSkillToolList(WordList skillToolList) {
		if(skillToolList == null)	return ;
		this.skillToolList.setList(skillToolList);
	}

	/** 
	 * スキル(その他)取得
	 * @return {@link WordList}
	 */
	public WordList getSkillOtherList() {
		return this.skillOtherList;
	}

	/**
	 * スキル(その他)設定
	 * @param skillOtherList {@link WordList}
	 */
	public void setSkillOtherList(WordList skillOtherList) {
		if(skillOtherList == null)	return ;
		this.skillOtherList.setList(skillOtherList);
	}

	/** 
	 * URL取得
	 * @return {@link NormalWord}
	 */
	public NormalWord getUrl() {
		return this.url;
	}

	/**
	 * URL設定
	 * @param url {@link NormalWord}
	 */
	public void setUrl(NormalWord url) {
		if(url == null)	return ;
		this.url.setWord(url);
	}

	/** 
	 * 趣味リスト取得
	 * @return {@link WordList}
	 */
	public WordList getHobbyList() {
		return this.hobbyList;
	}

	/**
	 * 趣味リスト設定
	 * @param hobbyList {@link WordList}
	 */
	public void setHobbyList(WordList hobbyList) {
		if(hobbyList == null)	return ;
		this.hobbyList.setList(hobbyList);
	}

	/** 
	 * 最後に一言取得
	 * @return {@link NormalWord}
	 */
	public NormalWord getWord() {
		return this.word;
	}

	/**
	 * 最後に一言設定
	 * @param word {@link String}
	 */
	public void setWord(NormalWord word) {
		if(word == null)	return ;
		this.word.setWord(word);
	}
}
