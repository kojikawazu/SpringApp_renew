package com.example.demo.app.entity.blog;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

/**
 * メインブログモデル
 * <br>
 * implements {@link SuperBlogModel}
 * @author nanai
 *
 */
public class BlogMainModel implements SuperBlogModel {

	/** ブログID */
	private BlogId               id;

	/** タイトル */
	private TittleWord           title;

	/** タグ */
	private TagWord              tag;

	/** コメント */
	private CommentWord          comment;

	/** いいね数 */
	private ThanksCntNumber      thanksCnt;

	/** 生成日付 */
	private LocalDateTime        created;

	/** 更新日付 */
	private LocalDateTime        updated;

	/** 返信モデルリスト */
	private BlogReplyModelList   replyList;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public BlogMainModel() {
		this.id 		= new BlogId();
		this.title 		= new TittleWord();
		this.tag		= new TagWord();
		this.comment	= new CommentWord();
		this.thanksCnt	= new ThanksCntNumber();
		this.created	= LocalDateTime.now();
		this.updated	= LocalDateTime.now();
		this.replyList	= new BlogReplyModelList();
	}

	/**
	 * コンストラクタ
	 * @param id			{@link BlogId}
	 * @param title			{@link TittleWord}
	 * @param tag			{@link TagWord}
	 * @param comment		{@link CommentWord}
	 * @param thanksCnt		{@link ThanksCntNumber}
	 * @param created		{@link LocalDateTime}
	 * @param updated		{@link LocalDateTime}
	 * @param replyList		{@link BlogReplyModelList}
	 */
	public BlogMainModel(
			BlogId               id,
			TittleWord           title,
			TagWord              tag,
			CommentWord          comment,
			ThanksCntNumber      thanksCnt,
			LocalDateTime        created,
			LocalDateTime        updated,
			BlogReplyModelList   replyList
			) {
		this();
		this.setId(id);
		this.setTitle(title);
		this.setTag(tag);
		this.setComment(comment);
		this.setThanksCnt(thanksCnt);
		this.setCreated(created);
		this.setUpdated(updated);
		this.setReplyList(replyList);
	}

	/**
	 * コンストラクタ
	 * @param id			{@link BlogId}
	 * @param title			{@link TittleWord}
	 * @param tag			{@link TagWord}
	 * @param comment		{@link CommentWord}
	 * @param thanksCnt		{@link ThanksCntNumber}
	 * @param created		{@link LocalDateTime}
	 * @param updated		{@link LocalDateTime}
	 */
	public BlogMainModel(
			BlogId               id,
			TittleWord           title,
			TagWord              tag,
			CommentWord          comment,
			ThanksCntNumber      thanksCnt,
			LocalDateTime        created,
			LocalDateTime        updated
			) {
		this();
		this.setId(id);
		this.setTitle(title);
		this.setTag(tag);
		this.setComment(comment);
		this.setThanksCnt(thanksCnt);
		this.setCreated(created);
		this.setUpdated(updated);
	}

	/**
	 * コンストラクタ
	 * @param title			{@link TittleWord}
	 * @param tag			{@link TagWord}
	 * @param comment		{@link CommentWord}
	 * @param thanksCnt		{@link ThanksCntNumber}
	 * @param created		{@link LocalDateTime}
	 * @param updated		{@link LocalDateTime}
	 */
	public BlogMainModel(
			TittleWord           title,
			TagWord              tag,
			CommentWord          comment,
			ThanksCntNumber      thanksCnt,
			LocalDateTime        created,
			LocalDateTime        updated
			) {
		this();
		this.setTitle(title);
		this.setTag(tag);
		this.setComment(comment);
		this.setThanksCnt(thanksCnt);
		this.setCreated(created);
		this.setUpdated(updated);
	}

	/**
	 * コンストラクタ
	 * @param model	{@link BlogMainModel}
	 */
	public BlogMainModel(
			BlogMainModel model) {
		this();
		if(model != null) {
			this.setId(model.getId());
			this.setTitle(model.getTitle());
			this.setTag(model.getTag());
			this.setComment(model.getComment());
			this.setThanksCnt(model.getThanksCnt());
			this.setCreated(model.getCreated());
			this.setUpdated(model.getUpdated());
			this.setReplyList(model.getReplyList());
		}
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * getter
	 * @return {@link BlogId}
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
	 * @param id {@link BlogId}
	 */
	public void setId(BlogId id) {
		if (id == null)	return;
		this.id.setId(id);
	}

	/**
	 * getter
	 * @return {link String}
	 */
	public String getTitle() {
		return this.title.getWord();
	}

	/**
	 * setter
	 * @param title {@link String}
	 */
	public void setTitle(String title) {
		if (title == null)	return;
		this.title.setWord(title);
	}

	/**
	 * setter
	 * @param title {@link TittleWord}
	 */
	public void setTitle(TittleWord title) {
		if (title == null)	return;
		this.title.setWord(title);
	}

	/**
	 * getter
	 * @return {@link String}
	 */
	public String getTag() {
		return this.tag.getWord();
	}

	/**
	 * setter
	 * @param tag {@link String}
	 */
	public void setTag(String tag) {
		if (tag == null)	return;
		this.tag.setWord(tag);
	}

	/**
	 * setter
	 * @param tag {@link TagWord}
	 */
	public void setTag(TagWord tag) {
		if (tag == null)	return;
		this.tag.setWord(tag);
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
		if (comment == null)	return;
		this.comment.setWord(comment);
	}

	/**
	 * getter
	 * @return int型
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

	/**
	 * getter
	 * @return {@link LocalDateTime}
	 */
	public LocalDateTime getUpdated() {
		return this.updated;
	}

	/**
	 * setter
	 * @param updated {@link LocalDateTime}
	 */
	public void setUpdated(LocalDateTime updated) {
		if(updated == null)	return;
		this.updated = updated;
	}

	/**
	 * getter
	 * @return {@link List}<{@link BlogReplyModel}>
	 */
	public List<BlogReplyModel> getReplyList() {
		return this.replyList.getList();
	}

	/**
	 * setter
	 * @param replyList {@link List}<{@link BlogReplyModel}>
	 */
	public void setReplyList(List<BlogReplyModel> replyList) {
		if (replyList == null)	return;
		this.replyList.setList(replyList);
	}

	/**
	 * setter
	 * @param replyList {@link BlogReplyModelList}
	 */
	public void setReplyList(BlogReplyModelList replyList) {
		if (replyList == null)	return;
		this.replyList.setList(replyList);
	}
}
