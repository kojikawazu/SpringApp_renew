package com.example.demo.app.common.id.inquiry;

import com.example.demo.common.id.SuperId;

/**
 * 問い合わせ返信IDクラス
 * @author nanai
 *
 */
public class InquiryReplyId implements SuperId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public InquiryReplyId(int id) {
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
