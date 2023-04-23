package com.example.demo.app.blog.main.form;

import javax.validation.constraints.NotNull;

import com.example.demo.common.encrypt.CommonEncrypt;

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

	/** attribute[pagingIdx] */
	public static final String ATTRIBUTE_PAGING_IDX = "pagingIdx";

	// --------------------------------------------------------------------------------------------

	/** 選択番号(暗号化) */
	@NotNull
	private String selectIdx;

	/** 並び順(0:降順 1:昇順)(暗号化) */
	@NotNull
	private String isDesc;

	/** ページング番号 */
	@NotNull
	private int pagingIdx;

	// --------------------------------------------------------------------------------------------

	/**
	 * コンストラクタ
	 */
	public BlogSelectedForm() {
		this.selectIdx = "wfssM4JI4nk=";
		this.isDesc    = "ySG/WJvK2Ao=";
		this.pagingIdx = 1;
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * getter(暗号化)
	 * @return selectIdx {@link String}
	 */
	public String getSelectIdx() {
		return this.selectIdx;
	}

	/**
	 * setter(暗号化)
	 * @param selectIdx {@link String}
	 */
	public void setSelectIdx(String selectIdx) {
		if (selectIdx == null)	return; 
		this.selectIdx = selectIdx;
	}

	/**
	 * getter(復号化)
	 * @return selectIdx
	 */
	public int getSelectIdxDecrypted() {
		String dSelectIdx = CommonEncrypt.decrypt(this.selectIdx);
		int    dSelectIdxValue = Integer.valueOf(dSelectIdx);
		return dSelectIdxValue;
	}

	/**
	 * setter(暗号化)
	 * @param selectIdx 原文ID
	 */
	public void setSelectIdxBeforeEncrypt(int selectIdx) {
		String eSelectIdx = CommonEncrypt.encrypt(selectIdx);
		this.selectIdx = eSelectIdx;
	}

	/**
	 * getter(暗号化)
	 * @return isDesc {@link String}
	 */
	public String getIsDesc() {
		return this.isDesc;
	}

	/**
	 * setter(暗号化)
	 * @param isDesc {@link String}
	 */
	public void setIsDesc(String isDesc) {
		if (isDesc == null)	return ;
		this.isDesc = isDesc;
	}

	/**
	 * getter(復号化)
	 * @return isDesc
	 */
	public boolean getIsDescDecrypted() {
		String dIsDesc = CommonEncrypt.decrypt(this.isDesc);
		boolean result = Boolean.valueOf(dIsDesc);
		return result;
	}

	/**
	 * setter(暗号化)
	 * @param isDesc
	 */
	public void setIsDescBeforeEncrypt(boolean isDesc) {
		int isDescValue = (isDesc ? 1 : 0);
		String eIsDesc = CommonEncrypt.encrypt(isDescValue);
		this.isDesc = eIsDesc;
	}

	/**
	 * getter
	 * @return pagingIdx
	 */
	public int getPagingIdx() {
		return this.pagingIdx;
	}

	/**
	 * setter
	 * @param pagingIdx
	 */
	public void setPagingIdx(int pagingIdx) {
		this.pagingIdx = pagingIdx;
	}
}
