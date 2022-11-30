package com.example.demo.app.common.id.blog;

import com.example.demo.common.id.SuperId;

/**
 * ブログタグIDモデル
 * @author nanai
 *
 */
public class BlogTagId implements SuperId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogTagId(int id) {
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
