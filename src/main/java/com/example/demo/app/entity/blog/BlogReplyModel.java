package com.example.demo.app.entity.blog;

import java.time.LocalDateTime;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信モデル
 * <br>
 * implements {@link SuperBlogModel}
 * @author nanai
 *
 */
public class BlogReplyModel implements SuperBlogModel {

	/** ブログ返信ID */
	private BlogReplyId     id;

	/** ブログID */
	private BlogId          blogId;

	/** 名前 */
	private NameWord        name;

	/** コメント */
	private CommentWord     comment;

	/** いいね数 */
	private ThanksCntNumber thanksCnt;

	/** 生成日付 */
	private LocalDateTime   created;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public BlogReplyModel() {
		this.id 			= new BlogReplyId();
		this.blogId 		= new BlogId();
		this.name 			= new NameWord();
		this.comment 		= new CommentWord();
		this.thanksCnt 		= new ThanksCntNumber();
		this.created 		= LocalDateTime.now();
	}

	/**
	 * コンストラクタ
	 * @param id			{@link BlogReplyId}
	 * @param blogId		{@link BlogId}
	 * @param name			{@link NameWord}
	 * @param comment		{@link CommentWord}
	 * @param thanksCnt		{@link ThanksCntNumber}
	 * @param created		{@link LocalDateTime}
	 */
	public BlogReplyModel(
			BlogReplyId     id,
			BlogId          blogId,
			NameWord        name,
			CommentWord     comment,
			ThanksCntNumber thanksCnt,
			LocalDateTime   created) {
		this();
		this.setId(id);
		this.setBlogId(blogId);
		this.setName(name);
		this.setComment(comment);
		this.setThanksCnt(thanksCnt);
		this.setCreated(created);
	}
	
	/**
	 * コンストラクタ
	 * @param blogId		{@link BlogId}
	 * @param name			{@link NameWord}
	 * @param comment		{@link CommentWord}
	 * @param thanksCnt		{@link ThanksCntNumber}
	 * @param created		{@link LocalDateTime}
	 */
	public BlogReplyModel(
			BlogId          blogId,
			NameWord        name,
			CommentWord     comment,
			ThanksCntNumber thanksCnt,
			LocalDateTime   created) {
		this();
		this.setBlogId(blogId);
		this.setName(name);
		this.setComment(comment);
		this.setThanksCnt(thanksCnt);
		this.setCreated(created);
	}
	
	/**
	 * コンストラクタ
	 * @param model {@link BlogReplyModel}
	 */
	public BlogReplyModel(
			BlogReplyModel model) {
		this();
		if(model != null) {
			this.setId(model.getId());
			this.setBlogId(model.getBlogId());
			this.setName(model.getName());
			this.setComment(model.getComment());
			this.setThanksCnt(model.getThanksCnt());
			this.setCreated(model.getCreated());
		}
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * getter
	 * @return id
	 */
	public int getId() {
		return id.getId();
	}

	/**
	 * setter
	 * @param id
	 */
	public void setId(int id) {
		this.id.setId(id);
	}

	/**
	 * setter
	 * @param id {@link BlogReplyId}
	 */
	public void setId(BlogReplyId id) {
		if (id == null)	return;
		this.id.setId(id);
	}

	/**
	 * getter
	 * @return blogId
	 */
	public int getBlogId() {
		return blogId.getId();
	}

	/**
	 * setter
	 * @param blogId
	 */
	public void setBlogId(int blogId) {
		this.blogId.setId(blogId);
	}

	/**
	 * setter
	 * @param blogId {@link BlogId}
	 */
	public void setBlogId(BlogId blogId) {
		if (blogId == null)	return;
		this.blogId.setId(blogId);
	}

	/**
	 * getter
	 * @return name {@link String}
	 */
	public String getName() {
		return this.name.getWord();
	}

	/**
	 * setter
	 * @param name {@link String}
	 */
	public void setName(String name) {
		if (name == null)	return;
		this.name.setWord(name);
	}

	/**
	 * setter
	 * @param name {@link NameWord}
	 */
	public void setName(NameWord name) {
		if (name == null)	return;
		this.name.setWord(name);
	}

	/**
	 * getter
	 * @return comment {@link String}
	 */
	public String getComment() {
		return this.comment.getWord();
	}

	/**
	 * setter
	 * @param comment {@link String}
	 */
	public void setComment(String comment) {
		if (comment == null)	return;
		this.comment.setWord(comment);
	}

	/**
	 * setter
	 * @param comment {@link CommentWord}
	 */
	public void setComment(CommentWord comment) {
		if (comment == null)	return;
		this.comment.setWord(comment);
	}

	/**
	 * getter
	 * @return thanksCnt
	 */
	public int getThanksCnt() {
		return this.thanksCnt.getNumber();
	}

	/**
	 * setter
	 * @param thanksCnt
	 */
	public void setThanksCnt(int thanksCnt) {
		this.thanksCnt.setNumber(thanksCnt);
	}

	/**
	 * setter
	 * @param thanksCnt {@link ThanksCntNumber}
	 */
	public void setThanksCnt(ThanksCntNumber thanksCnt) {
		if (thanksCnt == null)	return;
		this.thanksCnt.setNumber(thanksCnt);
	}

	/**
	 * getter
	 * @return {@link LocalDateTime}
	 */
	public LocalDateTime getCreated() {
		return this.created;
	}

	/**
	 * setter
	 * @param created {@link LocalDateTime}
	 */
	public void setCreated(LocalDateTime created) {
		if (created == null)	return;
		this.created = created;
	}
}
