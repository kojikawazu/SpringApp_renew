package com.example.demo.app.entity.blog;

import java.time.LocalDateTime;

import com.example.demo.app.common.id.blog.BlogCommentId;
import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログコメントモデル
 * @author nanai
 *
 */
public class BlogCommentModel {
	
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
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param blogid
	 * @param name
	 * @param comment
	 * @param thanksCnt
	 * @param created
	 */
	public BlogCommentModel(
			BlogCommentId   id,
			BlogId          blogid,
			NameWord        name,
			CommentWord     comment,
			ThanksCntNumber thanksCnt,
			LocalDateTime   created
			) {
		this.id = (id == null ?
				new BlogCommentId(0) :
				id);
		
		this.blogid = (blogid == null ?
				new BlogId(0) :
				blogid);
		
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
	public BlogCommentModel(
			BlogCommentModel model) {
		if(model == null) {
			this.id        = new BlogCommentId(0);
			this.blogid    = new BlogId(0);
			this.name      = new NameWord("");
			this.comment   = new CommentWord("");
			this.thanksCnt = new ThanksCntNumber(0);
			this.created   = LocalDateTime.now();
		} else {
			this.id        = new BlogCommentId(model.getId());
			this.blogid    = new BlogId(model.getBlogid());
			this.name      = new NameWord(model.getName());
			this.comment   = new CommentWord(model.getComment());
			this.thanksCnt = new ThanksCntNumber(model.getThanksCnt());
			this.created   = model.getCreated();
		}
	}

	public int getId() {
		return this.id.getId();
	}

	public int getBlogid() {
		return this.blogid.getId();
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
		return ( this.created != null ? 
				this.created : 
				LocalDateTime.of(2000, 01, 01, 00, 00, 00) );
	}
}
