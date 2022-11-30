package com.example.demo.app.common.id.blog;

import com.example.demo.common.id.SuperId;

/**
 * ブログIDクラス
 * @author nanai
 *
 */
public class BlogId implements SuperId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public BlogId(int id) {
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
