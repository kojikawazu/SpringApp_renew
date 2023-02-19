package com.example.demo.app.common.id.inquiry;

import com.example.demo.common.id.SuperId;

/**
 * 問い合わせIDクラス
 * @author nanai
 *
 */
public class InquiryId implements SuperId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public InquiryId(int id) {
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
