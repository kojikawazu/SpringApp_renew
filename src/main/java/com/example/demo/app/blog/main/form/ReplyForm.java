package com.example.demo.app.blog.main.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ブログ返信フォーム
 */
public class ReplyForm {
	
	/** ブログ返信名最小文字数 */
	private static final int MIN_BLOG_REPLY_NAME_LENGTH = 1;
	
	/** ブログ返信名最大文字数 */
	private static final int MAX_BLOG_REPLY_NAME_LENGTH = 20;
	
	/** ブログ返信名のエラーメッセージ */
	private static final String MESSAGE_REPLY_NAME = MAX_BLOG_REPLY_NAME_LENGTH + "文字を超えています。";
	
	/** ブログ返信コメント最小文字数 */
	private static final int MIN_BLOG_REPLY_COMMENT_LENGTH = 1;
	
	/** ブログ返信コメント最大文字数 */
	private static final int MAX_BLOG_REPLY_COMMENT_LENGTH = 40;
	
	/** ブログ返信コメントのエラーメッセージ */
	private static final String MESSAGE_REPLY_COMMENT = MAX_BLOG_REPLY_COMMENT_LENGTH + "文字を超えています。";
	
	/** ブログ返信名 */
	@NotNull
	@Size(min = MIN_BLOG_REPLY_NAME_LENGTH, 
			max = MAX_BLOG_REPLY_NAME_LENGTH, 
			message = MESSAGE_REPLY_NAME)
	private String name;
	
	/** ブログ返信コメント */
	@NotNull
	@Size(min = MIN_BLOG_REPLY_COMMENT_LENGTH, 
			max = MAX_BLOG_REPLY_COMMENT_LENGTH, 
			message = MESSAGE_REPLY_COMMENT)
	private String comment;

	// ----------------------------------------------------------------------------------------------------
	
	public ReplyForm() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
