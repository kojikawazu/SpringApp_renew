package com.example.demo.app.entity.blog;

import java.time.LocalDateTime;

import com.example.demo.app.common.id.blog.BlogCommentId;
import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログコメントモデル
 * <br>
 * implements {@link SuperBlogModel}
 * @author nanai
 *
 */
public class BlogCommentModel implements SuperBlogModel {

	/** ブログのコメントID */
	private BlogCommentId    id;

	/** ブログID */
	private BlogId           blogid;

	/** コメント名 */
	private NameWord         name;

	/** コメント */
	private CommentWord      comment;

	/** いいね数 */
	private ThanksCntNumber  thanksCnt;

	/** 生成日付 */
	private LocalDateTime    created;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public BlogCommentModel() {
		this.id 		= new BlogCommentId();
		this.blogid		= new BlogId();
		this.name		= new NameWord();
		this.comment	= new CommentWord();
		this.thanksCnt	= new ThanksCntNumber();
		this.created 	= LocalDateTime.now();
	}

	/**
	 * コンストラクタ
	 * @param id		{@link BlogCommentId}
	 * @param blogid	{@link BlogId}
	 * @param name		{@link NameWord}
	 * @param comment	{@link CommentWord}
	 * @param thanksCnt	{@link ThanksCntNumber}
	 * @param created	{@link LocalDateTime}
	 */
	public BlogCommentModel(
			BlogCommentId   id,
			BlogId          blogid,
			NameWord        name,
			CommentWord     comment,
			ThanksCntNumber thanksCnt,
			LocalDateTime   created) {
		this();
		this.setId(id);
		this.setBlogId(blogid);
		this.setName(name);
		this.setComment(comment);
		this.setThanksCnt(thanksCnt);
		this.setCreated(created);
	}
	
	/**
	 * コンストラクタ
	 * @param model {@link BlogCommentModel}
	 */
	public BlogCommentModel(
			BlogCommentModel model) {
		this();
		if(model != null) {
			this.setId(model.getId());
			this.setBlogId(model.getBlogid());
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
		return this.id.getId();
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
	 * @param id {@link BlogCommentId}
	 */
	public void setId(BlogCommentId id) {
		if (id == null)	return;
		this.id.setId(id);
	}

	/**
	 * getter
	 * @return blogid
	 */
	public int getBlogid() {
		return this.blogid.getId();
	}

	/**
	 * setter
	 * @param blogId
	 */
	public void setBlogId(int blogId) {
		this.blogid.setId(blogId);
	}

	/**
	 * setter
	 * @param blogId {@link BlogId}
	 */
	public void setBlogId(BlogId blogId) {
		if (blogId == null)	return;
		this.blogid.setId(blogId);
	}

	/**
	 * getter
	 * @return {@link String}
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
	 * @return {@link String}
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
		if (comment == null)	return ;
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
