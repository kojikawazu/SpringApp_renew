package com.example.demo.app.common.id.blog;

import com.example.demo.common.id.NormalId;

/**
 * ブログIDクラス
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class BlogId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public BlogId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link BlogId}
	 */
	public void setId(BlogId id) {
		if (id == null)	return;
		this.id = id.getId();
	}
}
