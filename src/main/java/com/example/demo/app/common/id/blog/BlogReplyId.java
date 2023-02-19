package com.example.demo.app.common.id.blog;

import com.example.demo.common.id.SuperId;

/**
 * ブログリプライIDクラス
 * @author nanai
 *
 */
public class BlogReplyId implements SuperId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogReplyId(int id) {
		this.id = id;
	}

	/**
	 * getter
	 * @return id
	 */
	@Override
	public int getId() {
		return this.id;
	}
}
