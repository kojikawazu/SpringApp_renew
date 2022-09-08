package com.example.demo.app.blog.main.form;

import javax.validation.constraints.NotNull;

/**
 * ブログ選択フォーム
 * @author nanai
 *
 */
public class BlogSelectedForm {
	
	/** 選択番号 */
	@NotNull
	private int selectIdx;

	// --------------------------------------------------------------------------------------------
	
	public BlogSelectedForm() {
		super();
	}

	public int getSelectIdx() {
		return selectIdx;
	}

	public void setSelectIdx(int selectIdx) {
		this.selectIdx = selectIdx;
	}

}
