package com.example.demo.json.model;

import com.example.demo.common.list.WordList;

/**
 * 経験タイトルモデル
 * @author nanai
 *
 */
public class ExperienceTitleModel {

	/** 経験タイトルキーリスト */
	private WordList  experienceTitleKeyList;

	/**　経験タイトルボディリスト */
	private WordList  experienceTitleBodyList;

	/**
	 * コンストラクタ
	 */
	public ExperienceTitleModel() {
		this.experienceTitleKeyList = new WordList();
		this.experienceTitleBodyList = new WordList();
	}

	/**
	 * コンストラクタ
	 * @param experienceTitleKeyList	{@link WordList}
	 * @param experienceTitleBodyList	{@link WordList}
	 */
	public ExperienceTitleModel(
			WordList  experienceTitleKeyList,
			WordList  experienceTitleBodyList) {
		this();
		this.setExperienceTitleKeyList(experienceTitleKeyList);
		this.setExperienceTitleBodyList(experienceTitleBodyList);
	}

	/**
	 * getter
	 * @return {@link WordList}
	 */
	public WordList getExperienceTitleKeyList() {
		return this.experienceTitleKeyList;
	}

	/**
	 * setter
	 * @param experienceTitleKeyList {@link WordList}
	 */
	public void setExperienceTitleKeyList(WordList experienceTitleKeyList) {
		if (experienceTitleKeyList == null)	return ;
		this.experienceTitleKeyList.setList(experienceTitleKeyList);
	}

	/**
	 * getter
	 * @return {@link WordList}
	 */
	public WordList getExperienceTitleBodyList() {
		return this.experienceTitleBodyList;
	}

	/**
	 * setter
	 * @param experienceTitleBodyList {@link WordList}
	 */
	public void setExperienceTitleBodyList(WordList experienceTitleBodyList) {
		if (experienceTitleBodyList == null) return ;
		this.experienceTitleBodyList.setList(experienceTitleBodyList);
	}

	public void setExperienceTitleModel(ExperienceTitleModel model) {
		if (model == null)	return ;
		this.experienceTitleKeyList.setList(model.getExperienceTitleKeyList());
		this.experienceTitleBodyList.setList(model.getExperienceTitleBodyList());
	}
}
