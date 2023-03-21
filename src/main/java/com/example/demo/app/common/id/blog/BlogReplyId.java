package com.example.demo.app.common.id.blog;

import com.example.demo.common.id.NormalId;

/**
 * ブログリプライIDクラス
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class BlogReplyId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public BlogReplyId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogReplyId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link BlogReplyId}
	 */
	public void setId(BlogReplyId id) {
		if (id == null)	return;
		this.id = id.getId();
	}
}
