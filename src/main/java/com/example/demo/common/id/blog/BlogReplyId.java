package com.example.demo.common.id.blog;

import com.example.demo.common.id.intro.IntroId;

/**
 * ブログリプライIDクラス
 * @author nanai
 *
 */
public class BlogReplyId implements IntroId {

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
