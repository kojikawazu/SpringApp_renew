package com.example.demo.app.entity;

import java.time.LocalDateTime;

import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信モデル
 * @author nanai
 *
 */
public class BlogReplyModel {
	
	/** ブログ返信ID */
	private BlogReplyId     id;
	
	/** ブログID */
	private BlogId          blogId;
	
	/** 名前 */
	private NameWord        name;
	
	/** コメント */
	private NameWord        comment;
	
	/** いいね数 */
	private ThanksCntNumber thanksCnt;
	
	/** 生成日付 */
	private LocalDateTime   created;

	/**
	 * コンストラクタ
	 * @param id
	 * @param blogId
	 * @param name
	 * @param comment
	 * @param thanksCnt
	 * @param created
	 */
	public BlogReplyModel(
			BlogReplyId     id,
			BlogId          blogId,
			NameWord        name,
			NameWord        comment,
			ThanksCntNumber thanksCnt,
			LocalDateTime   created) {
		this.id = (id == null ?
				new BlogReplyId(0) :
				id);
		
		this.blogId = (blogId == null ? 
				new BlogId(0) :
				blogId);
		
		this.name = (name == null ? 
				new NameWord("") :
				name);
		
		this.comment = (comment == null ? 
				new NameWord("") :
					comment);
		
		this.thanksCnt = (thanksCnt == null ? 
				new ThanksCntNumber(0) :
				thanksCnt);
		
		this.created = (created == null ? 
				LocalDateTime.now() :
				created);
	}
	
	/**
	 * コンストラクタ
	 * @param blogId
	 * @param name
	 * @param comment
	 * @param thanksCnt
	 * @param created
	 */
	public BlogReplyModel(
			BlogId          blogId,
			NameWord        name,
			NameWord        comment,
			ThanksCntNumber thanksCnt,
			LocalDateTime   created) {
		this.blogId = (blogId == null ? 
				new BlogId(0) :
				blogId);
		
		this.name = (name == null ? 
				new NameWord("") :
				name);
		
		this.comment = (comment == null ? 
				new NameWord("") :
					comment);
		
		this.thanksCnt = (thanksCnt == null ? 
				new ThanksCntNumber(0) :
				thanksCnt);
		
		this.created = (created == null ? 
				LocalDateTime.now() :
				created);
	}
	
	/**
	 * コンストラクタ
	 * @param model
	 */
	public BlogReplyModel(
			BlogReplyModel model) {
		if(model == null) {
			this.id        = new BlogReplyId(0);
			this.blogId    = new BlogId(0);
			this.name      = new NameWord("");
			this.comment   = new NameWord("");
			this.thanksCnt = new ThanksCntNumber(0);
			this.created   = LocalDateTime.now();
		} else {
			this.id        = new BlogReplyId(model.getId());
			this.blogId    = new BlogId(model.getBlogId());
			this.name      = new NameWord(model.getName());
			this.comment   = new NameWord(model.getComment());
			this.thanksCnt = new ThanksCntNumber(model.getThanksCnt());
			this.created   = model.getCreated();
		}
	}

	public int getId() {
		return id.getId();
	}

	protected void setId(int id) {
		this.id = new BlogReplyId(id);
	}

	public int getBlogId() {
		return blogId.getId();
	}

	protected void setBlogId(int blogId) {
		this.blogId = new BlogId(blogId);
	}
	
	public String getName() {
		return this.name.getWord();
	}

	protected void setName(String name) {
		if(name == null)	return ;
		this.name = new NameWord(name);
	}

	public String getComment() {
		return this.comment.getWord();
	}

	protected void setComment(String comment) {
		if(comment == null)	return ;
		this.comment = new NameWord(comment);
	}

	public int getThanksCnt() {
		return this.thanksCnt.getNumber();
	}

	protected void setThanksCnt(int thanksCnt) {
		this.thanksCnt = new ThanksCntNumber(thanksCnt);
	}

	public LocalDateTime getCreated() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		return ( created != null ? created : dateTime );
	}

	protected void setCreated(LocalDateTime created) {
		this.created = created;
	}

}
