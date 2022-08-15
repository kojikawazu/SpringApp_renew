package com.example.demo.app.blog.main;

import javax.validation.constraints.NotNull;

public class BlogSelectedForm {
	
	@NotNull
	private int selectIdx;

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
