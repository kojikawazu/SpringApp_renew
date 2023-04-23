package com.example.demo.app.entity.blog;

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.common.encrypt.CommonEncrypt;
import com.example.demo.common.id.EncryptedId;
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

	/** 暗号化済ID */
	private EncryptedId encrptedId;

	/** タグ名 */
	private TagWord   tag;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public BlogTagModel() {
		this.id 	    = new BlogTagId();
		this.encrptedId = new EncryptedId();
		this.tag 	    = new TagWord();
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
		this.encrptedId.setIdNoEncrypt(id.getId());
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
			this.encrptedId.setIdNoEncrypt(model.getId());
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

		// 暗号化済IDの設定
		this.encrptedId.setIdNoEncrypt(id);
	}

	/**
	 * setter
	 * @param id {@link BlogTagId}
	 */
	public void setId(BlogTagId id) {
		if (id == null)	return;
		this.id.setId(id);

		// 暗号化済IDの設定
		this.encrptedId.setIdNoEncrypt(id.getId());
	}

	/**
	 * getter
	 * @return encrptedId 暗号化済ID
	 */
	public String getEncryptedId() {
		return this.encrptedId.getId();
	}

	/**
	 * setter
	 * @param encrptedId 暗号化済ID
	 */
	public void setEncryptedId(String encrptedId) {
		if (encrptedId == null)	return;
		this.encrptedId.setId(encrptedId);
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
