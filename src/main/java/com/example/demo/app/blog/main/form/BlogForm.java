package com.example.demo.app.blog.main.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ブログフォーム
 * @author nanai
 *
 */
public class BlogForm {
	
	/** ブログタイトル最小文字数 */
	private static final int MIN_BLOG_TITLE_LENGTH = 1;
	
	/** ブログタイトル最大文字数 */
	private static final int MAX_BLOG_TITLE_LENGTH = 20;
	
	/** ブログタイトルのエラーメッセージ */
	private static final String MESSAGE_BLOG_TITLE = MAX_BLOG_TITLE_LENGTH + "文字を超えています。";
	
	/** ブログタイトル最小文字数 */
	private static final int MIN_BLOG_TAG_LENGTH = 1;
	
	/** ブログタイトル最大文字数 */
	private static final int MAX_BLOG_TAG_LENGTH = 15;
	
	/** ブログタイトルのエラーメッセージ */
	private static final String MESSAGE_BLOG_TAG = MAX_BLOG_TAG_LENGTH + "文字を超えています。";
	
	/** ブログコンテンツの最小文字数 */
	private static final int MIN_BLOG_CONTENTS_LENGTH = 1;
	
	/** ブログコンテンツの最大文字数 */
	private static final int MAX_BLOG_CONTENTS_LENGTH = 600;
	
	/** ブログコンテンツのエラーメッセージ */
	private static final String MESSAGE_BLOG_CONTENTS = MAX_BLOG_CONTENTS_LENGTH + "文字を超えています。";
	
	/** ブログタイトル */
	@NotNull
	@Size(min = MIN_BLOG_TITLE_LENGTH, 
			max = MAX_BLOG_TITLE_LENGTH, 
			message = MESSAGE_BLOG_TITLE)
	private String blogTitle;
	
	/** ブログタグ */
	@NotNull
	@Size(min = MIN_BLOG_TAG_LENGTH, 
			max = MAX_BLOG_TAG_LENGTH, 
			message = MESSAGE_BLOG_TAG)
	private String tag;
	
	/** ブログコンテンツ */
	@NotNull
	@Size(min = MIN_BLOG_CONTENTS_LENGTH, 
			max = MAX_BLOG_CONTENTS_LENGTH, 
			message = MESSAGE_BLOG_CONTENTS)
	private String blogContents;
	
	/** いいね数 */
	private int thanksCnt;
	
	// ---------------------------------------------------------------------------------------------

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
