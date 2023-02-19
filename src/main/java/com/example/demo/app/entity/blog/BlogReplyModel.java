package com.example.demo.app.entity.blog;

import java.time.LocalDateTime;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
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
	private CommentWord     comment;
	
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
			CommentWord     comment,
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
				new CommentWord("") :
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
			CommentWord     comment,
			ThanksCntNumber thanksCnt,
			LocalDateTime   created) {
		this.blogId = (blogId == null ? 
				new BlogId(0) :
				blogId);
		
		this.name = (name == null ? 
				new NameWord("") :
				name);
		
		this.comment = (comment == null ? 
				new CommentWord("") :
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
			this.comment   = new CommentWord("");
			this.thanksCnt = new ThanksCntNumber(0);
			this.created   = LocalDateTime.now();
		} else {
			this.id        = new BlogReplyId(model.getId());
			this.blogId    = new BlogId(model.getBlogId());
			this.name      = new NameWord(model.getName());
			this.comment   = new CommentWord(model.getComment());
			this.thanksCnt = new ThanksCntNumber(model.getThanksCnt());
			this.created   = model.getCreated();
		}
	}

	public int getId() {
		return id.getId();
	}

	public int getBlogId() {
		return blogId.getId();
	}
	
	public String getName() {
		return this.name.getWord();
	}

	public String getComment() {
		return this.comment.getWord();
	}

	public int getThanksCnt() {
		return this.thanksCnt.getNumber();
	}

	public LocalDateTime getCreated() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		return ( created != null ? created : dateTime );
	}

}
