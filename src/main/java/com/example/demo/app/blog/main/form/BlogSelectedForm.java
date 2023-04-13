package com.example.demo.app.blog.main.form;

import javax.validation.constraints.NotNull;

/**
 * ブログ選択フォーム
 * @author nanai
 *
 */
public class BlogSelectedForm {

	/** attribute[selectIdx] */
	public static final String ATTRIBUTE_SELECT_IDX = "selectIdx";

	/** attribute[isDesc] */
	public static final String ATTRIBUTE_IS_DESC = "isDesc";

	// --------------------------------------------------------------------------------------------

	/** 選択番号 */
	@NotNull
	private int selectIdx;

	/** 並び順(0:降順 1:昇順) */
	@NotNull
	private boolean isDesc;

	// --------------------------------------------------------------------------------------------

	/**
	 * コンストラクタ
	 */
	public BlogSelectedForm() {
		this.selectIdx = 0;
		this.isDesc    = true;
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * getter
	 * @return selectIdx
	 */
	public int getSelectIdx() {
		return this.selectIdx;
	}

	/**
	 * setter
	 * @param selectIdx
	 */
	public void setSelectIdx(int selectIdx) {
		this.selectIdx = selectIdx;
	}

	/**
	 * getter
	 * @return isDesc
	 */
	public boolean getIsDesc() {
		return this.isDesc;
	}

	/**
	 * setter
	 * @param isDesc
	 */
	public void setIsDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}
}
