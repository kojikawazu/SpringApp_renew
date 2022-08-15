package com.example.demo.app.blog.main;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BlogForm {
	
	@NotNull
	@Size(min = 1, max = 20, message = "20文字を超えています。")
	private String blogTitle;
	
	@NotNull
	@Size(min = 1, max = 15, message = "15文字を超えています。")
	private String tag;
	
	@NotNull
	@Size(min = 1, max = 600, message = "600文字を超えています。")
	private String blogContents;
	
	private int thanksCnt;

	public BlogForm() {
		super();
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBlogContents() {
		return blogContents;
	}

	public void setBlogContents(String blogContents) {
		this.blogContents = blogContents;
	}

	public int getThanksCnt() {
		return thanksCnt;
	}

	public void setThanksCnt(int thanksCnt) {
		this.thanksCnt = thanksCnt;
	}
	
}
