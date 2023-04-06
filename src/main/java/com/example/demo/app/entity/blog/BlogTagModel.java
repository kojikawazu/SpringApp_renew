package com.example.demo.app.entity.blog;

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグモデル
 * <br>
 * implements {@link SuperBlogModel}
 * @author nanai
 *
 */
public class BlogTagModel implements SuperBlogModel {

	/** タグID */
	private BlogTagId id;

	/** タグ名 */
	private TagWord   tag;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public BlogTagModel() {
		this.id 	= new BlogTagId();
		this.tag 	= new TagWord();
	}

	/**
	 * コンストラクタ
	 * @param id	{@link BlogTagId}
	 * @param tag	{@link TagWord}
	 */
	public BlogTagModel(
			BlogTagId id,
			TagWord   tag) {
		this();
		this.id.setId(id);
		this.tag.setWord(tag);
	}

	/**
	 * コンストラクタ
	 * @param tag {@link TagWord}
	 */
	public BlogTagModel(
			TagWord tag) {
		this();
		this.tag.setWord(tag);
	}

	/**
	 * コンストラクタ
	 * @param model {@link BlogTagModel}
	 */
	public BlogTagModel(
			BlogTagModel model) {
		this();
		if(model != null) {
			this.id.setId(model.getId());
			this.tag.setWord(model.getTag());
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
	 * @param id {@link BlogTagId}
	 */
	public void setId(BlogTagId id) {
		if (id == null)	return;
		this.id.setId(id);
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
	 * @param tag {@link tag}
	 */
	public void setTag(String tag) {
		if (tag == null)	return;
		this.tag.setWord(tag);
	}

	/**
	 * setter
	 * @param tag {@link tag}
	 */
	public void setTag(TagWord tag) {
		if (tag == null)	return;
		this.tag.setWord(tag);
	}
}
