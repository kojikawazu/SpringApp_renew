package com.example.demo.json.model;

import com.example.demo.common.list.WordList;

/**
 * attributeキーモデルクラス
 * @author nanai
 *
 */
public class AttributeKeyModel {

	/** タイトルキーリスト */
	private WordList	titleKeyList;

	/** bodyキーリスト */
	private WordList	bodyKeyList;

	/**
	 * コンストラクタ
	 */
	public AttributeKeyModel() {
		this.titleKeyList = new WordList();
		this.bodyKeyList  = new WordList();
	}

	/**
	 * コンストラクタ
	 * @param titleKeyList 	{@link WordList}
	 * @param bodyKeyList 	{@link WordList}
	 */
	public AttributeKeyModel(
			WordList	titleKeyList,
			WordList	bodyKeyList) {
		this();
		this.setTitleKeyList(titleKeyList);
		this.setBodyKeyList(bodyKeyList);
	}

	/**
	 * getter
	 * @return {@link WordList}
	 */
	public WordList getTitleKeyList() {
		return this.titleKeyList;
	}

	/**
	 * setter
	 * @param titleKeyList {@link WordList}
	 */
	public void setTitleKeyList(WordList titleKeyList) {
		if (titleKeyList == null)	return;
		this.titleKeyList.setList(titleKeyList);
	}

	/**
	 * getter
	 * @return {@link WordList}
	 */
	public WordList getBodyKeyList() {
		return this.bodyKeyList;
	}

	/**
	 * setter
	 * @param bodyKeyList {@link WordList}
	 */
	public void setBodyKeyList(WordList bodyKeyList) {
		if (bodyKeyList == null)	return;
		this.bodyKeyList.setList(bodyKeyList);
	}

	/**
	 * setter
	 * @param model {@link AttributeKeyModel}
	 */
	public void setAttributeKeyModel(AttributeKeyModel model) {
		this.setTitleKeyList(model.getTitleKeyList());
		this.setBodyKeyList(model.getBodyKeyList());
	}
}
